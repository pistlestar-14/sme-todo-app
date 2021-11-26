package com.sme.todo.util;

import com.datastax.driver.core.utils.UUIDs;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

public class DateUtil {

    private DateUtil() {}

    private static final ZoneOffset ZONE_OFFSET = ZoneOffset.UTC;

    public static ZonedDateTime timeNow() {
        return ZonedDateTime.now(ZONE_OFFSET);
    }

    public static ZonedDateTime fromEpochMilli(Long milli) {
        return ZonedDateTime.ofInstant(Instant.ofEpochMilli(milli), ZONE_OFFSET);
    }

    public static Long toEpochMilli(ZonedDateTime zonedDateTime) {
        return zonedDateTime == null ? null : zonedDateTime.toInstant().toEpochMilli();
    }

    public static String getUniqueTimeBasedUUID() {
        return UUIDs.timeBased().toString();
    }

}
