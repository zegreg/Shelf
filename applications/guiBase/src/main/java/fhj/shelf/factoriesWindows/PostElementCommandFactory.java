package fhj.shelf.factoriesWindows;

import java.util.Map;

import fhj.shelf.commandsFactory.PostElementGUI;

import fhj.shelf.factorys.CommandFactory;

public interface PostElementCommandFactory extends CommandFactory {

	PostElementGUI newInstance(String username, String password,Map<String, CommandFactory> mapCommands);
	
	
}
