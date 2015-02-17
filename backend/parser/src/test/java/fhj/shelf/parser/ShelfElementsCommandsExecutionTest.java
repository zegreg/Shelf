package fhj.shelf.parser;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.ExecutionException;

import org.junit.Before;
import org.junit.Test;

import fhj.shelf.CommandParser;
import fhj.shelf.commands.PostElement;
import fhj.shelf.commands.PostShelf;
import fhj.shelf.commands.PostShelfCollectionElement;
import fhj.shelf.exceptions.CommandException;
import fhj.shelf.exceptions.CommandParserException;
import fhj.shelf.inMemoryRepositories.ElementsRepository;
import fhj.shelf.inMemoryRepositories.InMemoryElementsRepository;
import fhj.shelf.inMemoryRepositories.InMemoryShelfRepository;
import fhj.shelf.inMemoryRepositories.InMemoryUserRepository;
import fhj.shelf.inMemoryRepositories.ShelfRepository;
import fhj.shelf.repositories.User;
import fhj.shelf.repositories.UserRepository;

public class ShelfElementsCommandsExecutionTest {
	
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
	public void shouldPostAnElementInAShelf() throws CommandParserException,
			IllegalArgumentException, CommandException, ExecutionException {
		
		parser.registerCommand("POST",
				new StringBuilder("/shelfs/").toString(),
				new PostShelf.Factory(userrepository, shelfRepo));

		parser.getCommand("POST", "/shelfs/",
				"loginName=Lima&loginPassword=SLB&nbElements=10").execute();
		
		
		parser.registerCommand(
				"POST",
				new StringBuilder("/shelfs/{").append(PostElement.SID)
				.append("}").append("/elements/{")
				.append(PostElement.ELEMENT_TYPE).append("}")
				.toString(), new PostElement.Factory(userrepository,
						shelfRepo, elementsRepo));
	
		String result = parser.getCommand("POST", "/shelfs/2/elements/Book","loginName=Lima&loginPassword=SLB&name=SLB&author=O_Maior").execute();
		
		System.out.println(result);
		
		String expectedResult = "ElementID: 3";

		assertEquals(expectedResult, result);

	}
	
	@Test
	public void shouldPostAnElementInAShelfCollection() throws CommandParserException,
	IllegalArgumentException, CommandException, ExecutionException {
		
		parser.registerCommand("POST",
				new StringBuilder("/shelfs/").toString(),
				new PostShelf.Factory(userrepository, shelfRepo));

		parser.getCommand("POST", "/shelfs/",
				"loginName=Lima&loginPassword=SLB&nbElements=10").execute();
		
		parser.registerCommand(
				"POST",
				new StringBuilder("/shelfs/{").append(PostElement.SID)
				.append("}").append("/elements/{")
				.append(PostElement.ELEMENT_TYPE).append("}")
				.toString(), new PostElement.Factory(userrepository,
						shelfRepo, elementsRepo));
	
		parser.getCommand("POST", "/shelfs/1/elements/BookCollection","loginName=Lima&loginPassword=SLB&name=SLBCollection").execute();
		
		
		parser.registerCommand(
				"POST",
				new StringBuilder("/shelfs/{")
						.append(PostShelfCollectionElement.SID).append("}")
						.append("/elements/{")
						.append(PostShelfCollectionElement.ELEMENT_TYPE)
						.append("}/{").append(PostShelfCollectionElement.EID)
						.append("}").toString(),
				new PostShelfCollectionElement.Factory(userrepository, shelfRepo,
						elementsRepo));
		
		String result = parser.getCommand("POST", "/shelfs/1/elements/Book/1/",
				"loginName=Lima&loginPassword=SLB&name=SLBSLB&author=Campeão").execute();
		
		String  expectedResult = "ElementID: 2";
		
		
		assertEquals(expectedResult, result);
		
	}

}
