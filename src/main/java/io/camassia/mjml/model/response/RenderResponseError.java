package io.camassia.mjml.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RenderResponseError {

    private final String message;
    private final String tagName;
    private final String formattedMessage;
    private final Integer lineNumber;

    public RenderResponseError(@JsonProperty("message") String message,
                               @JsonProperty("tagName") String tagName,
                               @JsonProperty("formattedMessage") String formattedMessage,
                               @JsonProperty("line") Integer lineNumber) {
        this.message = message;
        this.tagName = tagName;
        this.formattedMessage = formattedMessage;
        this.lineNumber = lineNumber;
    }

    public String getMessage() {
        return message;
    }

    public String getTagName() {
        return tagName;
    }

    public String getFormattedMessage() {
        return formattedMessage;
    }

    public Integer getLineNumber() {
        return lineNumber;
    }
}
