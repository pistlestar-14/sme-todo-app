package com.sme.todo.util;

import com.datastax.driver.core.utils.UUIDs;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtil {
    private static final ZoneOffset ZONE_OFFSET = ZoneOffset.UTC;

    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSz");

    public static ZonedDateTime timeNow() {
        return ZonedDateTime.now(ZONE_OFFSET);
    }

    public static ZonedDateTime fromEpochMilli(Long milli) {
        return ZonedDateTime.ofInstant(Instant.ofEpochMilli(milli), ZONE_OFFSET);
    }

    public static Long timeNowToEpochMilli() {
        return toEpochMilli(timeNow());
    }

    public static Long toEpochMilli(ZonedDateTime zonedDateTime) {
        return zonedDateTime == null ? null : zonedDateTime.toInstant().toEpochMilli();
    }

    public static ZonedDateTime getZoneDateTime(Date dateToConvert) {
        return ZonedDateTime.ofInstant(dateToConvert.toInstant(), ZONE_OFFSET);
    }

    public static String getFormattedDate(Long milli) {
        return getFormattedDate(fromEpochMilli(milli));
    }

    public static String getFormattedDate(Long milli, String format) {
        return DateTimeFormatter.ofPattern(format).format(fromEpochMilli(milli));
    }

    public static String getFormattedDate(ZonedDateTime dateTime) {
        return DATE_FORMATTER.format(dateTime);
    }

    public static String getFormattedDate(ZonedDateTime dateTime, String format) {
        return DateTimeFormatter.ofPattern(format).format(dateTime);
    }

    public static String getUniqueTimeBasedUUID() {
        return UUIDs.timeBased().toString();
    }

}
