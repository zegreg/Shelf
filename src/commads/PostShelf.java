package commads;

import java.util.Map;

import exceptions.CommandException;
import Database.Repository;
import Database.ShelfRepository;
import afterSOLIDrevisionEHL.model.AbstractShelf;
import afterSOLIDrevisionEHL.model.Shelf;

public class PostShelf extends BaseCommand implements Command {

	public static final String NBELEMENTS = "nbElements";

	/**
	 * Class that implements the {@link GetProducts} factory, according to the 
	 * AbstratFactory design pattern. 
	 */
	public static class Factory implements CommandFactory {

		private final Repository<AbstractShelf> repository;

		public Factory(ShelfRepository productRepo)
		{
			this.repository = productRepo;
		}

		@Override
		public Command newInstance(Map<String, String> parameters) 
		{
			return new PostShelf(repository, parameters);
		}

	}

	private final Repository<AbstractShelf> shelfRepository;

	

	private static final String[] DEMANDING_PARAMETERS = {NBELEMENTS};

	/**
	 * 
	 * @param repository
	 * @param id
	 */
	private PostShelf(Repository<AbstractShelf> repository, Map<String, String> parameters)
	{
		super(parameters);
		this.shelfRepository = repository;

	}

	@Override
	public void internalExecute() throws CommandException
	{	
		int elements = getParameterAsInt(NBELEMENTS);

		Shelf p = createShelf(elements);


		shelfRepository.insert(p);
		System.out.println( new StringBuilder("ShelfId :")
		                      .append(p.getId()));
		
	}

	private Shelf createShelf(int elements) {

		return new Shelf(elements);
	}

	@Override
	protected String[] getDemandingParametres() {
		
		return DEMANDING_PARAMETERS;
	}

}
