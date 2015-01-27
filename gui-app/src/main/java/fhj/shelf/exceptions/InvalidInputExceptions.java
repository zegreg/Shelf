package fhj.shelf.exceptions;

@SuppressWarnings("serial")
public class InvalidInputExceptions extends Exception {

	public InvalidInputExceptions() {}

	public InvalidInputExceptions(String message)
	{
		super(message);
	}

	public InvalidInputExceptions(String message, Throwable cause)
	{
		super(message, cause);
	}
}

