package io.camassia.mjml.exception;

import java.time.LocalDateTime;

public class MJMLUnauthorizedException extends MJMLClientErrorException {

    public MJMLUnauthorizedException(String message, String requestID, LocalDateTime requestStart) {
        super(message, requestID, requestStart);
    }

}
