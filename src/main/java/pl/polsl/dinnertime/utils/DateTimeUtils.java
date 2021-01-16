package pl.polsl.dinnertime.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtils {

    private static final DateTimeFormatter BASIC_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static String formatBasic(LocalDateTime dateTime) {
        return BASIC_DATE_TIME_FORMATTER.format(dateTime);
    }
}
