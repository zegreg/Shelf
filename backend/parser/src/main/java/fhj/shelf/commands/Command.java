package fhj.shelf.commands;

import java.util.concurrent.ExecutionException;

import fhj.shelf.exceptions.CommandException;

/**
 * Contract to be supported by all commands. Instances cannot be executed
 * multiple times.
 */
public interface Command {

	/**
	 * Executes the instance.
	 * 
	 * @throws ExecutionException
	 * @throws IllegalArgumentException
	 * @throws OptionalParameterNotPresentException
	 * @throws ElementNotAddedToShelfException
	 */
	public String execute() throws CommandException, IllegalArgumentException,
			ExecutionException;
}
