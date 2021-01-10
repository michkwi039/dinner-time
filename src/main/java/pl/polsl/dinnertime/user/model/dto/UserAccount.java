package pl.polsl.dinnertime.user.model.dto;

import lombok.Data;

@Data
public class UserAccount{
    private String email;
    private String username;
    private String password;
    private String name;
    private String lastname;

    public UserAccount() {
    }

    public UserAccount(String email, String username, String password, String name, String lastname) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.name = name;
        this.lastname = lastname;
    }
}
