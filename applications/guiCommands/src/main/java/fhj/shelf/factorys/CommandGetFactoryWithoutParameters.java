package fhj.shelf.factorys;

import fhj.shelf.commands.UIGetCommand;

/**
 * Contract to be supported by all all user interface's applications GET
 * commands factories that don't need to receive parameters.
 * 
 * @author Filipa Estiveira, Hugo Leal, José Oliveira
 */
public interface CommandGetFactoryWithoutParameters extends CommandFactory {

	/**
	 * Creates a instance of an UIGetCommand
	 * 
	 * @return UIGetCommand
	 */
	UIGetCommand newInstance();
}
