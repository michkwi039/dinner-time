package pl.polsl.dinnertime.user.model.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.polsl.dinnertime.user.model.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> getUserByUsername(String username);
}
