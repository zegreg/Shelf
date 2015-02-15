package fhj.shelf.factoriesWindows;

import java.util.Map;

import fhj.shelf.commandsFactory.PatchUserGUI;
import fhj.shelf.factorys.CommandFactory;

public interface PatchUserCommandFactory {

	PatchUserGUI newInstance(String username, String password,Map<String, CommandFactory> mapCommands);
	
}
