package pl.polsl.dinnertime.user.model;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.polsl.dinnertime.user.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> getUserByUsername(String username);
}
