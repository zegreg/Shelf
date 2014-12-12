package commads;


import java.util.Map;

import exceptions.CommandException;
import Database.ShelfRepository;
import afterSOLIDrevisionEHL.model.AbstractShelf;

public class GetShelf extends BaseCommand implements Command {

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
			return new GetShelf(repository, parameters);
		}
		
	}

	private final ShelfRepository shelfRepository;
	
	
	public static final String SID = "sid";
	//private final long shelfId;
	
	private static final String[] DEMANDING_PARAMETERS = {SID};
	/**
	 * 
	 * @param repository
	 * @param id
	 */
	private GetShelf(ShelfRepository repository, Map<String, String> parameters)
	{
		super(parameters);
		this.shelfRepository = repository;
	}
	
	@Override
	protected void internalExecute() throws CommandException 
	{	
		long shelfId = Long.parseLong(parameters.get(SID));
		
		AbstractShelf shelf = shelfRepository.getElementById(shelfId);
		System.out.println(shelf.toString());
	}

	@Override
	protected String[] getDemandingParametres() {
		return DEMANDING_PARAMETERS;
	}


}
