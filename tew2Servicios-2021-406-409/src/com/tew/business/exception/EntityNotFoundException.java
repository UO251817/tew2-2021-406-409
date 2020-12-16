package com.tew.business.exception;

public class EntityNotFoundException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6619661094870982628L;

	public EntityNotFoundException() {
	}

	public EntityNotFoundException(String message) {
		super(message);
	}

	public EntityNotFoundException(Throwable cause) {
		super(cause);
	}

	public EntityNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
