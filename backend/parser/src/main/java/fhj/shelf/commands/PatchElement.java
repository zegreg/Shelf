package fhj.shelf.commands;


import java.util.Map;
import java.util.concurrent.ExecutionException;

import fhj.shelf.exceptions.CommandException;
import fhj.shelf.repos.ElementsRepository;
import fhj.shelf.repos.ShelfRepository;
import fhj.shelf.repos.UserRepository;

/**
 * This class defines the process of editing an element
 * 
 * @author Filipa Estiveira, Hugo Leal, José Oliveira
 */
public class PatchElement extends BasePostCommand implements Command {

	/**
	 * Class that implements the {@link PatchElement} factory, according to the
	 * AbstratFactory design pattern.
	 */
	public static class Factory implements CommandFactory {

		/**
		 * Holds the shelf repository to be used by the command
		 */
		private final ShelfRepository shelfRepo;

		/**
		 * Holds the elements repository to be used by the command
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
		 * instance of PatchElement
		 */
		@Override
		public Command newInstance(Map<String, String> parameters) {
			return new PatchElement(userRepo, shelfRepo, elementsRepo,
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
	 * demanding parameter
	 */
	public static final String NAME = "name";

	/**
	 * demanding parameter
	 */
	public static final String DURATION = "duration";

	/**
	 * demanding parameter
	 */
	public static final String TRACKSNUMBER = "tracksNumber";

	/**
	 * demanding parameter
	 */
	public static final String AUTHOR = "author";

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
	public static final String[] MANDATORY_PARAMETERS = { SID, EID };

	/**
	 * Initiates an instance with the given the repository{user, shelf, element}
	 * and command parameters
	 * 
	 * @param repository
	 *            the repository to be used
	 * @param parameters
	 *            the command's unparsed parameters
	 */
	private PatchElement(UserRepository userRepo, ShelfRepository shelfRepo,
			ElementsRepository elementsRepo, Map<String, String> parameters) {
		super(userRepo, parameters);
		this.shelfRepo = shelfRepo;
		this.elementsRepo = elementsRepo;
	}

	/**
	 * This is an override method of the base class, that returns the demanding
	 * parameters
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

		String name = parameters.get(NAME);
		String author = parameters.get(AUTHOR);
		long shelfsID = getParameterAsLong(SID);
		long elementsID = getParameterAsLong(EID);
		
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
			return new fhj.shelf.commandsDomain.EditShelfElement(shelfRepo,
					elementsRepo, shelfsID, elementsID, name, author,
					tracksNumber, duration).call();

		} catch (Exception cause) {
			throw new ExecutionException(cause);
		}

	}
}
