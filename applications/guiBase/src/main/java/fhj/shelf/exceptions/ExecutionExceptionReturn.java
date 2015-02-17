package fhj.shelf.exceptions;

import java.text.MessageFormat;

@SuppressWarnings("serial")
public class ExecutionExceptionReturn extends ExceptionsGUI{

	public ExecutionExceptionReturn(String message) {
		super(MessageFormat
				.format("Database unable to execution {0}.", message));
		
	}

	
	/**
	 * Initiates an instance with the given message and cause
	 * 
	 * @param message
	 *            the exception's message
	 * @param cause
	 *            the original error
	 */
	public ExecutionExceptionReturn(String message, Throwable cause) {
		super(message, cause);
	}

}
