package main.java.FHJ.shelf.commands;

import java.util.Map;
import java.util.TreeMap;

import main.java.FHJ.shelf.commands.exceptions.CommandException;
import main.java.FHJ.shelf.model.Element;
import main.java.FHJ.shelf.model.Shelf;
import main.java.FHJ.shelf.model.repos.ElementsRepository;
import main.java.FHJ.shelf.model.repos.ShelfRepository;

/**
 * This class defines the process of getting a shelf element
 * 
 * @author Filipa Estiveira, Hugo Leal, José Oliveira
 */
public class GetShelfElement extends BaseGetCommand implements Command {

	/**
	 * Class that implements the {@link GetShelfElement} factory, according to
	 * the AbstratFactory design pattern.
	 */
	public static class Factory implements CommandFactory {

		/**
		 * Holds the shelf repository to be used by the command
		 */
		private final ShelfRepository shelfRepo;

		/**
		 * Holds the element repository to be used by the command
		 */
		private final ElementsRepository elementsRepo;

		/**
		 * This is the constructor for the class above, it defines the factory
		 * 
		 * @param shelfRepo
		 *            is an instance of ShelfRepository
		 * @param elementsRepo
		 *            is an instance of ElementsRepository
		 */
		public Factory(ShelfRepository shelfRepo,
				ElementsRepository elementsRepo) {
			this.shelfRepo = shelfRepo;
			this.elementsRepo = elementsRepo;
		}

		/**
		 * This is an override method of the base class, it returns a new
		 * instance of GetShelfElement
		 */
		@Override
		public Command newInstance(Map<String, String> parameters) {
			return new GetShelfElement(shelfRepo, elementsRepo, parameters);
		}

	}

	/**
	 * Holds the shelf repository to be used by the command
	 */
	private final ShelfRepository shelfRepo;

	/**
	 * Holds the element repository to be used by the command
	 */
	private final ElementsRepository elementsRepo;

	/**
	 * The name of the parameter holding the shelf's identifier
	 */
	public static final String SID = "sid";

	/**
	 * The name of the parameter holding the element's identifier
	 */
	public static final String EID = "eid";
	// private final long shelfId;

	/**
	 * The array containing all the demanding parameters of this command
	 */
	private static final String[] DEMANDING_PARAMETERS = { SID, EID };

	/**
	 * Initiates an instance with the given the repository{shelf, element} and
	 * command parameters
	 * 
	 * @param repository
	 *            the repository to be used
	 * @param parameters
	 *            the command's unparsed parameters
	 */
	private GetShelfElement(ShelfRepository shelfRepo,
			ElementsRepository elementsRepo, Map<String, String> parameters) {
		super(parameters);
		this.shelfRepo = shelfRepo;
		this.elementsRepo = elementsRepo;
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
		@SuppressWarnings("unused")
		Shelf shelf = (Shelf) shelfRepo.getShelfById(shelfID);

		long elementsID = Long.parseLong(parameters.get(EID));
		Element element = (Element) elementsRepo.getElementById(elementsID);

		Map<String, String> containerToCommandResult = new TreeMap<String, String>();

		containerToCommandResult.put("Element ID:" + elementsID + "\n",
				"Element ID -" + elementsID + element.toString() + " ");
		return containerToCommandResult;
	}

}

