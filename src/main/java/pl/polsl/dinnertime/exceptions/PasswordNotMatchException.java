package pl.polsl.dinnertime.exceptions;

import java.util.List;

public class PasswordNotMatchException extends RuntimeException{
    private final List<String> errors;

    public PasswordNotMatchException(List<String> errors) {
        super();
        this.errors = errors;
    }

    @Override
    public String getMessage() {
        return MessageProvider.getString("user.password.negative");
    }

    public List<String> getErrors() {
        return errors;
    }
}
