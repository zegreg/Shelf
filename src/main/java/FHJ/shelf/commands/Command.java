package main.java.FHJ.shelf.commands;

import main.java.FHJ.shelf.commands.exceptions.CommandException;

/**
 * Contract to be supported by all commands. Instances cannot be executed multiple times.
 */
public interface Command {
	
	
	/**
	 * Executes the instance.
	 */
	void execute() throws CommandException;
}

