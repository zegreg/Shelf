package test.java.FHJ.shelf.commands;

import static org.junit.Assert.*;
import main.java.FHJ.shelf.commandParser.CommandParser;
import main.java.FHJ.shelf.commandParser.CommandParserException;
import main.java.FHJ.shelf.commandParser.DuplicateArgumentsException;
import main.java.FHJ.shelf.commandParser.InvalidCommandArgumentsException;
import main.java.FHJ.shelf.commandParser.UnknownCommandException;
import main.java.FHJ.shelf.commands.PostShelf;
import main.java.FHJ.shelf.model.Book;
import main.java.FHJ.shelf.model.BookCollection;
import main.java.FHJ.shelf.model.CD;
import main.java.FHJ.shelf.model.Shelf;
import main.java.FHJ.shelf.model.repos.ElementsRepository;
import main.java.FHJ.shelf.model.repos.InMemoryElementsRepository;
import main.java.FHJ.shelf.model.repos.InMemoryShelfRepository;
import main.java.FHJ.shelf.model.repos.InMemoryUserRepository;
import main.java.FHJ.shelf.model.repos.ShelfRepository;
import main.java.FHJ.shelf.model.repos.UserRepository;

import org.junit.Before;
import org.junit.Test;

public class PostShelfTest {



	/**
	 * Create a CommandParser
	 */
	CommandParser parser = new CommandParser();


	/**
	 * Creates Repository
	 */
	ShelfRepository shelfRepo = new InMemoryShelfRepository();
	UserRepository userrepository = new InMemoryUserRepository();



	@Before
	public void createAShelf() throws CommandParserException
	{
		parser.registerCommand("POST", 
				new StringBuilder("/shelfs/{").append(PostShelf.NBELEMENTS).append("}").toString(), 
				new PostShelf.Factory(userrepository,shelfRepo));
		
		
	}


	@Test
	public void shouldReturnAnShelf() throws CommandParserException
	{
		assertEquals("shelfId:0" ,parser.getCommand("POST", "/shelfs/10").execute() );
	}

}



