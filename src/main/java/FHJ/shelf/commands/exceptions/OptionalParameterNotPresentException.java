package main.java.FHJ.shelf.commands.exceptions;

import java.text.MessageFormat;

@SuppressWarnings("serial")
public class OptionalParameterNotPresentException extends Exception {

	public OptionalParameterNotPresentException(String parameterName) {
		super(MessageFormat.format("Optional parameter with name {0} not present.", parameterName));
	}



	public OptionalParameterNotPresentException(String message, Throwable cause) {
		super(message, cause);
	}
}
