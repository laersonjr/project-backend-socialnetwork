package com.sysmap.laersonjr.socialnetwork.domain.exception;

public class EmailAlreadyExistsException extends RuntimeException {

    private static final String ERROR_MESSAGE = "Email address already in use. Please use a different one.";

    public EmailAlreadyExistsException() {
        super(ERROR_MESSAGE);
    }

}
