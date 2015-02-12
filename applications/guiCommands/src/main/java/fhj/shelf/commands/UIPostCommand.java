package fhj.shelf.commands;

/**
 * Contract to be supported by all POST commands of user's interface
 * applications.
 * 
 *@author Filipa Estiveira, Hugo Leal, José Oliveira
 */
public interface UIPostCommand extends UICommand {
	
	/**
	 * Executes the instance
	 * @return a map with the result of command execution
	 * @throws NumberFormatException
	 * @throws Exception
	 */
	public String execute() throws NumberFormatException, Exception;
}