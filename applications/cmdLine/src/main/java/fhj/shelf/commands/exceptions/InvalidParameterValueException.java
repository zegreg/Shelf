package fhj.shelf.commands.exceptions;

import java.text.MessageFormat;

/**
 * Exception thrown whenever a given command parameter value is invalid 
 */
@SuppressWarnings("serial")
public class InvalidParameterValueException extends CommandException {

	
	/**
     * Initiates an instance bearing information regarding the invalid parameter 
     * 
     * @param name the name of the invalid parameter
     * @param value the value considered invalid
     */
	public InvalidParameterValueException(String name, String value) {
		super(MessageFormat.format("Demanding parameter with name {0} has invalid value {1}.", name, value));
	}


	/**
     * Initiates an instance with the given message and cause
     * 
     * @param message the exception's message
     * @param cause the original error
     */
	public InvalidParameterValueException(String message, Throwable cause) {
		super(message, cause);
	}

}
