package ifusion.mjml.exception;

import java.time.LocalDateTime;

public class MJMLBadRequestException extends MJMLClientErrorException {

    public MJMLBadRequestException(String message, String requestID, LocalDateTime requestStart) {
        super(message, requestID, requestStart);
    }

}
