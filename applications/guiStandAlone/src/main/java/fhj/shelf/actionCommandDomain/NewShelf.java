package fhj.shelf.actionCommandDomain;

import java.util.Map;

import fhj.shelf.commandsDomain.CreateShelf;
import fhj.shelf.repos.InMemoryShelfRepository;
import fhj.shelf.repos.ShelfRepository;
import fhj.shelf.utils.mutation.ShelfCreationDescriptor;

public class NewShelf implements StandAloneCommand {

	private Map<String, String> parameters;
	
	private static final String NBELEMENTS = "nbElements";

	private ShelfRepository shelfRepo = StandAloneDatabase.getShelfRepoInstance();

	public NewShelf(Map<String, String> parameters) {
		this.parameters = parameters;
	}

	public String execute() throws NumberFormatException, Exception {
		return new CreateShelf(shelfRepo, new ShelfCreationDescriptor(
				Integer.valueOf(parameters.get(NBELEMENTS)))).call();
	}

}