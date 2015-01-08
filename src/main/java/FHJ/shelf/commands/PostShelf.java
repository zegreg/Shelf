package main.java.FHJ.shelf.commands;

import java.util.Map;

import main.java.FHJ.shelf.model.Shelf;
import main.java.FHJ.shelf.model.repos.ShelfRepository;
import main.java.FHJ.shelf.model.repos.UserRepository;

public class PostShelf extends BasePostCommand implements Command {

	/**
	 * demanding parameter
	 */
	public static final String NBELEMENTS = "nbElements";

	/**
	 * The array containing all the demanding parameters of this command
	 */
	private static final String[] MANDATORY_PARAMETERS = { NBELEMENTS };

	/**
	 * Class that implements the {@link PostShelf} factory, according to the
	 * AbstratFactory design pattern.
	 */
	public static class Factory implements CommandFactory {

		private final ShelfRepository shelfRepo;

		private final UserRepository userRepo;

		/**
		 * This is the constructor for the class above, it defines the factory
		 * 
		 * @param userRepo is an instance of UserRepository
		 * @param shelfRepo is an instance of ShelfRepository
		 */
		public Factory(UserRepository userRepo, ShelfRepository shelfRepo) {
			this.shelfRepo = shelfRepo;
			this.userRepo = userRepo;
		}

		/**
		 * This is an override method of the base class, it returns
		 * a new instance of PostShelf
		 */
		@Override
		public Command newInstance(Map<String, String> parameters) {
			return new PostShelf(userRepo, shelfRepo, parameters);
		}
	}

	/**
	 * Holds the shelf repository to be used by the command
	 */
	private final ShelfRepository shelfRepo;

	/**
	 * Initiates an instance with the given the repository{user, shelf} and
	 * command parameters
	 * 
	 * @param repository
	 *            the repository to be used
	 * @param parameters
	 *            the command's unparsed parameters
	 */
	private PostShelf(UserRepository userRepo, ShelfRepository shelfRepo,
			Map<String, String> parameters) {
		super(userRepo, parameters);
		this.shelfRepo = shelfRepo;
	}

	/**
	 * {@see Command#getMandatoryParameters()}
	 */
	@Override
	protected String[] getMandatoryParameters() {

		return MANDATORY_PARAMETERS;
	}

	/**
	 * This is an override method of the base class, it executes and validates the command
	 * post login and throws an exception when execution isn't valid
	 */
	@Override
	protected String validLoginPostExecute() throws IllegalArgumentException  {
		int elements = getParameterAsInt(NBELEMENTS);

		Shelf p = createShelf(elements);

		shelfRepo.insert(p);
		
		String result = new StringBuilder("ShelfId: ").append(p.getId())
				.toString();
		return result;
	}

	/**
	 * Instantiate a Shelf
	 * 
	 * @param elements
	 *            , shelf's size
	 * @return
	 */
	private Shelf createShelf(int elements) throws IllegalArgumentException {

		return new Shelf(elements);
	}
}
