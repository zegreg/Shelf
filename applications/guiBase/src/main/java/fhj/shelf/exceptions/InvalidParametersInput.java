package fhj.shelf.exceptions;

import java.text.MessageFormat;

@SuppressWarnings("serial")
public class InvalidParametersInput extends ExceptionsGUI {

	

	public InvalidParametersInput(String message) {
		super(MessageFormat
				.format("Input parameter with name {0} not present.", message));
	
	}
	
	
	/**
	 * Initiates an instance with the given message and cause
	 * 
	 * @param message
	 *            the exception's message
	 * @param cause
	 *            the original error
	 */
	public InvalidParametersInput(String message, Throwable cause) {
		super(message, cause);
	}


}
