package com.rashid.abrar.exception;
import static com.rashid.abrar.util.Constants.*;

public class BookNotFoundException extends RuntimeException {

    public BookNotFoundException() {
        super(NO_BOOK_FOUND_EXCEPTION);
    }
}
