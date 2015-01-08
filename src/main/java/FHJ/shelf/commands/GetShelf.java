package main.java.FHJ.shelf.commands;

import java.util.Map;
import java.util.TreeMap;

import main.java.FHJ.shelf.commands.exceptions.CommandException;
import main.java.FHJ.shelf.model.Shelf;
import main.java.FHJ.shelf.model.repos.ShelfRepository;


/**
 * Class that implements the command to obtain the {@link Shelf} instance with a given identifier.
 */
public class GetShelf extends BaseGetCommand implements Command {

	 /**
     * Class that implements the {@link GetShelf} factory, according to the Abstract Factory
     * design pattern (GoF).
     */
	public static class Factory implements CommandFactory {

		private final ShelfRepository repository;

		public Factory(ShelfRepository repository) {
			this.repository = repository;

		}

		@Override
		public Command newInstance(Map<String, String> parameters) {
			return new GetShelf(repository, parameters);
		}

	}

	
	 /**
     * Holds the shelf repository to be used by the command
     */
	private final ShelfRepository shelfRepository;

	
	 /**
     * The name of the parameter holding the shelf's identifier
     */
	public static final String SID = "sid";
	// private final long shelfId;

	/**
     * The array containing all the demanding parameters of this command
     */
	 	private static final String[] DEMANDING_PARAMETERS = { SID };

	 	 /**
	     * Initiates an instance with the given shelf repository and command parameters
	     * 
	     * @param repository the repository to be used
	     * @param parameters the command's unparsed parameters
	     */
	private GetShelf(ShelfRepository repository, Map<String, String> parameters) {
		super(parameters);
		this.shelfRepository = repository;
	}
	
	/**
     * {@see Command#getMandatoryParameters()}
     */
	@Override
	protected String[] getMandatoryParameters() {
		return DEMANDING_PARAMETERS;
	}

	@Override
	protected Map<String, String> actionExecute() throws CommandException {
		long shelfId = Long.parseLong(parameters.get(SID));

		Shelf shelf = (Shelf)shelfRepository.getShelfById(shelfId);
	
		 new TreeMap<String, String>();
		
		Map<String, String> map = putCommandResultInAMap(shelf);
	
		return map;
	}

	protected Map<String, String> putCommandResultInAMap(Shelf shelf) {
		Map<String, String> containerToCommandResult = new TreeMap<String, String>();
		@SuppressWarnings("unused")
		String shelfID = String.valueOf(shelf.getId());

		@SuppressWarnings("unused")
		String elementContained = null;
		for (int i = 0; i < shelf.getInfoAboutAllElementsContained().length; i++) {
			elementContained = shelf.getInfoAboutAllElementsContained()[i]
					.toString();
		}

		containerToCommandResult.put(
				"Shelf Details ID " + String.valueOf(shelf.getId()),
				shelf.details());

		return containerToCommandResult;
	}

}
