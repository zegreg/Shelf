package main.java.FHJ.shelf.commands;

import java.util.Map;
import java.util.TreeMap;
import main.java.FHJ.shelf.commands.exceptions.CommandException;
import main.java.FHJ.shelf.model.Shelf;
import main.java.FHJ.shelf.model.repos.ShelfRepository;

/**
 * This class defines the process of getting shelf elements
 * 
 * @author Filipa Estiveira, Hugo Leal, José Oliveira
 */
public class GetShelfElements extends BaseGetCommand implements Command {

	/**
	 * Class that implements the {@link GetShelfElements} factory, according to
	 * the AbstratFactory design pattern.
	 */
	public static class Factory implements CommandFactory {

		/**
		 * Holds the shelf repository to be used by the command
		 */
		private final ShelfRepository shelfRepo;

		/**
		 * This is the constructor for the class above, it defines the factory
		 * 
		 * @param shelfRepo
		 *            is an instance of ShelfRepository
		 */
		public Factory(ShelfRepository shelfRepo) {
			this.shelfRepo = shelfRepo;
		}

		/**
		 * This is an override method of the base class, it returns a new
		 * instance of GetShelfElements
		 */
		@Override
		public Command newInstance(Map<String, String> parameters) {
			return new GetShelfElements(shelfRepo, parameters);
		}

	}

	/**
	 * Holds the shelf repository to be used by the command
	 */
	private final ShelfRepository shelfRepo;

	/**
	 * The name of the parameter holding the shelf's identifier
	 */
	public static final String SID = "sid";

	/**
	 * The array containing all the demanding parameters of this command
	 */
	private static final String[] DEMANDING_PARAMETERS = { SID };

	/**
	 * Initiates an instance with the given the repository{shelf} and command
	 * parameters
	 * 
	 * @param repository
	 *            the repository to be used
	 * @param parameters
	 *            the command's unparsed parameters
	 */
	private GetShelfElements(ShelfRepository shelfRepo,
			Map<String, String> parameters) {
		super(parameters);
		this.shelfRepo = shelfRepo;
	}

	/**
	 * {@see Command#getMandatoryParameters()}
	 */
	@Override
	protected String[] getMandatoryParameters() {
		return DEMANDING_PARAMETERS;
	}

	/**
	 * Return a parameter map result of the command execution
	 */
	@Override
	protected Map<String, String> actionExecute() throws CommandException {

		long shelfID = Long.parseLong(parameters.get(SID));

		Shelf shelf = (Shelf) shelfRepo.getShelfById(shelfID);

		Map<String, String> map = new TreeMap<String, String>();

		putCommandResultInAMap(map, shelf);

		return map;
	}

	/**
	 * This method is the process of putting a command result in a map
	 * 
	 * @param shelf
	 *            is an instance of Shelf
	 * @return an instance of containerToCommandResult
	 */
	protected void putCommandResultInAMap(
			Map<String, String> containerToCommandResult, Shelf shelf) {

		String shelfID = String.valueOf(shelf.getId());

		containerToCommandResult.put("Shelf Content ID:" + shelfID,
				shelf.toString());
	}
}

