package CommandParser;

/**
 * Class whose instances represent parser errors that result from trying to obtain 
 * an unknown command. 
 * 
 * TODO: elaborate
 */
@SuppressWarnings("serial")
public class UnknownCommandException extends CommandParserException 
{
	public UnknownCommandException() {
	}

	public UnknownCommandException(String message) {
		super(message);
	}


	public UnknownCommandException(String message, Throwable cause) {
		super(message, cause);
	}
 
}
