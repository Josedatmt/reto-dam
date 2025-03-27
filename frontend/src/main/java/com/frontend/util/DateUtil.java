package com.frontend.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat DATETIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static String formatDate(Date date) {
        return date != null ? DATE_FORMAT.format(date) : "";
    }

    public static String formatDateTime(Date date) {
        return date != null ? DATETIME_FORMAT.format(date) : "";
    }

    public static Date parseDate(String dateStr) throws ParseException {
        return dateStr != null && !dateStr.isEmpty() ? DATE_FORMAT.parse(dateStr) : null;
    }

    public static Date parseDateTime(String dateStr) throws ParseException {
        return dateStr != null && !dateStr.isEmpty() ? DATETIME_FORMAT.parse(dateStr) : null;
    }
}