package fhj.shelf.factorys;

import fhj.shelf.commands.UIGetCommand;

public interface CommandGetFactoryWithoutParameters extends CommandFactory {

	UIGetCommand newInstance();
}
