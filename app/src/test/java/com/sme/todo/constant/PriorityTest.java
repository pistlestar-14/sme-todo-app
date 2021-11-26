package com.sme.todo.constant;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

class PriorityTest {

    @Test
    void fromValue() {
        Arrays.stream(Priority.values())
                .map(Priority::getType)
                .map(Priority::fromValue)
                .forEach(Assertions::assertNotNull);
    }

    @Test
    void fromName() {
        Arrays.stream(Priority.values())
                .map(Priority::getName)
                .map(Priority::fromName)
                .forEach(Assertions::assertNotNull);
    }
}