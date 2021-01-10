package pl.polsl.dinnertime.security.jwt;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class UsernameAndPasswordAuthenticationRequest {
    private String username;
    private String password;
}
