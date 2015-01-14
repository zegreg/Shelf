package main.java.FHJ.shelf.commandParser;

/**
 * Class whose instances represent parser errors that result from trying to obtain 
 * an unknown command. 
 * 
 * 
 */
@SuppressWarnings("serial")
public class InvalidRegisterException extends CommandParserException 
{
	public InvalidRegisterException() {
	}

	public InvalidRegisterException(String message) {
		super(message);
	}


	public InvalidRegisterException(String message, Throwable cause) {
		super(message, cause);
	}
 
}
