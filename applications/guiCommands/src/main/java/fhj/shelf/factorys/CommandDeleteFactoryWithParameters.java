package fhj.shelf.factorys;

import java.util.Map;

import fhj.shelf.commands.UIDeleteCommand;

/**
 * Contract to be supported by all all user interface's applications DELETE
 * commands factories that don't need to receive parameters.
 * 
 * @author Filipa Estiveira, Hugo Leal, José Oliveira
 */
public interface CommandDeleteFactoryWithParameters extends CommandFactory {

	/**
	 * Creates a instance of an UIDeleteCommand with the given parameters
	 * 
	 * @return UIDeleteCommand
	 */
	UIDeleteCommand newInstance(Map<String, String> parameters);

}

