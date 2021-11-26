package com.sme.todo.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

class UtilTest {

    @Test
    void test_from_with_status() {
        Assertions.assertEquals(200, Util.from(HttpStatus.OK).getStatusCodeValue());
    }

    @Test
    void test_from_with_status_and_body() {
        Assertions.assertEquals(1, Util.from(1, HttpStatus.OK).getBody());
    }
}