package main.java.FHJ.shelf.commands;

import java.util.Map;


/**
 * Contract to be supported by all {@link main.java.FHJ.shelf.commands.Command}
 * factories. 
 */
public interface CommandFactory {
	
	public Command newInstance(Map<String,String> parameters);
}
