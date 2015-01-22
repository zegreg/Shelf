package fhj.shelf.commands;

import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ExecutionException;

import fhj.shelf.commands.exceptions.CommandException;
import fhj.shelf.utils.AbstractElement;
import fhj.shelf.utils.repos.ElementsRepository;
import fhj.shelf.utils.repos.ShelfRepository;

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
	 * 
	 * @throws ExecutionException
	 */
	@Override
	protected Map<String, String> actionExecute() throws CommandException,
			ExecutionException {

		long shelfID = Long.parseLong(parameters.get(SID));
		long elementID = Long.parseLong(parameters.get(EID));

		try {
			AbstractElement element = new fhj.shelf.commandsDomain.GetAnElementThatIsInAShelf(
					shelfRepo, elementsRepo, shelfID, elementID).call();
			return putCommandResultInAMapPreparedForTheOutput(element);

		} catch (Exception cause) {
			throw new ExecutionException(cause);
		}

	}

	protected Map<String, String> putCommandResultInAMapPreparedForTheOutput(
			AbstractElement element) {

		Map<String, String> map = new TreeMap<String, String>();

		map.put("Element ID:" + element.getId() + "\n", "Element ID -"
				+ element.getId() + element.toString() + " ");

		return map;
	}
}

