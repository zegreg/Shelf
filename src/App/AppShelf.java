package App;

import User.InMemoryUserRepository;
import User.User;
import User.UserRepository;
import afterSOLIDrevisionEHL.model.CD;
import afterSOLIDrevisionEHL.model.DVD;
import afterSOLIDrevisionEHL.model.Shelf;
import commads.GetElement;
import commads.GetShelfDetails;
import commads.GetShelfElements;
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


public class AppShelf {

	public static void main(String[] args) throws InvalidRegisterException,
	UnknownCommandException, DuplicateArgumentsException, InvalidCommandArgumentsException, CommandException {
		
		// Criar CommandParser
		CommandParser parser = new CommandParser();
		
		// Criar Repositório
		ShelfRepository productRepo = new InMemoryShelfRepository();
		
		
		// Registar o comando Post Shelf com capacidade nbElements
		
		parser.registerCommand("POST",
				new StringBuilder("/Shelf/{").append(PostShelf.NBELEMENTS).
				append("}").toString(),
				new PostShelf.Factory(productRepo));
		
		
		parser.getCommand("POST", "/Shelf/10").execute();
		
		// Registar Comando Get/Shelf/ID
		
		parser.registerCommand("GET", 
				new StringBuilder("/Shelf/{").append(GetShelfDetails.ID).append("}").append("/details").toString(), 
				new GetShelfDetails.Factory(productRepo));
		
		parser.getCommand("GET", "/Shelf/0/details").execute();
		
		
//		Registar Get/Shelf/
		parser.registerCommand("Get", "/Shelf", new GetShelf.Factory(productRepo));
		parser.getCommand("Get", "/Shelf").execute();

//		Registar Get/Shelf/ID/elements
		
	
		Shelf shelf1 = (Shelf) productRepo.GetElementById(0);
		
		shelf1.add(new CD("CD", 1));
		
		productRepo.insert(shelf1);
		
		parser.registerCommand("Get",
				new StringBuilder("/Shelf/{").append(GetShelfElements.ID).append("}").append("/elements").toString(), 
				new GetShelfElements.Factory(productRepo));
		
		parser.getCommand("Get", "/Shelf/0/elements").execute();
		
		
		
//		productRepo.insert(new Shelf (20));
//		parser.registerCommand("POST", "/Shelf", new PostShelf.Factory(productRepo));
//		parser.getCommand("POST", "/Shelf").execute();
		

//		productRepo.insert(new Shelf (30));
//		parser.registerCommand("POST", "/Shelf", new PostShelf.Factory(productRepo));
//		parser.getCommand("POST", "/Shelf").execute();
		
		
		
	
		
		
		
//		parser.registerCommand("Get", "/Shelf", new GetShelf.Factory(productRepo));
//		parser.getCommand("Get", "/Shelf").execute();
		
		
		
		
//		StringBuilder builder = new StringBuilder();
//		for (String elements : PostElement.DEMANDING_PARAMETERS) {
//			builder.append(elements);
//		}
	
		
		
		
		parser.registerCommand("POST",
				new StringBuilder().append("/Shelfs/{").append(PostElement.SID).append("}")
				.append("/elements/{").append(PostElement.TYPE).append("}/")
				.toString(),				
				new PostElement.Factory(productRepo));
		
		parser.getCommand("POST", "/Shelfs/0/elements/Book/","title=A mae&details=Maximo Gorki").execute();
		
		
		
		
		
//		UserRepository userRepository = new InMemoryUserRepository();
//		userRepository.insert(new User("José", "6676", "j@gmail.com", "Jose Oliveira"));
//		parser.registerCommand("Get", "/Shelf", new GetUser.Factory(userRepository));
//		parser.getCommand("Get", "/Shelf").execute();
		
		
		
		
		
	}

}
