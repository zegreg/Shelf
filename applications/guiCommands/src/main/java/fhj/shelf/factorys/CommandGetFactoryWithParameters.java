package fhj.shelf.factorys;

import java.util.Map;

import fhj.shelf.commands.UIGetCommand;

/**
 * Contract to be supported by all all user interface's applications GET
 * commands factories that need to receive parameters.
 * 
 * @author Filipa Estiveira, Hugo Leal, José Oliveira
 */
public interface CommandGetFactoryWithParameters extends CommandFactory {

	/**
	 * Creates a instance of an UIGetCommand with the given parameters
	 * 
	 * @return UIGetCommand
	 */
	UIGetCommand newInstance(Map<String, String> parameters);
}
