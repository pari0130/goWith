package com.jd.gowith.common.exception.auth;

public class AuthEmailSigninFailedException extends RuntimeException {
    public AuthEmailSigninFailedException(String msg, Throwable t) {
        super(msg, t);
    }

    public AuthEmailSigninFailedException(String msg) {
        super(msg);
    }

    public AuthEmailSigninFailedException() {
        super();
    }
}
