package fhj.shelf.exceptions;

import java.text.MessageFormat;

@SuppressWarnings("serial")
public class InterruptedCommunicationException extends InterruptedException {

	public InterruptedCommunicationException(String message) {
		super(MessageFormat
				.format("Communication with name {0}, unable.", message));
	
	}

	
	
	
	

}
