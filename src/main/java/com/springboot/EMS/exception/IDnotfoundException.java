package com.springboot.EMS.exception;

public class IDnotfoundException extends Exception {
	private static final long serialVersionUID = 1L;
	public IDnotfoundException(String message) {
        super(message);
}
}