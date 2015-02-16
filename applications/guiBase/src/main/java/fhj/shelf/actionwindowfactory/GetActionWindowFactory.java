package fhj.shelf.actionwindowfactory;

import java.util.Map;

import fhj.shelf.actionwindow.GetActionWindow;
import fhj.shelf.factorys.CommandFactory;

public interface GetActionWindowFactory extends CommandFactory {

	GetActionWindow newInstance(Map<String, CommandFactory> mapCommands);
	
}
