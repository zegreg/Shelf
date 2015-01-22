package fhj.shelf.commandsDomain.Exceptions;

/**
 * Base class for {@link Command} exceptions.
 */
@SuppressWarnings("serial")
public class CommandDomainException extends Exception {

	public CommandDomainException() {
	}

	/**
	 * Initiates an instance with the given message
	 * 
	 * @param message
	 *            the exception's message
	 */
	public CommandDomainException(String message) {
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
	public CommandDomainException(String message, Throwable cause) {
		super(message, cause);
	}
}
