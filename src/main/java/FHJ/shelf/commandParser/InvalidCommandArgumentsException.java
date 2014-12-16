package main.java.FHJ.shelf.commandParser;

/**
 * Class whose instances represent parser errors that result from trying to  
 * parse a command which has an illegal syntax. 
 * 
 * TODO: elaborate
 */
@SuppressWarnings("serial")
public class InvalidCommandArgumentsException extends CommandParserException 
{
	public InvalidCommandArgumentsException() {
	}

	public InvalidCommandArgumentsException(String message) {
		super(message);
	}


	public InvalidCommandArgumentsException(String message, Throwable cause) {
		super(message, cause);
	}
 
}
