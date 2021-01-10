package pl.polsl.dinnertime.user.service;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;
import pl.polsl.dinnertime.user.model.entity.User;

@Getter
@Setter
public class OnRegistrationCompleteEvent extends ApplicationEvent {
    private String url;
    private User user;

    public OnRegistrationCompleteEvent(String url, User user) {
        super(user);
        this.url = url;
        this.user = user;
    }
}
