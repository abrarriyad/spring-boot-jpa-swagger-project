package com.rashid.abrar.exception;
import static com.rashid.abrar.util.Constants.ILLEGAL_AUTHOR_EXCEPTION;

public class IllegalAuthorException extends RuntimeException {

    public IllegalAuthorException() {
        super(ILLEGAL_AUTHOR_EXCEPTION);
    }
}
