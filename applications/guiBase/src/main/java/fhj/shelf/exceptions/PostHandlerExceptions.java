package fhj.shelf.exceptions;

import java.text.MessageFormat;

@SuppressWarnings("serial")
public class PostHandlerExceptions extends ExceptionsGUI{
	
	public PostHandlerExceptions(String message) {
		super(MessageFormat
				.format("Method with name {0} not present.", message));
	
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
