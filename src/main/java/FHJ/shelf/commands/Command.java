package main.java.FHJ.shelf.commands;

import main.java.FHJ.shelf.commands.exceptions.CommandException;
import main.java.FHJ.shelf.commands.exceptions.OptionalParameterNotPresentException;

/**
 * Contract to be supported by all commands. Instances cannot be executed multiple times.
 */
public interface Command {
	
	
	/**
	 * Executes the instance.
	 * @throws OptionalParameterNotPresentException 
	 */
	void execute() throws CommandException, OptionalParameterNotPresentException;
}

