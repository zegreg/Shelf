package fhj.shelf.commands;

import java.util.Map;
import java.util.concurrent.ExecutionException;

import fhj.shelf.commands.exceptions.CommandException;
import fhj.shelf.repos.ElementsRepository;
import fhj.shelf.repos.ShelfRepository;
import fhj.shelf.repos.UserRepository;

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
		String name = parameters.get(NAME);
		String author = parameters.get(AUTHOR);
		
		int tracksNumber;
		if(parameters.get(TRACKSNUMBER) != null){
		tracksNumber = getParameterAsInt(TRACKSNUMBER);
		}
		else{
		tracksNumber = 0;
		}
		
		int duration;
		if(parameters.get(DURATION) != null){
			duration = getParameterAsInt(DURATION);
		}
		else{
		duration = 0;
		}
		
		
		try {
			return new fhj.shelf.commandsDomain.CreateAnElementInAShelf(
					shelfRepo, elementsRepo, shelfID, elementType, name,
					author, tracksNumber, duration).call();

		} catch (Exception cause) {
			throw new ExecutionException(cause);
		}

	}
}
