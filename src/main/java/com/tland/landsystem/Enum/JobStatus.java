package com.tland.landsystem.Enum;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum JobStatus {
    IN_PROGRESS("In progress"),
    COMPLETED("Completed"),
    PAUSED("Paused");

    private final String value;

    JobStatus(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static JobStatus fromString(String value) {
        for (JobStatus status : JobStatus.values()) {
            if (status.value.equalsIgnoreCase(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid JobStatus: " + value);
    }
}
