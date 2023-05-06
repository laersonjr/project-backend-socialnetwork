package com.sysmap.laersonjr.socialnetwork.domain.exception;

public class PostNotFoundException extends RuntimeException{

    private static final String POST_NOT_FOUND_MESSAGE = "Post not found.";

    public PostNotFoundException(){
        super(POST_NOT_FOUND_MESSAGE);
    }

}
