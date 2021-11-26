package com.sme.todo.constant;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import org.apache.commons.lang3.StringUtils;

public enum Priority {
    NONE(0),
    LOW(1),
    MEDIUM(2),
    HIGH(3),
    ;

    private final int type;

    Priority(int type) {
        this.type = type;
    }

    public static Priority fromValue(int value) {
        switch (value) {
            case 1: return LOW;
            case 2: return MEDIUM;
            case 3: return HIGH;
            default: return NONE;
        }
    }

    @JsonCreator
    public static Priority fromName(String name) {
        switch (StringUtils.upperCase(name)) {
            case "LOW": return LOW;
            case "MEDIUM": return MEDIUM;
            case "HIGH": return HIGH;
            default: return NONE;
        }
    }

    public int getType() {
        return type;
    }

    @JsonValue
    public String getName() {
        return toString();
    }
}
