package com.backend.apiserver.exception;

public class ForbiddenException extends Exception {

    public ForbiddenException() {
    }

    public ForbiddenException(final String message) {
        super(message);
    }

}
