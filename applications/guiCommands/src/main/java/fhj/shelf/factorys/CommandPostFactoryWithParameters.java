package fhj.shelf.factorys;

import java.util.Map;

import fhj.shelf.commands.UIPostCommand;

public interface CommandPostFactoryWithParameters extends CommandFactory {

	UIPostCommand newInstance(Map<String, String> parameters);

}
