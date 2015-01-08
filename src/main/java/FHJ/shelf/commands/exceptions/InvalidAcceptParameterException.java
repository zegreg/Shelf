package main.java.FHJ.shelf.commands.exceptions;

import java.text.MessageFormat;

/**
 * This class verifies if parameter values are not valid, throwing an exception
 *  that will be analyzed.
 *
 *@authors Hugo Leal, José Oliveira, Filipa Estiveira
 */
@SuppressWarnings("serial")
public class InvalidAcceptParameterException extends CommandException {

	/**
     * Initiates an instance bearing the name of the missing accept parameter
     * 
     * @param parameterName the name of the mandatory parameter which is missing
     */
	public InvalidAcceptParameterException(String parameterName) {
		super(
				MessageFormat
						.format("Accept parameter with invalid name ( valid--> txt/plain, txt/html, application/json )",
								parameterName));
	}

	/**
     * Initiates an instance with the given message and cause
     * 
     * @param message the exception's message
     * @param cause the original error
     */
	public InvalidAcceptParameterException(String message, Throwable cause) {
		super(message, cause);
	}
}
