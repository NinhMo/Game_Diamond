package com.game_diamond.utils;

import lombok.extern.slf4j.Slf4j;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.WeekFields;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Create by
 *
 * @author : Nguyen Ba Hung
 * @since : 1/5/2021, Tue
 **/
@Slf4j
public class TimeUtils {
    public static Timestamp getCurrentTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }

    public static boolean isEqualDate(Date date1, Date date2) {
        return date1.compareTo(date2) == 0;
    }

    public static boolean isEqualDateByTwoTimestamp(Timestamp timestamp1, Timestamp timestamp2) {
        long longTimestamp1 = timestamp1.getTime();
        long longTimestamp2 = timestamp2.getTime();

        String date1 = new SimpleDateFormat("yyy-MM-dd").format(new Date(longTimestamp1));
        String date2 = new SimpleDateFormat("yyy-MM-dd").format(new Date(longTimestamp2));

        return date1.equals(date2);
    }

    public static boolean timestamp1GreaterThanTimestamp2ByDate(Timestamp timestamp1, Timestamp timestamp2) {
        long longTimestamp1 = timestamp1.getTime();
        long longTimestamp2 = timestamp2.getTime();

        String date1Str = new SimpleDateFormat("yyy-MM-dd").format(new Date(longTimestamp1));
        String date2Str = new SimpleDateFormat("yyy-MM-dd").format(new Date(longTimestamp2));

        Date date1 = null;
        Date date2 = null;
        try {
            date1 = new SimpleDateFormat("yyy-MM-dd").parse(date1Str);
            date2 = new SimpleDateFormat("yyy-MM-dd").parse(date2Str);

        } catch (ParseException e) {
            log.error("Error convert string to date: {}", e.getMessage());
        }
        if (date1 == null || date2 == null) {
            return false;
        }
        return date1.after(date2);
    }

    public static LocalDate getDateNow() {
        return LocalDate.now();
    }

    public static int getWeekNowOfYear() {
        LocalDateTime currentTime = LocalDateTime.now();
        return currentTime.get(WeekFields.of(Locale.GERMANY).weekOfWeekBasedYear());
    }

    public static int getMonthNowOfYear() {
        LocalDateTime currentTime = LocalDateTime.now();
        return currentTime.getMonthValue();
    }

    public static String timeMonthCode() {
        LocalDateTime currentTime = LocalDateTime.now();
        int monthOfYear = currentTime.getMonthValue();
        int year = currentTime.getYear();
        return String.format("%02d_%04d", monthOfYear, year);
    }

    public static String timeWeekCode() {
        LocalDateTime currentTime = LocalDateTime.now();
        int weekOfYear = currentTime.get(WeekFields.of(Locale.GERMANY).weekOfWeekBasedYear());
        int year = currentTime.get(WeekFields.of(Locale.GERMANY).weekBasedYear());
        return String.format("%02d_%04d", weekOfYear, year);
    }

    public static String timeLastWeekCode() {
        LocalDateTime currentTime = LocalDateTime.now();
        int weekOfYear = currentTime.get(WeekFields.of(Locale.GERMANY).weekOfWeekBasedYear());
        int year = currentTime.get(WeekFields.of(Locale.GERMANY).weekBasedYear());
        int lastWeekOfYear;
        int lastYear;
        if (weekOfYear == 1) {
            lastWeekOfYear = 52;
            lastYear = year - 1;
        } else {
            lastWeekOfYear = weekOfYear - 1;
            lastYear = year;
        }
        return String.format("%02d_%04d", lastWeekOfYear, lastYear);
    }

    public static Date getDateByHour(Date date, int hours) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY, hours);
        return calendar.getTime();
    }

    public static Timestamp getTimestampByDateAndHours(Date date, int hours) {
        return new Timestamp(getDateByHour(date, hours).getTime());
    }
}