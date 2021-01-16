package pl.polsl.dinnertime.exceptions.handler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import pl.polsl.dinnertime.exceptions.*;

@ControllerAdvice
public class UserExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = PasswordRulesNotMatchException.class)
    public ResponseEntity<Object> handlePasswordException(PasswordRulesNotMatchException e,
                                                                  WebRequest request) {
        AppExceptionHolder holder = AppExceptionHolder.builder()
                .message(e.getMessage())
                .details(e.getErrors())
                .build();
        return handleExceptionInternal(e, holder, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<Object> handlePasswordRulesException(UserNotFoundException e,
                                                          WebRequest request) {
        AppExceptionHolder holder = AppExceptionHolder.builder()
                .message(e.getMessage())
                .build();
        return handleExceptionInternal(e, holder, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(value = TokenExpiredException.class)
    public ResponseEntity<Object> handleTokenException(TokenExpiredException e,
                                                       WebRequest request) {
        AppExceptionHolder holder = AppExceptionHolder.builder()
                .message(e.getMessage())
                .build();
        return handleExceptionInternal(e, holder, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(value = PasswordNotMatchException.class)
    public ResponseEntity<Object> handlePasswordException(PasswordNotMatchException e,
                                                       WebRequest request) {
        AppExceptionHolder holder = AppExceptionHolder.builder()
                .message(e.getMessage())
                .build();
        return handleExceptionInternal(e, holder, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

}
