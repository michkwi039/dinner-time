package pl.polsl.dinnertime.exceptions;

import java.util.List;

public class PasswordRulesNotMatchException extends RuntimeException {

    private final List<String> errors;

    public PasswordRulesNotMatchException(List<String> errors) {
        super();
        this.errors = errors;
    }

    @Override
    public String getMessage() {
        return MessageProvider.getString("user.passwordRules.negative");
    }

    public List<String> getErrors() {
        return errors;
    }
}

