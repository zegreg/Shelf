package fhj.shelf.exceptions;

import java.text.MessageFormat;


@SuppressWarnings("serial")
public class ExecutionUrlClientServer extends ExceptionsClientServer {

	public  ExecutionUrlClientServer(String message) {
		super(MessageFormat
				.format("Url connection problem on {0}", message));
	
	}

	
	

}
