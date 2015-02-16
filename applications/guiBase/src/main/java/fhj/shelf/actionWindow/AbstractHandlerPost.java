package fhj.shelf.actionWindow;

import java.util.Map;


import fhj.shelf.factorys.CommandFactory;


public abstract class AbstractHandlerPost {
	
	private Map<String, String> params;
	protected  Map<String,CommandFactory> shelfCommands;
	private   String command;

	public AbstractHandlerPost(Map<String, String> params, Map<String, CommandFactory> shelfCommands, String command) {
		this.params = params;
		this.shelfCommands = shelfCommands;
		this.command = command;
	
	}
	
	
}
