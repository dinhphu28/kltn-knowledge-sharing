package com.ndp.knowsharing.Utils.DateTime;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.stereotype.Component;

@Component
public class MyDateTimeUtils {
    public LocalDate getCurrentDate() {
        return LocalDate.now();
    }

    public LocalTime getCurrentTime() {
        return LocalTime.now();
    }
}