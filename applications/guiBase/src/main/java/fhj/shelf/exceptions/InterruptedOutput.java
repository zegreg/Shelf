package fhj.shelf.exceptions;

import java.text.MessageFormat;

@SuppressWarnings("serial")
public class InterruptedOutput extends ExceptionsGUI{

	public InterruptedOutput(String message) {
		super(MessageFormat
				.format("Database Result with name {0}, unable.", message));
		
	}

	
	/**
	 * Initiates an instance with the given message and cause
	 * 
	 * @param message
	 *            the exception's message
	 * @param cause
	 *            the original error
	 */
	public InterruptedOutput(String message, Throwable cause) {
		super(message, cause);
	}

}
