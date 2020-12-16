package com.tew.business.exception;

public class EntityAlreadyExistsException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4551395575776209520L;

	public EntityAlreadyExistsException() {
	}

	public EntityAlreadyExistsException(String message) {
		super(message);
	}

	public EntityAlreadyExistsException(Throwable cause) {
		super(cause);
	}

	public EntityAlreadyExistsException(String message, Throwable cause) {
		super(message, cause);
	}
}
