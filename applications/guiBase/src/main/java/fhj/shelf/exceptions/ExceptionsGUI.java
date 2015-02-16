package fhj.shelf.exceptions;

@SuppressWarnings("serial")
public class ExceptionsGUI extends Exception{
	
	/**
	 * Initiates an instance with the given message
	 * 
	 * @param message
	 *            the exception's message
	 */
	public ExceptionsGUI(String message) {
		super(message);
	}

	/**
	 * Initiates an instance with the given message and cause
	 * 
	 * @param message
	 *            the exception's message
	 * @param cause
	 *            the original error
	 */
	public ExceptionsGUI(String message, Throwable cause) {
		super(message, cause);
	}
}
