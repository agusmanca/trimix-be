package com.challenge.trimix.trimixbe.exception.errorModels;

public class NotFoundEx extends Exception {
    public static final String DESCRIPTION = "Not Found:";

    public NotFoundEx() {
        super(DESCRIPTION);
    }

    public NotFoundEx(String detail) {
        super(DESCRIPTION + " " + detail);
    }
}
