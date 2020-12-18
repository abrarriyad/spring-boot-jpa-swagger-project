package com.rashid.abrar.exception;
import static com.rashid.abrar.util.Constants.*;
public class AuthorNotFoundException extends RuntimeException {

    public AuthorNotFoundException() {
        super(NO_AUTHOR_FOUND_EXCEPTION);
    }
}
