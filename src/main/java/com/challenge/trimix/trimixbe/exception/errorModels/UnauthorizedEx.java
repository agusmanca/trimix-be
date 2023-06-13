package com.challenge.trimix.trimixbe.exception.errorModels;

public class UnauthorizedEx extends Exception {
    public static final String DESCRIPTION = "Unauthorized Access:";

    public UnauthorizedEx() {
        super(DESCRIPTION);
    }

    public UnauthorizedEx(String detail) {
        super(DESCRIPTION + " " + detail);
    }
}
