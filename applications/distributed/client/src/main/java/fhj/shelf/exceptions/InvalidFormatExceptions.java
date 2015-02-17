package fhj.shelf.exceptions;

import java.text.MessageFormat;

public class InvalidFormatExceptions extends ExceptionsClientServer{

	public InvalidFormatExceptions(String message) {
		super(MessageFormat
				.format("Unexpeced format on {0}", message));
	}

}
