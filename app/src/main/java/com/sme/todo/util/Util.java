package com.sme.todo.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class Util {

    public static <T> ResponseEntity<T> from(HttpStatus status) {
        return from(null, status);
    }

    public static <T> ResponseEntity<T> from(T body, HttpStatus status) {
        return new ResponseEntity<>(body, status);
    }

}
