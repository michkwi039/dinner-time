package pl.polsl.dinnertime.user.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.polsl.dinnertime.exceptions.TokenExpiredException;
import pl.polsl.dinnertime.user.model.dto.SimpleUser;
import pl.polsl.dinnertime.user.model.dto.UserAccount;
import pl.polsl.dinnertime.user.model.entity.Role;
import pl.polsl.dinnertime.user.model.entity.Status;
import pl.polsl.dinnertime.user.model.entity.User;
import pl.polsl.dinnertime.user.model.entity.UserRepository;
import pl.polsl.dinnertime.verificationToken.model.VerificationToken;
import pl.polsl.dinnertime.verificationToken.model.VerificationTokenRepository;

import javax.transaction.Transactional;
import java.time.ZonedDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Transactional
@Service
public class UserService {

    private final UserRepository userRepository;
    private final VerificationTokenRepository verificationTokenRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, VerificationTokenRepository verificationTokenRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.verificationTokenRepository = verificationTokenRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User addUser(UserAccount userAccount) {
        User newUser = new User();
        newUser.setUsername(userAccount.getUsername());
        newUser.setPassword(passwordEncoder.encode(userAccount.getPassword()));
        newUser.setName(userAccount.getName());
        newUser.setLastname(userAccount.getLastname());
        newUser.setEmail(userAccount.getEmail());
        newUser.setStatus(Status.INACTIVE);
        newUser.setRole(Role.USER);
        userRepository.save(newUser);
        return newUser;
    }

    public VerificationToken createVerificationToken(User user, String token) {
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setUser(user);
        verificationToken.setToken(token);
        verificationTokenRepository.save(verificationToken);
        return verificationToken;
    }

    public void activateUser(String token) {
        VerificationToken verificationToken = getVerificationToken(token);
        User user = verificationToken.getUser();
        user.setStatus(Status.ACTIVE);
        userRepository.save(user);
        verificationTokenRepository.delete(verificationToken);
    }

    private VerificationToken getVerificationToken(String token) throws TokenExpiredException {
        VerificationToken verificationToken = verificationTokenRepository.getByToken(token);
        if (verificationToken.getExpiryDate().isBefore(ZonedDateTime.now().minusHours(72))) {
            throw new TokenExpiredException("Token is expired.");
        }
        return verificationToken;
    }

    public List<SimpleUser> getUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(SimpleUser::new)
                .sorted(Comparator.comparing(SimpleUser::getUsername))
                .collect(Collectors.toList());
    }
}
