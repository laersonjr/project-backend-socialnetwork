package com.sysmap.laersonjr.socialnetwork.domain.exception;

public class CommentNotFoundException extends RuntimeException {

    private static final String ERROR_MESSAGE = "Comment not found.";

    public CommentNotFoundException() {
        super((ERROR_MESSAGE));
    }

}
