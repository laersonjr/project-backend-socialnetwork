package com.sysmap.laersonjr.socialnetwork.domain.exception;

public class ForbiddenActionException extends RuntimeException{

    private static final String FORBIDDEN_ERROR_MESSAGE = "You are not authorized to make this action.";

    public ForbiddenActionException(){
        super(FORBIDDEN_ERROR_MESSAGE);
    }

}
