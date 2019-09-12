package io.camassia.mjml.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RenderResponse {

    private final List<RenderResponseError> errors;
    private final String html;
    private final String mjml;
    private final String mjmlVersion;

    public RenderResponse(@JsonProperty("errors") List<RenderResponseError> errors,
                          @JsonProperty("html") String html,
                          @JsonProperty("mjml") String mjml,
                          @JsonProperty("mjml_version") String mjmlVersion) {
        this.errors = errors;
        this.html = html;
        this.mjml = mjml;
        this.mjmlVersion = mjmlVersion;
    }

    public List<RenderResponseError> getErrors() {
        return errors;
    }

    public String getHTML() {
        return html;
    }

    public String getMJML() {
        return mjml;
    }

    public String getMJMLVersion() {
        return mjmlVersion;
    }
}
