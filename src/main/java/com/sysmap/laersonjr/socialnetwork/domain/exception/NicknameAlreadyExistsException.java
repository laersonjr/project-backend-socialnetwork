package com.sysmap.laersonjr.socialnetwork.domain.exception;

public class NicknameAlreadyExistsException extends RuntimeException {


    private static final String ERROR_MESSAGE = "Nickname already taken. Please choose a different one.";

    public NicknameAlreadyExistsException() {
        super(ERROR_MESSAGE);
    }

}
