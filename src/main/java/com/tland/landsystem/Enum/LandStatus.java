package com.tland.landsystem.Enum;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum LandStatus {
    UNUSED("Unused"),
    IN_USE("In use"),
    NEEDS_INSPECTION("Needs inspection"),
    UNDER_CONSTRUCTION("Under construction"),
    SOLD("Sold");

    private final String value;

    LandStatus(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static LandStatus fromValue(String value) {
        for (LandStatus status : values()) {
            if (status.value.equalsIgnoreCase(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid LandStatus: " + value);
    }

}
