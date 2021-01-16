package pl.polsl.dinnertime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.polsl.dinnertime.user.model.entity.Role;
import pl.polsl.dinnertime.user.model.entity.Status;
import pl.polsl.dinnertime.user.model.entity.User;
import pl.polsl.dinnertime.user.model.entity.UserRepository;

@Service
public class DBInit implements CommandLineRunner {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DBInit(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public void run(String... args) {

        User mentor = new User();
        mentor.setRole(Role.ADMIN);
        mentor.setStatus(Status.ACTIVE);
        mentor.setUsername("admin");
        mentor.setEmail("admin@email.com");
        mentor.setName("Krzysztof");
        mentor.setLastname("Suwaj");
        mentor.setPassword(passwordEncoder.encode("admin"));
        userRepository.save(mentor);

    }
}

