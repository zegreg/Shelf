package fhj.shelf.exceptions;

import java.text.MessageFormat;
import java.util.concurrent.ExecutionException;

@SuppressWarnings("serial")
public class ExecutionClientServer extends ExecutionException {

	public  ExecutionClientServer(String message) {
		super(MessageFormat
				.format("Communication with name {0}, unable.", message));
	
	}

	
	

}
