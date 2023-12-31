package com.learning.productservice.util;

import java.time.Clock;
import java.time.LocalDateTime;

public class DateTimeUtils {

    private DateTimeUtils() {
    }

    public static LocalDateTime getCurrentDateTimeUtc() {
        return LocalDateTime.now(Clock.systemUTC());
    }
}
