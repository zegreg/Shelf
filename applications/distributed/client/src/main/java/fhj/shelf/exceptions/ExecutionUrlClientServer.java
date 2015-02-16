package fhj.shelf.exceptions;

import java.text.MessageFormat;


@SuppressWarnings("serial")
public class ExecutionUrlClientServer extends ExceptionsClientServer {

	public  ExecutionUrlClientServer(String message) {
		super(MessageFormat
				.format("Communication with name {0}, unable.", message));
	
	}

	
	

}
