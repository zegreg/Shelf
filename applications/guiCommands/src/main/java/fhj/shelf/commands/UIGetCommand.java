package fhj.shelf.commands;

import java.util.Map;

/**
 * Contract to be supported by all GET commands of user's interface
 * applications.
 * 
 * @author Filipa Estiveira, Hugo Leal, José Oliveira
 */
public interface UIGetCommand extends UICommand {

	/**
	 * Executes the instance
	 * @return a map with the result of command execution
	 * @throws NumberFormatException
	 * @throws Exception
	 */
	public Map<String, String> execute() throws NumberFormatException,
			Exception;
}
