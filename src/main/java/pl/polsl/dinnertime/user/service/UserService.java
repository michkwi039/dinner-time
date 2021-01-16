package pl.polsl.dinnertime.user.service;

import org.passay.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.polsl.dinnertime.exceptions.PasswordNotMatchException;
import pl.polsl.dinnertime.exceptions.PasswordRulesNotMatchException;
import pl.polsl.dinnertime.exceptions.TokenExpiredException;
import pl.polsl.dinnertime.exceptions.UserNotFoundException;
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
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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

    public User addUser(UserAccount userAccount) throws PasswordRulesNotMatchException {
        User newUser = new User();
        newUser.setUsername(userAccount.getUsername());
        validatePassword(userAccount.getPassword());
        newUser.setPassword(passwordEncoder.encode(userAccount.getPassword()));
        newUser.setName(userAccount.getName());
        newUser.setLastname(userAccount.getLastname());
        newUser.setEmail(userAccount.getEmail());
        newUser.setStatus(Status.INACTIVE);
        newUser.setRole(Role.USER);
        userRepository.save(newUser);
        return newUser;
    }

    private void validatePassword(String password) throws PasswordRulesNotMatchException {
        List<CharacterRule> rules = getPasswordRules();
        PasswordValidator passwordValidator = new PasswordValidator(rules);
        RuleResult result = passwordValidator.validate(new PasswordData(password));
        if (!result.isValid()) {
            throw new PasswordRulesNotMatchException(passwordValidator.getMessages(result));
        }
    }

    private List<CharacterRule> getPasswordRules() {
        CharacterData lowerCaseChars = EnglishCharacterData.LowerCase;
        CharacterRule lowerCaseRule = new CharacterRule(lowerCaseChars);
        lowerCaseRule.setNumberOfCharacters(3);

        CharacterData upperCaseChars = EnglishCharacterData.UpperCase;
        CharacterRule upperCaseRule = new CharacterRule(upperCaseChars);
        upperCaseRule.setNumberOfCharacters(1);

        CharacterData digitChars = EnglishCharacterData.Digit;
        CharacterRule digitRule = new CharacterRule(digitChars);
        digitRule.setNumberOfCharacters(2);

        return Arrays.asList(lowerCaseRule, upperCaseRule, digitRule);
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
            throw new TokenExpiredException();
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

    public void changePassword(String oldPassword, String newPassword) throws PasswordNotMatchException, PasswordRulesNotMatchException {
        User user = authenticateUser();
        if (passwordEncoder.matches(oldPassword, user.getPassword())) {
            validatePassword(newPassword);
            user.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(user);
        } else {
            throw new PasswordNotMatchException();
        }
    }

    public User authenticateUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        return userRepository.getUserByUsername(username)
                .orElseThrow(() -> new UserNotFoundException());
    }

    public void deleteUser(Long id) {
        User user = userRepository.getOne(id);
        user.setStatus(Status.INACTIVE);
        user.setName("unknown");
        user.setLastname("unknown");
        user.setEmail("unknown");
        user.setUsername(user.getId().toString());
        userRepository.save(user);
    }

}
