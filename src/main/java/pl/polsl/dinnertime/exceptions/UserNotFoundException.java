package pl.polsl.dinnertime.exceptions;

import java.util.List;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException() {
        super();
    }

    @Override
    public String getMessage() {
        return MessageProvider.getString("user.verification.negative");
    }
}
