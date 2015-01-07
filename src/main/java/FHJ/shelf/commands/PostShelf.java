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
	private static final String[] DEMANDING_PARAMETERS = {NBELEMENTS};
	
	
	/**
	 * Class that implements the {@link PostShelf} factory, according to the 
	 * AbstratFactory design pattern. 
	 */
	public static class Factory implements CommandFactory {

		private final ShelfRepository shelfRepo;
		
		private final UserRepository userRepo;

		public Factory(UserRepository userRepo, ShelfRepository shelfRepo)
		{
			this.shelfRepo = shelfRepo;
			this.userRepo = userRepo;
		}

		@Override
		public Command newInstance(Map<String, String> parameters) 
		{
			return new PostShelf(userRepo, shelfRepo, parameters);
		}
	}

	
	/**
     * Holds the shelf repository to be used by the command
     */
	private final ShelfRepository shelfRepo;

	
	 /**
     * Initiates an instance with the given the repository{user, shelf} and command parameters
     * 
     * @param repository the repository to be used
     * @param parameters the command's unparsed parameters
     */
	private PostShelf(UserRepository userRepo, ShelfRepository shelfRepo, Map<String, String> parameters)
	{
		super(userRepo, parameters);
		this.shelfRepo = shelfRepo;
	}

	/**
     * {@see Command#getMandatoryParameters()}
     */
	@Override
	protected String[] getMandatoryParameters() {
		
		return DEMANDING_PARAMETERS;
	}

	@Override
	protected String validLoginPostExecute() {
		int elements = getParameterAsInt(NBELEMENTS);
		
		Shelf p = createShelf(elements);
		
		shelfRepo.insert(p);
		String result =  new StringBuilder("ShelfId: ").append(p.getId()).toString();
		return result;
	}

	
	/**
	 * Instantiate a Shelf
	 * @param elements , shelf's size
	 * @return
	 */
	private Shelf createShelf(int elements) {
		
		return new Shelf(elements);
	}
}
