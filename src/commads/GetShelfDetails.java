package commads;

import java.util.Map;

import Database.ShelfRepository;
import afterSOLIDrevisionEHL.model.AbstractShelf;

public class GetShelfDetails implements Command {

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
			final String ID = "sid";
			return new GetShelfDetails(repository, Long.parseLong(parameters.get(ID)));
		}

	}

	private final ShelfRepository shelfRepository;
	
	private final long shelfId;
	
	public static final String ID = "sid";
	//private final long shelfId;
	
	/**
	 * 
	 * @param repository
	 * @param id
	 */
	private GetShelfDetails(ShelfRepository repository, long id)
	{
		this.shelfRepository = repository;
		this.shelfId  = id;
	}

	@Override
	public void execute() 
	{
		AbstractShelf shelf = shelfRepository.getProductById(shelfId);
		System.out.println(shelf.toString());
	}		

}
