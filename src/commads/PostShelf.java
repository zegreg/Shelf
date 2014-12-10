package commads;

import java.util.Map;

import Database.ShelfRepository;
import afterSOLIDrevisionEHL.model.AbstractShelf;
import afterSOLIDrevisionEHL.model.Shelf;

public class PostShelf implements Command {

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
			
					
			//final String id = "sid";
			return new PostShelf(repository);
		}
		
	}

	private final ShelfRepository shelfRepository;
	
	//private final long shelfId;
	
	/**
	 * 
	 * @param repository
	 * @param id
	 */
	private PostShelf(ShelfRepository repository)
	{
		this.shelfRepository = repository;
 	
	}
	
	@Override
	public void execute() 
	{
		Iterable<AbstractShelf> iterator = shelfRepository.getDatabaseElements();
		for (AbstractShelf abstractShelf : iterator) {
			System.out.println(abstractShelf.getId());
		}





		
	}
	
	
}
