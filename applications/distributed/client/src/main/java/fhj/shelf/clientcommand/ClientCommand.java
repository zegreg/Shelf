package fhj.shelf.clientcommand;

import java.util.concurrent.ExecutionException;

import fhj.shelf.exceptions.ExceptionsClientServer;




public interface ClientCommand {

	
	public Object execute() throws ExceptionsClientServer;
	
	
	
}
