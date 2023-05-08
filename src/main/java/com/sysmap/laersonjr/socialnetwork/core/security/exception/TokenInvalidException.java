package com.sysmap.laersonjr.socialnetwork.core.security.exception;

public class TokenInvalidException extends RuntimeException {

    private static final String TOKEN_INVALID = "Token invalid.";

    public TokenInvalidException(){
        super(TOKEN_INVALID);
    }

}
