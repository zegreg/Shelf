package App;

import User.InMemoryUserRepository;
import User.User;
import User.UserRepository;
import afterSOLIDrevisionEHL.model.CD;
import afterSOLIDrevisionEHL.model.DVD;
import afterSOLIDrevisionEHL.model.Shelf;
import commads.GetElement;
import commads.GetShelfDetails;
import commads.GetUser;
import commads.PostElement;
import commads.GetShelf;
import commads.PostShelf;
import exceptions.CommandException;
import CommandParser.CommandParser;
import CommandParser.DuplicateArgumentsException;
import CommandParser.InvalidCommandArgumentsException;
import CommandParser.InvalidRegisterException;
import CommandParser.UnknownCommandException;
import Database.InMemoryShelfRepository;
import Database.ShelfRepository;


public class AppShelf2 {

	public static void main(String[] args) throws InvalidRegisterException,
	UnknownCommandException, DuplicateArgumentsException, InvalidCommandArgumentsException, CommandException {
		
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
//		parser.registerCommand("POST", "/Shelf", new PostShelf.Factory(productRepo));
//		parser.getCommand("POST", "/Shelf").execute();
		
		
//		parser.registerCommand("Get", "/Shelf", new GetShelf.Factory(productRepo));
//		parser.getCommand("Get", "/Shelf").execute();
	
		/*
		Shelf shelf1 = (Shelf) productRepo.getProductById(2);
		
		shelf1.add(new CD("CD", 1));
		
//		productRepo.insert(shelf1);
		parser.registerCommand("POST", "/Shelf", new PostElement.Factory(productRepo));
//		parser.getCommand("POST", "/Shelf").execute();
		
		parser.registerCommand("Get", "/Shelf", new GetElement.Factory(productRepo));
		parser.getCommand("Get", "/Shelf").execute();
		
		
		UserRepository userRepository = new InMemoryUserRepository();
		userRepository.insert(new User("Jos�", "6676", "j@gmail.com", "Jose Oliveira"));
		parser.registerCommand("Get", "/Shelf", new GetUser.Factory(userRepository));
		parser.getCommand("Get", "/Shelf").execute();
		*/
		parser.registerCommand("GET", 
				new StringBuilder("/shelfs/{").append(GetShelfDetails.ID).append("}").append("/details").toString(), 
				new GetShelfDetails.Factory(productRepo));
		
		parser.getCommand("GET", "/shelfs/2/details").execute();
	}

}
