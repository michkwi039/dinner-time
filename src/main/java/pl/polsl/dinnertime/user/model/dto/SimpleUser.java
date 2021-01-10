package pl.polsl.dinnertime.user.model.dto;

import lombok.Data;
import pl.polsl.dinnertime.user.model.entity.Role;
import pl.polsl.dinnertime.user.model.entity.Status;
import pl.polsl.dinnertime.user.model.entity.User;

@Data
public class SimpleUser {
    private Long id;
    private String username;
    private String name;
    private String lastName;
    private String email;
    private Status status;
    private Role role;

    public SimpleUser(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.name = user.getName();
        this.lastName = user.getLastname();
        this.email = user.getEmail();
        this.status = user.getStatus();
        this.role = user.getRole();
    }

}

