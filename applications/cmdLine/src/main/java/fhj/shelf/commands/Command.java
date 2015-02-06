package fhj.shelf.commands;

import java.util.concurrent.ExecutionException;

import fhj.shelf.commands.exceptions.CommandException;
import fhj.shelf.output.StackMensage;

/**
 * Contract to be supported by all commands. Instances cannot be executed multiple times.
 */
public interface Command {
	
	
	/**
	 * Executes the instance.
	 * @throws ExecutionException 
	 * @throws IllegalArgumentException 
	 * @throws OptionalParameterNotPresentException 
	 * @throws ElementNotAddedToShelfException 
	 */
	String execute() throws CommandException, IllegalArgumentException, ExecutionException;
}

