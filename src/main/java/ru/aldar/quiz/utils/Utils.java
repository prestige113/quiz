package ru.aldar.quiz.utils;

import static java.util.Objects.nonNull;

import java.util.Date;
import java.util.Objects;

public class Utils {

    public static boolean isNull(Object value) {
        return Objects.isNull(value);
    }

    public static boolean isNull(String value) {
        return value == null || value.isEmpty();
    }

    public static boolean isNull(Long value) {
        return value == null || value == 0;
    }


    public static Date toDate(Long time) {
        return nonNull(time) ? new Date(time) : null;
    }
}
