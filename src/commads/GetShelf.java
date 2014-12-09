package commads;

import java.util.Iterator;
import java.util.Map;

import afterSOLIDrevisionEHL.model.*;
import Database.ShelfRepository;


public class GetShelf implements Command {

	/**
	 * Class that implements the {@link GetElement} factory, according to the 
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
			
			
			
			//final String ID = "id";
			return new GetShelf(repository);
		}
		
	}

	private final ShelfRepository shelfRepository;
	
	//private final long productId;
	
	/**
	 * 
	 * @param repository
	 * @param id
	 */
	private GetShelf(ShelfRepository repository)
	{
		this.shelfRepository = repository;
		//this.productId  = id;
	}
	
	@Override
	public void execute() 
	{
		Iterable<AbstractShelf> iterator = shelfRepository.getDatabaseElements();
		for (AbstractShelf element : iterator) {
			System.out.println(element.toString());
		}
		
	}
}
