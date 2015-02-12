package fhj.shelf.factorys;

import java.util.Map;

import fhj.shelf.commands.UIPostCommand;

/**
 * Contract to be supported by all all user interface's applications POST
 * commands factories that don't need to receive parameters.
 * 
 * @author Filipa Estiveira, Hugo Leal, José Oliveira
 */
public interface CommandPostFactoryWithParameters extends CommandFactory {

	/**
	 * Creates a instance of an UIPostCommand with the given parameters
	 * 
	 * @return UIPostCommand
	 */
	UIPostCommand newInstance(Map<String, String> parameters);

}
