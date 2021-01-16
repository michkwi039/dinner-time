package pl.polsl.dinnertime.exceptions;

import lombok.Builder;
import lombok.Getter;
import pl.polsl.dinnertime.exceptions.MessageProvider;
import pl.polsl.dinnertime.utils.DateTimeUtils;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Builder
@Getter
public class AppExceptionHolder {

    @Builder.Default
    private final String dateTime = DateTimeUtils.formatBasic(LocalDateTime.now());
    @Builder.Default
    private final String message = MessageProvider.getString("app.execution.exception.message");
    @Builder.Default
    private final List<String> details = Collections.emptyList();
    @Builder.Default
    private final String cause = "Internal server error.";

}
