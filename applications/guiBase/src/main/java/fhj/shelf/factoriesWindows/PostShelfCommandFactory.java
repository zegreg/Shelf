package fhj.shelf.factoriesWindows;

import java.util.Map;

import fhj.shelf.commandsFactory.PostShelfGUI;
import fhj.shelf.commandsFactory.PostUserGUI;
import fhj.shelf.factorys.CommandFactory;

public interface PostShelfCommandFactory {

	PostShelfGUI newInstance(String username, String password,Map<String, CommandFactory> mapCommands);
}
