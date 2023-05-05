package com.sysmap.laersonjr.socialnetwork.domain.exception;

public class UserNotFoundException extends RuntimeException {

    private static final String ERROR_MESSAGE = "Usuário not found.";

    public UserNotFoundException() {
        super((ERROR_MESSAGE));
    }

}
