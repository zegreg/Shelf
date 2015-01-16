package fhj.shelf.commands.exceptions;

import java.text.MessageFormat;

/**
 * Exception thrown whenever the required command parameter's are not present 
 */
@SuppressWarnings("serial")
public class DemandingParameterNotPresentException extends CommandException {

	
	/**
     * Initiates an instance bearing the name of the missing mandatory parameter
     * 
     * @param parameterName the name of the mandatory parameter which is missing
     */
	public DemandingParameterNotPresentException(String parameterName) {
		super(MessageFormat.format("Demanding parameter with name {0} not present.", parameterName));
	}


	/**
     * Initiates an instance with the given message and cause
     * 
     * @param message the exception's message
     * @param cause the original error
     */
	public DemandingParameterNotPresentException(String message, Throwable cause) {
		super(message, cause);
	}

}
