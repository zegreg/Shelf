package main.java.FHJ.shelf.commands;

import java.util.Map;

import main.java.FHJ.shelf.commands.exceptions.CommandException;
import main.java.FHJ.shelf.model.Element;
import main.java.FHJ.shelf.model.Shelf;
import main.java.FHJ.shelf.model.repos.ElementsRepository;
import main.java.FHJ.shelf.model.repos.ShelfRepository;
import main.java.FHJ.shelf.model.repos.UserRepository;

/**
 * This class defines how to delete a shelf element.
 * 
 * @authors Filipa Estiveira, Hugo Leal, José Oliveira
 */
public class DeleteShelfElement extends BasePostCommand implements Command {

	/**
	 * Class that implements the {@link DeleteShelElement} factory, according to
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
		 * Holds the user repository to be used by the command
		 */
		private final UserRepository userRepo;

		/**
		 * This is the constructor for the class above, it defines how the
		 * factory is constructed.
		 * 
		 * @param shelfRepo
		 *            is an instance of ShelfRepository
		 * @param elementsRepo
		 *            is an instance of ElementsRepository
		 */
		public Factory(UserRepository userRepo, ShelfRepository shelfRepo,
				ElementsRepository elementsRepo) {
			this.userRepo = userRepo;
			this.shelfRepo = shelfRepo;
			this.elementsRepo = elementsRepo;
		}

		/**
		 * This is an override method of the base class, it returns a new
		 * instance of DeleteShelfElement
		 */
		@Override
		public Command newInstance(Map<String, String> parameters) {
			return new DeleteShelfElement(userRepo, shelfRepo, elementsRepo,
					parameters);
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
	 * Initiates an instance with the given the repository{user, shelf, element}
	 * and command parameters
	 * 
	 * @param repository
	 *            the repository to be used
	 * @param parameters
	 *            the command's unparsed parameters
	 */
	private DeleteShelfElement(UserRepository userRepo,
			ShelfRepository shelfRepo, ElementsRepository elementsRepo,
			Map<String, String> parameters) {
		super(userRepo, parameters);
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
	 * Return a message for login success
	 */
	@Override
	protected String validLoginPostExecute() throws CommandException {
		long shelfID = Long.parseLong(parameters.get(SID));
		Shelf shelf = (Shelf) shelfRepo.getShelfById(shelfID);

		long elementsID = Long.parseLong(parameters.get(EID));
		Element element = (Element) elementsRepo.getElementById(elementsID);

		String result = "";

		if (shelf.remove(element)) {
			result = "Element successful remove from shelf ";
		}
		return result;
	}

}
