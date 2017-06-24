package com.quincy.core.exception;

public class QuincyException extends RuntimeException {
	private static final long serialVersionUID = -7649882477203274043L;

	public QuincyException() {
		super();
	}

	public QuincyException(String msg) {
		super(msg);
	}
}
