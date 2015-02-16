package fhj.shelf.actionWindowFactory;


import java.util.Map;

import fhj.shelf.actionWindow.PostActionWindow;
import fhj.shelf.factorys.CommandFactory;

public interface PostActionWindowFactory {

	PostActionWindow newInstance(String username, String password,Map<String, CommandFactory> mapCommands);
	
	
}
