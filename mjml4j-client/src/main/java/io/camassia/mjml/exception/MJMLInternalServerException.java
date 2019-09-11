package io.camassia.mjml.exception;

import java.time.LocalDateTime;

public class MJMLInternalServerException extends MJMLServerErrorException {

    public MJMLInternalServerException(String message, String requestID, LocalDateTime requestStart) {
        super(message, requestID, requestStart);
    }

}
