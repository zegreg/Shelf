package fhj.shelf.exceptions;

import java.text.MessageFormat;

@SuppressWarnings("serial")
public class PostHandlerExceptions extends ExceptionsGUI{
	
	public PostHandlerExceptions(String message) {
		super(MessageFormat
				.format("Problems occured in thread {0}.", message));
	
	}
	
	
	/**
	 * Initiates an instance with the given message and cause
	 * 
	 * @param message
	 *            the exception's message
	 * @param cause
	 *            the original error
	 */
	public PostHandlerExceptions(String message, Throwable cause) {
		super(message, cause);
	}
}
