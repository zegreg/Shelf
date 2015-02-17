package fhj.shelf.exceptions;

import java.text.MessageFormat;

@SuppressWarnings("serial")
public class ReaderMessageException extends ExceptionsClientServer {

	public ReaderMessageException(String message) {
		super(MessageFormat
				.format("Problems IO on {0}", message));
	}

}
