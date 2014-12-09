package App;

import afterSOLIDrevisionEHL.model.CD;
import afterSOLIDrevisionEHL.model.DVD;
import afterSOLIDrevisionEHL.model.Shelf;
import commads.GetShelf;
import commads.PostShelf;
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
		
		
		productRepo.insert(new Shelf(10));
//		parser.registerCommand("POST", "/Shelf", new PostShelf.Factory(productRepo));
//		parser.getCommand("POST", "/Shelf").execute();
//		
		productRepo.insert(new Shelf (20));
//		parser.registerCommand("POST", "/Shelf", new PostShelf.Factory(productRepo));
//		parser.getCommand("POST", "/Shelf").execute();
		

		productRepo.insert(new Shelf (30));
		parser.registerCommand("POST", "/Shelf", new PostShelf.Factory(productRepo));
		parser.getCommand("POST", "/Shelf").execute();
		
		
		parser.registerCommand("Get", "/Shelf", new GetShelf.Factory(productRepo));
		parser.getCommand("Get", "/Shelf").execute();
		
		
	}

}
