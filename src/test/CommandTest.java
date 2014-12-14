package test;

import static org.junit.Assert.*;

import org.junit.Test;

import User.InMemoryUserRepository;
import User.User;
import User.UserRepository;
import commads.GetShelf;
import commads.GetShelfElement;
import commads.GetShelfElements;
import commads.GetUsers;
import commads.PostShelf;
import commads.PostUser;
import CommandParser.CommandParser;
import CommandParser.CommandParserException;
import Database.ElementsRepository;
import Database.InMemoryElementsRepository;
import Database.InMemoryShelfRepository;
import Database.ShelfRepository;

public class CommandTest {


	/**
	 * Create a CommandParser
	 */
	CommandParser parser = new CommandParser();

	/**
	 * Creates Repository
	 */
	ShelfRepository shelfRepo = new InMemoryShelfRepository();
	ElementsRepository elementsRepo = new InMemoryElementsRepository();
	UserRepository userrepository = new InMemoryUserRepository();
	
		@Test
		public void shouldPostAUserCommand() throws CommandParserException
		{
			User user = new User("Paulo", "000", "vjvjfv", "JGGO");
			userrepository.insert(user);
			
			
			parser.registerCommand("POST",
					new StringBuilder("/User/").toString(),				
					new PostUser.Factory(userrepository));
			
			
			
			assertTrue(parser.getCommand("POST", "/User/","username=jose&password=6786&email=j@hotmail.com&fullname=JGGO")
					instanceof PostUser);
		}
		
		@Test
		public void shouldReturnAGetUserCommand() throws CommandParserException
		{

			parser.registerCommand("Get", "/Users/", new GetUsers.Factory(userrepository));
			
			assertTrue(parser.getCommand("Get", "/Users/") instanceof GetUsers);
		}
		
		
		@Test
		public void shouldPostAShelfCommand() throws CommandParserException {
			parser.registerCommand("POST", "/Shelf", new PostShelf.Factory(shelfRepo));
		
			assertTrue(	parser.getCommand("POST", "/Shelf") instanceof PostShelf);
		}
		
		@Test
		public void shouldReturnAGetShelfCommand() throws CommandParserException
		{
			parser.registerCommand("Get", "/Shelf", new GetShelf.Factory(shelfRepo));
			
			assertTrue(parser.getCommand("Get", "/Shelf") instanceof GetShelf);
		}
		
	
		@Test
		public void shouldReturnAGetShelfElementsCommand() throws CommandParserException
		{
			parser.registerCommand("Get",
					new StringBuilder("/Shelf/{").append(GetShelfElements.SID).append("}").append("/elements").toString(), 
					new GetShelfElements.Factory(shelfRepo));
					
			assertTrue(parser.getCommand("Get", "/Shelf/0/elements")  instanceof GetShelfElements);
		}
		
		@Test
		public void shouldReturnAGetShelfElementCommand() throws CommandParserException
		{
			//  Executa o comando Get/shelfs/{sid}/elements/{eid}
			parser.registerCommand("GET", 
					new StringBuilder("/shelfs/{").append(GetShelfElement.SID).append("}").append("/elements/{")
					.append(GetShelfElement.EID).append("}").toString(), 
					new GetShelfElement.Factory(shelfRepo, elementsRepo));
			
			
		
			assertTrue(parser.getCommand("GET", "/shelfs/1/elements/1") instanceof GetShelfElement);
		}
		

}
