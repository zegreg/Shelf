package fhj.shelf.exceptions;

import java.text.MessageFormat;

@SuppressWarnings("serial")
public class ExecutionCommunicationException extends ExceptionsClientServer {

	public ExecutionCommunicationException(String message) {
		super(MessageFormat
				.format("Communication with name {0}, unable.", message));
	
	}

	
	
	
	

}
