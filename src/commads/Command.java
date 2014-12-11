package commads;

import exceptions.CommandException;

/**
 * Contract to be supported by all commands. Instances cannot be executed multiple times.
 */
public interface Command {
	
	
	/**
	 * Executes the instance.
	 */
	void execute() throws CommandException;
}

