package App;

import afterSOLIDrevisionEHL.model.CD;
import afterSOLIDrevisionEHL.model.DVD;
import commads.GetShelf;
import CommandParser.CommandParser;
import CommandParser.DuplicateArgumentsException;
import CommandParser.InvalidCommandArgumentsException;
import CommandParser.InvalidRegisterException;
import CommandParser.UnknownCommandException;
import Database.InMemoryShelfRepository;
import Database.ShelfRepository;


public class AppShelf {

	public static void main(String[] args) throws InvalidRegisterException,
	UnknownCommandException, DuplicateArgumentsException, InvalidCommandArgumentsException {
		CommandParser parser = new CommandParser();
		ShelfRepository productRepo = new InMemoryShelfRepository();
		productRepo.insert(new CD("Moon walk", 4, 1));
		productRepo.insert(new DVD("Jay walking in India",1, 2));

		parser.registerCommand("GET", "/CD", new GetShelf.Factory(productRepo));

		parser.getCommand("GET", "/CD").execute();
	}

}
