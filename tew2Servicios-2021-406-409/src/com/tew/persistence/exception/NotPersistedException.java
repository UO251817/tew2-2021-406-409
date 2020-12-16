package com.tew.persistence.exception;

public class NotPersistedException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -965622047182137462L;

	public NotPersistedException() {
	}

	public NotPersistedException(String message) {
		super(message);
	}

	public NotPersistedException(Throwable cause) {
		super(cause);
	}

	public NotPersistedException(String message, Throwable cause) {
		super(message, cause);
	}
}
