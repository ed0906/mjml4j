package io.camassia.mjml.exception;

import java.time.LocalDateTime;

public class MJMLForbiddenException extends MJMLClientErrorException {

    public MJMLForbiddenException(String message, String requestID, LocalDateTime requestStart) {
        super(message, requestID, requestStart);
    }

}
