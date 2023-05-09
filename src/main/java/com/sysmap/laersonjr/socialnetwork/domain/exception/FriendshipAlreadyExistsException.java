package com.sysmap.laersonjr.socialnetwork.domain.exception;

public class FriendshipAlreadyExistsException extends RuntimeException {

    private static final String ERROR_MESSAGE = "Friend request already sent or user is already a friend.";

    public FriendshipAlreadyExistsException() {
        super(ERROR_MESSAGE);
    }

}
