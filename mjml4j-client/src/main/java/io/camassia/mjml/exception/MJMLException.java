package io.camassia.mjml.exception;

public abstract class MJMLException extends RuntimeException {

    public MJMLException(String message) {
        super(message);
    }

    public MJMLException(Throwable e) {
        super(e);
    }

}
