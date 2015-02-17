package fhj.shelf.exceptions;

import java.text.MessageFormat;

@SuppressWarnings("serial")
public class ExecutionCommunicationException extends ExceptionsClientServer {

	public ExecutionCommunicationException(String message) {
		super(MessageFormat
				.format("Building path problems on {0}", message));
	
	}

	
	
	
	

}
