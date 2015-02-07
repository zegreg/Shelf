package fhj.shelf.commands;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import fhj.shelf.commandsDomain.CreateAnElementInAShelf;
import fhj.shelf.exceptions.CommandException;
import fhj.shelf.exceptions.InvalidParameterValueException;
import fhj.shelf.repos.ElementsRepository;
import fhj.shelf.repos.ShelfRepository;
import fhj.shelf.repos.UserRepository;
import fhj.shelf.utils.mutation.BookCollectionCreationDescriptor;
import fhj.shelf.utils.mutation.BookCreationDescriptor;
import fhj.shelf.utils.mutation.CDCollectionCreationDescriptor;
import fhj.shelf.utils.mutation.CDCreationDescriptor;
import fhj.shelf.utils.mutation.DVDCollectionCreationDescriptor;
import fhj.shelf.utils.mutation.DVDCreationDescriptor;
import fhj.shelf.utils.mutation.ElementCreationDescriptor;

/**
 * Class whose instances represent the command that gets all products in the
 * repository.
 */
public class PostElement extends BasePostCommand implements Command {

	/**
	 * demanding parameter
	 */
	public static final String ELEMENT_TYPE = "elementType";

	/**
	 * demanding parameter
	 */
	private static final String NAME = "name";

	/**
	 * demanding parameter
	 */
	private static final String AUTHOR = "author";

	/**
	 * demanding parameter
	 */
	private static final String TRACKSNUMBER = "tracksnumber";

	/**
	 * demanding parameter
	 */
	private static final String DURATION = "duration";

	/**
	 * Class that implements the {@link PostElement} factory, according to the
	 * AbstratFactory design pattern.
	 */
	public static class Factory implements CommandFactory {

		private final ShelfRepository shelfRepo;
		private final ElementsRepository elementsRepo;
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
		 * instance of PostElement
		 */
		@Override
		public Command newInstance(Map<String, String> parameters) {
			return new PostElement(userRepo, shelfRepo, elementsRepo,
					parameters);
		}

	}

	/**
	 * Holds the shelf repository to be used by the command
	 */
	private final ShelfRepository shelfRepo;

	/**
	 * Holds the elements repository to be used by the command
	 */
	private final ElementsRepository elementsRepo;

	/**
	 * The name of the parameter holding the shelf's identifier
	 */
	public static String SID = "sid";

	/**
	 * The array containing all the demanding parameters of this command
	 */
	public static final String[] MANDATORY_PARAMETERS = { SID, ELEMENT_TYPE,
			NAME };

	/**
	 * Initiates an instance with the given the repository{user, shelf, element}
	 * and command parameters
	 * 
	 * @param repository
	 *            the repository to be used
	 * @param parameters
	 *            the command's unparsed parameters
	 */
	private PostElement(UserRepository userRepo, ShelfRepository shelfRepo,
			ElementsRepository elementsRepo, Map<String, String> parameters) {
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
		String title = parameters.get(NAME);

		try {
			ElementCreationDescriptor<?> creationDescriptor = null;

			String methodName = "create" + elementType + "Descriptor";

			Class<? extends PostElement> c = this.getClass();

			Method creatorMethod;

			creatorMethod = c.getDeclaredMethod(methodName, String.class);
			creationDescriptor = (ElementCreationDescriptor<?>) creatorMethod
					.invoke(this, title);

			return new CreateAnElementInAShelf(shelfRepo, elementsRepo,
					shelfID, creationDescriptor).call();

		} catch (Exception e) {
			throw new InvalidParameterValueException(
					"Invalid Parameter unable to create " + elementType, e);
		}

	}

	/**
	 * Creates a CD
	 * 
	 * @param name
	 *            of the element to be created
	 * @return an AbstractElement
	 */
	@SuppressWarnings({ "unused", "rawtypes" })
	private ElementCreationDescriptor createCDDescriptor(String name) {

		return new CDCreationDescriptor(name, getParameterAsInt(TRACKSNUMBER));
	}

	/**
	 * Creates a DVD
	 * 
	 * @param name
	 *            of the element to be created
	 * @return an AbstractElement
	 */
	@SuppressWarnings({ "unused", "rawtypes" })
	private ElementCreationDescriptor createDVDDescriptor(String name) {

		return new DVDCreationDescriptor(name, getParameterAsInt(DURATION));
	}

	/**
	 * Creates a Book
	 * 
	 * @param name
	 *            of the element to be created
	 * @return an AbstractElement
	 */
	@SuppressWarnings({ "unused", "rawtypes" })
	private ElementCreationDescriptor createBookDescriptor(String name) {

		return new BookCreationDescriptor(name, parameters.get(AUTHOR));
	}

	/**
	 * Creates a CDCollection
	 * 
	 * @param name
	 *            of the element to be created
	 * @return an AbstractElement
	 */
	@SuppressWarnings({ "unused", "rawtypes" })
	private ElementCreationDescriptor createCDCollectionDescriptor(String name) {
		return new CDCollectionCreationDescriptor(name);
	}

	/**
	 * Creates a DVDCollection
	 * 
	 * @param name
	 *            of the element to be created
	 * @return an AbstractElement
	 */
	@SuppressWarnings({ "unused", "rawtypes" })
	private ElementCreationDescriptor createDVDCollectionDescriptor(String name) {
		return new DVDCollectionCreationDescriptor(name);
	}

	/**
	 * Creates a BookCollection
	 * 
	 * @param name
	 *            of the element to be created
	 * @return an AbstractElement
	 */
	@SuppressWarnings({ "unused", "rawtypes" })
	private ElementCreationDescriptor createBookCollectionDescriptor(String name) {
		return new BookCollectionCreationDescriptor(name);
	}
}

