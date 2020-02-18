package com.jd.gowith.common.exception.common;

public class CommonCommunicationException extends RuntimeException {
    public CommonCommunicationException(String msg, Throwable t) {
        super(msg, t);
    }

    public CommonCommunicationException(String msg) {
        super(msg);
    }

    public CommonCommunicationException() {
        super();
    }
}
