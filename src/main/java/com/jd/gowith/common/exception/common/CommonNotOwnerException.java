package com.jd.gowith.common.exception.common;

public class CommonNotOwnerException extends RuntimeException {

	private static final long serialVersionUID = 2241549550934267615L;

	public CommonNotOwnerException(String msg, Throwable t) {
		super(msg, t);
	}

	public CommonNotOwnerException(String msg) {
		super(msg);
	}

	public CommonNotOwnerException() {
		super();
	}
}
