package commads;

import java.util.Map;

import afterSOLIDrevisionEHL.model.CD;
import afterSOLIDrevisionEHL.model.AbstractElement;
import afterSOLIDrevisionEHL.model.AbstractShelf;
import afterSOLIDrevisionEHL.model.Shelf;
import afterSOLIDrevisionEHL.model.SimpleElement;
import Database.ShelfRepository;


/**
 * Class whose instances represent the command that gets all products in the repository.
 */
public class GetElement implements Command {
	
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
			return new GetElement(repository, parameters);
		}
		
	}
	
	private final ShelfRepository productsRepository;
	private final Map<String, String> parameters;
	
	private GetElement(ShelfRepository repository, Map<String, String> parameters)
	{
		this.productsRepository = repository;
		this.parameters = parameters;
	}
	
	@Override
	public void execute() 
	{
		
		Iterable<AbstractShelf> elements = productsRepository.getDatabaseElements();
		
		for(AbstractShelf p : elements) {
			//TODO
		
		}
	}
}
