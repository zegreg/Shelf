package commads;


import java.util.Map;






import exceptions.CommandException;
import afterSOLIDrevisionEHL.model.*;
import Database.ShelfRepository;


public class GetShelfs extends BaseCommand implements Command {

	/**
	 * Class that implements the {@link PostElement} factory, according to the 
	 * AbstratFactory design pattern. 
	 */
	public static class Factory implements CommandFactory {
		

		private final ShelfRepository repository;
		
		public Factory(ShelfRepository repository)
		{
			this.repository = repository;
			
		}
		
		@Override
		public Command newInstance(Map<String, String> parameters) 
		{
			return new GetShelfs(repository, parameters);
		}
		
	}

	private final ShelfRepository shelfRepository;
	
	private static final String[] DEMANDING_PARAMETERS = {};
	/**
	 * 
	 * @param repository
	 * @param id
	 */
	private GetShelfs(ShelfRepository repository, Map<String, String> parameters)
	{
		super(parameters);
		this.shelfRepository = repository;
	}
	
	@Override
	protected void internalExecute() throws CommandException {
		Iterable<AbstractShelf> iterator = shelfRepository.getDatabaseElements();
		
		for (AbstractShelf element :  iterator) {
			
			System.out.println(element.getId() + " "  +element.toString());
		}		
		
	}
	
	@Override
	protected String[] getDemandingParametres() {
		return DEMANDING_PARAMETERS;
	}

}
