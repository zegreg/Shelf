package fhj.shelf.clientCommand;

import java.util.concurrent.ExecutionException;

public interface ClientCommand {

	
	public Object execute() throws InterruptedException, ExecutionException, Exception;
}
