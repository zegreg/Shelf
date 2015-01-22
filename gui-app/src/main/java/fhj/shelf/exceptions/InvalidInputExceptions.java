package fhj.shelf.exceptions;

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

