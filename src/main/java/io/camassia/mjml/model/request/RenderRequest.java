package io.camassia.mjml.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class RenderRequest {

    private final String mjml;

    public RenderRequest(String mjml) {
        Objects.requireNonNull(mjml);
        this.mjml = mjml;
    }

    @JsonProperty("mjml")
    public String getMJML() {
        return mjml;
    }

}
