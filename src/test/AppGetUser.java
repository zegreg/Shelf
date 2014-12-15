package test;

import CommandParser.CommandParser;
import CommandParser.DuplicateArgumentsException;
import CommandParser.InvalidCommandArgumentsException;
import CommandParser.InvalidRegisterException;
import CommandParser.UnknownCommandException;
import User.InMemoryUserRepository;
import User.User;
import User.UserRepository;
import commads.GetUsers;
import commads.PostUser;
import exceptions.CommandException;

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
