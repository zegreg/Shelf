package fhj.shelf.factoriesWindows;

import java.util.Map;

import fhj.shelf.commandsFactory.GetUserGUI;
import fhj.shelf.factorys.CommandFactory;

public interface GetUserCommandFactory extends CommandFactory {

	GetUserGUI newInstance(Map<String, CommandFactory> mapCommands);
}
