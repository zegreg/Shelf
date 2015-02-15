package fhj.shelf.factoriesWindows;

import java.util.Map;

import fhj.shelf.commandsFactory.GetShelfGUI;

import fhj.shelf.factorys.CommandFactory;

public interface GetShelfCommandFactory extends  CommandFactory{
	
	GetShelfGUI newInstance(Map<String, CommandFactory> mapCommands);
}
