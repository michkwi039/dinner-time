package pl.polsl.dinnertime.verificationToken.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.polsl.dinnertime.user.model.entity.User;

import java.time.ZonedDateTime;
import java.util.List;

@Repository
public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {
    VerificationToken getByToken(String token);

    VerificationToken getByUser(User user);

    List<VerificationToken> getByExpiryDateIsBefore(ZonedDateTime expiryDate);
}

