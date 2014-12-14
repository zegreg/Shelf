package test;

import static org.junit.Assert.*;

import org.junit.Test;

import afterSOLIDrevisionEHL.model.Shelf;
import User.InMemoryUserRepository;
import User.User;
import User.UserRepository;
import commads.BasePostCommand;
import commads.GetShelf;
import commads.GetShelfElement;
import commads.GetShelfElements;
import commads.GetUser;
import commads.GetUsers;
import commads.PostElement;
import commads.PostShelf;
import commads.PostShelfCollectionElement;
import commads.PostUser;
import CommandParser.CommandParser;
import CommandParser.CommandParserException;
import Database.ElementsRepository;
import Database.InMemoryElementsRepository;
import Database.InMemoryShelfRepository;
import Database.ShelfRepository;
/**
 * Class that test CommandParser Class
 * 
 *
 @authors Hugo Leal, José Oliveira, Filipa Estiveira
 */
public class CommandParserTest {


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
	
	
	
	/**
	 * 
	 * Test the {@code PostUser} command.<b> 
	 * This command that belongs to <i> User Management</i>, creates a new user, given the following parameters:
	 * <ul><li>username - unique name of the user;
	 * <li>password - User password;
	 * <li>email - email single user;
	 * </li>fullname - full name (optional)
	 * </ul>
	 * @throws CommandParserException
	 */
		@Test
		public void shouldPostAUserCommand() throws CommandParserException
		{
			
			
			
			parser.registerCommand("POST",
					new StringBuilder("/User/").toString(),				
					new PostUser.Factory(userrepository));
			


			assertTrue(parser.getCommand("POST", "/User/","username=jose&password=6786&email=j@hotmail.com&fullname=JGGO")
					instanceof PostUser);
		}


		
		/**
		 * Test the {@code GetUsers} command.<br> 
		 * This command that belongs to <i> User Management</i>, returns the list of users.
		 * @throws CommandParserException
		 */
		@Test
		public void shouldReturnAGetUsersCommand() throws CommandParserException
		{

			parser.registerCommand("Get", "/Users/", new GetUsers.Factory(userrepository));

			assertTrue(parser.getCommand("Get", "/Users/") instanceof GetUsers);
		}

		

		/**
		 * Test the {@code GetUser} command.<br> 
		 * This command that belongs to <i> User Management</i>, returns the information about the user with a given username.
		 * @throws CommandParserException
		 */
		@Test
		public void shouldReturnAGetUserCommand() throws CommandParserException
		{

			User user = new User("Paulo", "000", "vjvjfv", "JGGO");
			userrepository.insert(user);
			
			parser.registerCommand("GET", "/User", new GetUser.Factory(userrepository));
			
			assertTrue(parser.getCommand("GET", "/User","username=Paulo") instanceof GetUser);
		}
		
		
		
		/**
		 * Test the {@code GetUser} command.<br> 
		 * This command that belongs to <i> User Management</i>, returns the information about the user with a given username.
		 * @throws CommandParserException
		 */
		@Test
		public void shouldPostAShelfCommand() throws CommandParserException {
			
			parser.registerCommand("POST", "/Shelf", new PostShelf.Factory(userrepository, shelfRepo));
		
			assertTrue(	parser.getCommand("POST", "/Shelf","username=jose&password=6786") instanceof PostShelf);
		}
		
		
		
		/**
		 * Test the {@code GetShelf} command.<br> 
		 * This command that belongs to <i> Shelf Management</i>, returns the details of all the shelves of the establishment.
		 * @throws CommandParserException
		 */
		@Test
		public void shouldReturnAGetShelfCommand() throws CommandParserException
		{
			parser.registerCommand("Get", "/shelfs", new GetShelf.Factory(shelfRepo));
			
			assertTrue(parser.getCommand("Get", "/shelfs") instanceof GetShelf);
		}
		
	

		/**
		 * Test the {@code GetShelfElements} command.<br> 
		 * This command that belongs to <i> Shelf Management</i>, returns all elements present in a given shelf id,
		 *  with a clear distinction between elements and collections elements.
		 * @throws CommandParserException
		 */
		@Test
		public void shouldReturnAGetShelfElementsCommand() throws CommandParserException
		{
			parser.registerCommand("Get",
					new StringBuilder("/Shelf/{").append(GetShelfElements.SID).append("}").append("/elements").toString(), 
					new GetShelfElements.Factory(shelfRepo));
					
			assertTrue(parser.getCommand("Get", "/Shelf/0/elements")  instanceof GetShelfElements);
		}
		
		
		
		/**
		 * Test the {@code GetShelfElement} command.<br> 
		 * This command that belongs to <i> Shelf Management</i>, return element id  which it is in a given shelf id.
		 *  with a clear distinction between elements and collections elements. 
		 * @throws CommandParserException
		 */
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
		
		/**
		 * Test the {@code PostShelf} command.<br> 
		 * This command that belongs to <i> Shelf Management</i>, creates a new shelf, with  a given number of elements.<br>
		 * This command returns the shelf identifier (sid).
		 * 
		 * @throws CommandParserException
		 */
		@Test
		public void shouldPostAShelf() throws CommandParserException
		{
			 
			//  Registration command: /shelfs/{sid}/elements/{eid}/{type}
			parser.registerCommand("POST", 
					new StringBuilder("/shelfs/{").append(PostShelf.NBELEMENTS).append("}").toString(), 
					new PostShelf.Factory(userrepository,shelfRepo));
			
			
		
			assertTrue(parser.getCommand("POST", "/shelfs/10") instanceof PostShelf);
		}
		
		/**
		 * Test the {@code PostElement} command.<br> 
		 * This command that belongs to <i> Shelf Management</i>, creates a new element of type type (Collection, book, CD or DVD)
		 *  on the shelf identified by  a given id.
		 * @throws CommandParserException
		 */
		@Test
		public void shouldPostAElement() throws CommandParserException
		{
			 
			//  Registration command: /shelfs/{sid}/elements/{eid}/{type}
			parser.registerCommand("POST", 
					new StringBuilder("/shelfs/{").append(PostElement.SID).append("}").append("/elements/{")
					.append(PostElement.ELEMENT_TYPE).append("}")
					.toString(), 
					new PostElement.Factory(userrepository,shelfRepo, elementsRepo));
			
			
		
			assertTrue(parser.getCommand("POST", "/shelfs/1/elements/CD") instanceof PostElement);
		}
		
		/**
		 *Test the {@code PostShelfCollectionElment} command.<br>  
		 * This command that belongs to <i> Shelf Management</i>, creates a new element of type type (Collection, book, CD or DVD) 
		 * in the collection identified by a given id, which is on the shelf identified by a given id:
		 *  
		 * @throws CommandParserException
		 */
		@Test
		public void shouldPostAShelfCollection() throws CommandParserException
		{
						
			//  Registration command: /shelfs/{sid}/elements/{eid}/{type}
			parser.registerCommand("POST", 
					new StringBuilder("/shelfs/{").append(PostShelfCollectionElement.SID).append("}").append("/elements/{").
					append(PostShelfCollectionElement.EID).append("}/{").append(PostShelfCollectionElement.ELEMENT_TYPE)
					.append("}").toString(), 
					new PostShelfCollectionElement.Factory(userrepository,shelfRepo, elementsRepo));
			
			
		
			assertTrue(parser.getCommand("POST", "/shelfs/1/elements/1/Book") instanceof PostShelfCollectionElement);
		}

}
