package main.java.FHJ.shelf.commands;

import java.util.Map;

import main.java.FHJ.shelf.model.Shelf;
import main.java.FHJ.shelf.model.repos.ShelfRepository;
import main.java.FHJ.shelf.model.repos.UserRepository;

public class PostShelf extends BasePostCommand implements Command {

	public static final String NBELEMENTS = "nbElements";
	
	private static final String[] DEMANDING_PARAMETERS = {NBELEMENTS};
	/**
	 * Class that implements the {@link GetProducts} factory, according to the 
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

	private final ShelfRepository shelfRepo;

	/**
	 * 
	 * @param repository
	 * @param id
	 */
	private PostShelf(UserRepository userRepo, ShelfRepository shelfRepo, Map<String, String> parameters)
	{
		super(userRepo, parameters);
		this.shelfRepo = shelfRepo;
	}

	@Override
	protected String[] getDemandingParametres() {
		
		return DEMANDING_PARAMETERS;
	}

	@Override
	protected void validLoginPostExecute() {
		int elements = getParameterAsInt(NBELEMENTS);
		
		Shelf p = createShelf(elements);
		
		shelfRepo.insert(p);
		System.out.println( new StringBuilder("ShelfId: ")
		.append(p.getId()));
	}

	private Shelf createShelf(int elements) {
		
		return new Shelf(elements);
	}
}
