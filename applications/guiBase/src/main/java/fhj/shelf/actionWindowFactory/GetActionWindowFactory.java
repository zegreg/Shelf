package fhj.shelf.actionWindowFactory;

import java.util.Map;

import fhj.shelf.actionWindow.GetActionWindow;
import fhj.shelf.factorys.CommandFactory;

public interface GetActionWindowFactory extends CommandFactory {

	GetActionWindow newInstance(Map<String, CommandFactory> mapCommands);
}
