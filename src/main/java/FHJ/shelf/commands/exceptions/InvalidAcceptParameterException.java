package main.java.FHJ.shelf.commands.exceptions;

import java.text.MessageFormat;

@SuppressWarnings("serial")
public class InvalidAcceptParameterException extends CommandException {

	public InvalidAcceptParameterException(String parameterName) {
		super(
				MessageFormat
						.format("Accept parameter with invalid name ( valid--> txt/plain, txt/html, application/json )",
								parameterName));
	}

	public InvalidAcceptParameterException(String message, Throwable cause) {
		super(message, cause);
	}
}
