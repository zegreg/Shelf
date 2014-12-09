package commads;

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
			final String ID = "id";
			return new GetShelf(repository, Long.parseLong(parameters.get(ID)));
		}
		
	}

	private final ShelfRepository productsRepository;
	
	private final long productId;
	
	/**
	 * 
	 * @param repository
	 * @param id
	 */
	private GetShelf(ShelfRepository repository, long id)
	{
		this.productsRepository = repository;
		this.productId  = id;
	}
	
	@Override
	public void execute() 
	{
		Shelf shelf = productsRepository.getProductById(productId);
		System.out.println(shelf.toString());
	}
}
