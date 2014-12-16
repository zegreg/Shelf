package test.java.FHJ.shelf.commands;

import test.java.FHJ.shelf.model.User;
import main.java.FHJ.shelf.commandParser.CommandParser;
import main.java.FHJ.shelf.commandParser.DuplicateArgumentsException;
import main.java.FHJ.shelf.commandParser.InvalidCommandArgumentsException;
import main.java.FHJ.shelf.commandParser.InvalidRegisterException;
import main.java.FHJ.shelf.commandParser.UnknownCommandException;
import main.java.FHJ.shelf.commands.GetUsers;
import main.java.FHJ.shelf.commands.PostUser;
import main.java.FHJ.shelf.commands.exceptions.CommandException;
import main.java.FHJ.shelf.model.repos.InMemoryUserRepository;
import main.java.FHJ.shelf.model.repos.UserRepository;

public class AppGetUser {

	public static void main(String[] args) throws InvalidRegisterException, UnknownCommandException, DuplicateArgumentsException, InvalidCommandArgumentsException, CommandException {
		
		// Cria um CommandParser
		CommandParser parser = new CommandParser();
		
		UserRepository userrepository = new InMemoryUserRepository();
		
		User user = new User("Paulo", "000", "vjvjfv", "JGGO");
		userrepository.insert(user);
		
		
		parser.registerCommand("POST",
				new StringBuilder("/User/").toString(),				
				new PostUser.Factory(userrepository));
		
		parser.getCommand("POST", "/User/","username=jose&password=6786&email=j@hotmail.com&fullname=JGGO").execute();
		
		
		
		parser.registerCommand("POST",
				new StringBuilder("/User/").toString(),				
				new PostUser.Factory(userrepository));
		
		parser.getCommand("POST", "/User/","username=jose&password=6786&email=j@hotmail.com&fullname=JGGO").execute();
		
		
		
	
		parser.registerCommand("Get", "/Users/", new GetUsers.Factory(userrepository));
		parser.getCommand("Get", "/Users/").execute();
		
	}

}
