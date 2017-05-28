package ifusion.mjml.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import ifusion.mjml.exception.*;

import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ErrorResponse {

    private final String message;
    private final String requestID;
    private final LocalDateTime requestStart;

    public ErrorResponse(
            @JsonProperty("message") String message,
            @JsonProperty("requestID") String requestID,
            @JsonProperty("requestStart") LocalDateTime requestStart) {
        this.message = message;
        this.requestID = requestID;
        this.requestStart = requestStart;
    }

    public String getMessage() {
        return message;
    }

    public String getRequestID() {
        return requestID;
    }

    public LocalDateTime getRequestStart() {
        return requestStart;
    }

    public MJMLException asException(int status) {
        switch (status) {
            case 403:
                return new MJMLForbiddenException(message, requestID, requestStart);
            case 401:
                return new MJMLUnauthorizedException(message, requestID, requestStart);
            case 500:
                return new MJMLInternalServerException(message, requestID, requestStart);
            default:
                return new MJMLBadRequestException(message, requestID, requestStart);

        }
    }

}
