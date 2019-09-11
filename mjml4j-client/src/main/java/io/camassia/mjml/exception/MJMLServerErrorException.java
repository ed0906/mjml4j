package io.camassia.mjml.exception;

import java.time.LocalDateTime;

public abstract class MJMLServerErrorException extends MJMLAPIException {

    private final String requestID;
    private final LocalDateTime requestStart;

    MJMLServerErrorException(String message, String requestID, LocalDateTime requestStart) {
        super(message);
        this.requestID = requestID;
        this.requestStart = requestStart;
    }

    public String getRequestID() {
        return requestID;
    }

    public LocalDateTime getRequestStart() {
        return requestStart;
    }

}
