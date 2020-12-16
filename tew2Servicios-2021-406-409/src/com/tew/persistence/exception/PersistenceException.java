package com.tew.persistence.exception;

public class PersistenceException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5491523613989622636L;

	public PersistenceException() {
		super();
	}

	public PersistenceException(String message, Throwable cause) {
		super(message, cause);
	}

	public PersistenceException(String message) {
		super(message);
	}

	public PersistenceException(Throwable cause) {
		super(cause);
	}

}
