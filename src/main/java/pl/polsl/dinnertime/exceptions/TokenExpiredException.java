package pl.polsl.dinnertime.exceptions;

public class TokenExpiredException extends RuntimeException {

    public TokenExpiredException() {
        super();
    }

    @Override
    public String getMessage() {
        return MessageProvider.getString("token.verification.negative");
    }
}
