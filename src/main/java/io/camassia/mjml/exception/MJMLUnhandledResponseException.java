package io.camassia.mjml.exception;

public class MJMLUnhandledResponseException extends MJMLAPIException {

    private final String body;

    public MJMLUnhandledResponseException(int statusCode, String body) {
        super("Unhandled status code " + statusCode);
        this.body = body;
    }

    public String getBody() {
        return body;
    }
}
