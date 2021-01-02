package com.helpdesk.api.dto;

import java.io.Serializable;
import java.time.Instant;


public final class ErrorResponseDTO implements Serializable {

    private static final long serialVersionUID = -6293096295035066550L;

    private final int status;
    private final String error;
    private final String message;
    private final Instant timestamp;

    public ErrorResponseDTO(
            int status,
            String error,
            String message,
            Instant timestamp) {

        this.status = status;
        this.error = error;
        this.message = message;
        this.timestamp = timestamp;

    }

    public int getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

}
