package com.challenge.trimix.trimixbe.exception.errorModels;

public class ForbiddenEx extends Exception {
    public static final String DESCRIPTION = "Forbidden Access:";

    public ForbiddenEx() {
        super(DESCRIPTION);
    }

    public ForbiddenEx(String detail) {
        super(DESCRIPTION + " " + detail);
    }
}
