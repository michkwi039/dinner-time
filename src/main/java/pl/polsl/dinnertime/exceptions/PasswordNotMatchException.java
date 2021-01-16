package pl.polsl.dinnertime.exceptions;

public class PasswordNotMatchException extends RuntimeException{

    public PasswordNotMatchException() {
        super();
    }

    @Override
    public String getMessage() {
        return MessageProvider.getString("user.password.negative");
    }
}
