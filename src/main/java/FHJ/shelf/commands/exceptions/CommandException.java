package main.java.FHJ.shelf.commands.exceptions;

/**
 * Base class for {@link Command} exceptions.
 */
@SuppressWarnings("serial")
public class CommandException extends Exception {

	public CommandException() {
	}

	/**
     * Initiates an instance with the given message
     * 
     * @param message the exception's message
     */
	public CommandException(String message) {
		super(message);
	}


	/**
     * Initiates an instance with the given message and cause
     * 
     * @param message the exception's message
     * @param cause the original error
     */
	public CommandException(String message, Throwable cause) {
		super(message, cause);
	}
}
