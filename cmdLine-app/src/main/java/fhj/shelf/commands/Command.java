package fhj.shelf.commands;

import fhj.shelf.commands.exceptions.CommandException;

/**
 * Contract to be supported by all commands. Instances cannot be executed multiple times.
 */
public interface Command {
	
	
	/**
	 * Executes the instance.
	 * @throws OptionalParameterNotPresentException 
	 * @throws ElementNotAddedToShelfException 
	 */
	void execute() throws CommandException;
}

