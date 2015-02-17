package fhj.shelf.parser;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.ExecutionException;

import org.junit.Before;
import org.junit.Test;

import fhj.shelf.CommandParser;
import fhj.shelf.commands.GetShelf;
import fhj.shelf.commands.GetShelfs;
import fhj.shelf.commands.PostShelf;
import fhj.shelf.exceptions.CommandException;
import fhj.shelf.exceptions.CommandParserException;
import fhj.shelf.inMemoryRepositories.ElementsRepository;
import fhj.shelf.inMemoryRepositories.InMemoryElementsRepository;
import fhj.shelf.inMemoryRepositories.InMemoryShelfRepository;
import fhj.shelf.inMemoryRepositories.InMemoryUserRepository;
import fhj.shelf.inMemoryRepositories.ShelfRepository;
import fhj.shelf.repositories.User;
import fhj.shelf.repositories.UserRepository;

public class ShelfCommandsExecutionTest {

	/**
	 * Create a CommandParser
	 */
	CommandParser parser = CommandParser.getInstance();

	/**
	 * Creates Repository
	 */
	ShelfRepository shelfRepo = new InMemoryShelfRepository();
	ElementsRepository elementsRepo = new InMemoryElementsRepository();
	UserRepository userrepository = new InMemoryUserRepository();

	@Before
	public void createAdmin() {
		User lima = new User("Lima", "SLB", "SLB", "SLB");
		userrepository.add(lima);
	}

	@Test
	public void shouldPostAShelf() throws CommandParserException,
			IllegalArgumentException, CommandException, ExecutionException {

		parser.registerCommand("POST",
				new StringBuilder("/shelfs/").toString(),
				new PostShelf.Factory(userrepository, shelfRepo));

		String result = parser.getCommand("POST", "/shelfs/",
				"loginName=Lima&loginPassword=SLB&nbElements=10").execute();

		String expectedResult = "ShelfId: 2";

		assertEquals(expectedResult, result);

	}

	@Test
	public void shouldPostAndGetAShelfDetails() throws CommandParserException,
			IllegalArgumentException, CommandException, ExecutionException {

		parser.registerCommand("POST",
				new StringBuilder("/shelfs/").toString(),
				new PostShelf.Factory(userrepository, shelfRepo));

		parser.getCommand("POST", "/shelfs/",
				"loginName=Lima&loginPassword=SLB&nbElements=10").execute();

		parser.registerCommand("GET",
				new StringBuilder("/shelfs/{").append(GetShelf.SID).append("}")
						.append("/details").toString(), new GetShelf.Factory(
						shelfRepo));

		String result = parser.getCommand("GET", "/shelfs/1/details").execute();

		StringBuilder expectedResult = new StringBuilder(" ShelfShelf1=null")
				.append("\n\n\n").append("Capacity=10").append("\n\n\n")
				.append("FreeSpace=10").append("\n\n\n");

		assertEquals(expectedResult.toString(), result);

	}

	@Test
	public void shouldPostAndGetAShelf() throws CommandParserException,
			IllegalArgumentException, CommandException, ExecutionException {

		parser.registerCommand("POST",
				new StringBuilder("/shelfs/").toString(),
				new PostShelf.Factory(userrepository, shelfRepo));

		parser.getCommand("POST", "/shelfs/",
				"loginName=Lima&loginPassword=SLB&nbElements=10").execute();
		
		parser.registerCommand("GET", new StringBuilder("/shelfs/").toString(),
				new GetShelfs.Factory(shelfRepo));

		String result = parser.getCommand("GET", "/shelfs/").execute();

		StringBuilder expectedResult = new StringBuilder(
				"Container Shelves=null").append("\n\n\n")
				.append("Shelf_id=3=Elements=0&FreeSpace=10").append("\n\n\n");

		assertEquals(expectedResult.toString(), result);

	}


}
