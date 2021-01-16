package pl.polsl.dinnertime.exceptions.handler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import pl.polsl.dinnertime.exceptions.AppExceptionHolder;
import pl.polsl.dinnertime.exceptions.MessageProvider;
import pl.polsl.dinnertime.exceptions.PasswordRulesNotMatchException;

@ControllerAdvice
public class UserExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = PasswordRulesNotMatchException.class)
    public ResponseEntity<Object> handlePasswordException(PasswordRulesNotMatchException e,
                                                                  WebRequest request) {
        AppExceptionHolder holder = AppExceptionHolder.builder()
                .message(MessageProvider.getString("user.passwordRules.negative"))
                .details(e.getErrors())
                .build();
        return handleExceptionInternal(e, holder, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

}
