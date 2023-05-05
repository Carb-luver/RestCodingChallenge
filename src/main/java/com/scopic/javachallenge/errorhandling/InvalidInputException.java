package com.scopic.javachallenge.errorhandling;

public class InvalidInputException extends Exception{

	private static final long serialVersionUID = 1L;

	public InvalidInputException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidInputException(String message) {
		super(message);
	}
		
}
