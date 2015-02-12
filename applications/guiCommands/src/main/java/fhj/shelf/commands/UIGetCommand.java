package fhj.shelf.commands;

import java.util.Map;

public interface UIGetCommand extends UICommand {

	public Map<String, String> execute() throws NumberFormatException,
	Exception;
}
