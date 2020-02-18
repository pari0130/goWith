package com.jd.gowith.common.exception.common;

public class CommonResourceNotExistException extends RuntimeException {
    public CommonResourceNotExistException(String msg, Throwable t) {
        super(msg, t);
    }

    public CommonResourceNotExistException(String msg) {
        super(msg);
    }

    public CommonResourceNotExistException() {
        super();
    }
}
