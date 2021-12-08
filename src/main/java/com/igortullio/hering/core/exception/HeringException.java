package com.igortullio.hering.core.exception;

public class HeringException extends RuntimeException {

    private static final long serialVersionUID = 1995424658816447286L;

    public HeringException(String message) {
        super(message);
    }

    public HeringException(String message, Throwable cause) {
        super(message, cause);
    }

}
