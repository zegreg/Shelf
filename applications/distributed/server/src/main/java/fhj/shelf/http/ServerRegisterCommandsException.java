package fhj.shelf.http;

public class ServerRegisterCommandsException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ServerRegisterCommandsException() {
	}

	/**
	 * Initiates an instance with the given message
	 * 
	 * @param message
	 *            the exception's message
	 */
	public ServerRegisterCommandsException(String message) {
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
	public ServerRegisterCommandsException(String message, Throwable cause) {
		super(message, cause);
	}

}
