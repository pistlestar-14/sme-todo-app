package com.sme.todo.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DateUtilTest {

    @Test
    void toEpochMilli_with_null() {
        Assertions.assertNull(DateUtil.toEpochMilli(null));
    }

    @Test
    void fromEpochMilli() {
        Long milli = DateUtil.toEpochMilli(DateUtil.timeNow());
        Assertions.assertNotNull(DateUtil.fromEpochMilli(milli));
    }

    @Test
    void fromEpochMilli_with_null_input() {
        Assertions.assertNull(null);
    }

    @Test
    void getUniqueTimeBasedUUID() {
        Assertions.assertEquals(36, DateUtil.getUniqueTimeBasedUUID().length());
    }
}