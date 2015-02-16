package fhj.shelf.actionwindowfactory;


import java.util.Map;

import fhj.shelf.actionwindow.PostActionWindow;
import fhj.shelf.factorys.CommandFactory;

public interface PostActionWindowFactory {

	PostActionWindow newInstance(String username, String password,Map<String, CommandFactory> mapCommands);
	
	
}
