package com.tew.persistence.exception;

public class AlreadyPersistedException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3179223221111056088L;

	public AlreadyPersistedException() {
	}

	public AlreadyPersistedException(String message) {
		super(message);
	}

	public AlreadyPersistedException(Throwable cause) {
		super(cause);
	}

	public AlreadyPersistedException(String message, Throwable cause) {
		super(message, cause);
	}
}
