package fhj.shelf.commands;

import java.util.Map;
import java.util.concurrent.ExecutionException;

import model.fhj.shelf.model.mutations.ElementCreationDescriptor;
import fhj.shelf.commandsDomain.CreateAnElementInACollectionInAShelf;
import fhj.shelf.commandsDomain.ElementCreationDescriptorWizard;
import fhj.shelf.exceptions.CommandException;
import fhj.shelf.inMemoryRepositories.ElementsRepository;
import fhj.shelf.inMemoryRepositories.ShelfRepository;
import fhj.shelf.repositories.UserRepository;


/**
 * Class whose instances represent the command that posts a shelf collection
 * element.
 * 
 * @author Filipa Estiveira, Hugo Leal, José Oliveira
 */
public class PostShelfCollectionElement extends BasePostCommand implements
		Command {

	/**
	 * Class that implements the {@link PostShelfCollectionElement} factory,
	 * according to the AbstratFactory design pattern.
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
		 * This is the constructor for the class above, it defines the factory
		 * 
		 * @param userRepo
		 *            is an instance of UserRepository
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
		 * instance of PostShelfCollectionElement
		 */
		@Override
		public Command newInstance(Map<String, String> parameters) {
			return new PostShelfCollectionElement(userRepo, shelfRepo,
					elementsRepo, parameters);
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
	 * Mandatory parameter
	 */
	public static final String ELEMENT_TYPE = "elementType";

	/**
	 * Mandatory parameter
	 */
	private static final String NAME = "name";

	/**
	 * Book mandatory parameter
	 */
	private static final String AUTHOR = "author";

	/**
	 * CD mandatory parameter
	 */
	private static final String TRACKSNUMBER = "tracksnumber";

	/**
	 * DVD mandatory parameter
	 */
	private static final String DURATION = "duration";

	/**
	 * The name of the parameter holding the shelf's identifier
	 */
	public static String SID = "sid";

	/**
	 * The name of the parameter holding the element's identifier
	 */
	public static String EID = "eid";

	/**
	 * The array containing all the demanding parameters of this command
	 */
	public static final String[] MANDATORY_PARAMETERS = { SID, EID,
			ELEMENT_TYPE, NAME };

	/**
	 * Initiates an instance with the given the repository{user, shelf, element}
	 * and command parameters
	 * 
	 * @param repository
	 *            the repository to be used
	 * @param parameters
	 *            the command's unparsed parameters
	 */
	private PostShelfCollectionElement(UserRepository userRepo,
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
		return MANDATORY_PARAMETERS;
	}

	/**
	 * This is an override method of the base class, it executes and validates
	 * the command post login and throws an exception when execution isn't valid
	 * 
	 * @throws ExecutionException
	 */
	@Override
	protected String validLoginPostExecute() throws CommandException,
			ExecutionException {

		String elementType = parameters.get(ELEMENT_TYPE);
		long shelfID = getParameterAsLong(SID);
		long elementID = getParameterAsLong(EID);
		String name = parameters.get(NAME);
		String author = parameters.get(AUTHOR);

		int tracksNumber;
		if (parameters.get(TRACKSNUMBER) != null) {
			tracksNumber = getParameterAsInt(TRACKSNUMBER);
		} else {
			tracksNumber = 0;
		}

		int duration;
		if (parameters.get(DURATION) != null) {
			duration = getParameterAsInt(DURATION);
		} else {
			duration = 0;
		}

		try {

			ElementCreationDescriptor<?> creationDescriptor = new ElementCreationDescriptorWizard(
					elementType, name, author, tracksNumber, duration).create();

			return new CreateAnElementInACollectionInAShelf(shelfRepo,
					elementsRepo, shelfID, elementID, creationDescriptor,
					elementType).call();

		} catch (Exception cause) {
			throw new ExecutionException(cause);
		}

	}

}