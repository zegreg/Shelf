package fhj.shelf.factorys;

import java.util.Map;

import fhj.shelf.commands.UIGetCommand;

public interface CommandGetFactoryWithParameters extends CommandFactory {

	UIGetCommand newInstance(Map<String, String> parameters);
}
