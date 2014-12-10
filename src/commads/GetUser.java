package commads;

import java.util.Map;

import Database.ShelfRepository;
import User.UserInterface;
import User.UserRepository;
import afterSOLIDrevisionEHL.model.AbstractShelf;

/**
 * @author amiguinhos do Maia
 *
 */
public class GetUser implements Command
{
	
	/**
	 * Class that implements the {@link PostElement} factory, according to the 
	 * AbstratFactory design pattern. 
	 */
	public static class Factory implements CommandFactory {
		

		private final UserRepository repository;
		
		public Factory(UserRepository repository)
		{
			this.repository = repository;
			
		}
		
		@Override
		public Command newInstance(Map<String, String> parameters) 
		{
		
			final String id = "sid";
			return new GetUser(repository);
		}
		
	}

	private final UserRepository userRepository;
	
	//private final long shelfId;
	
	/**
	 * 
	 * @param repository
	 * @param id
	 */
	private GetUser(UserRepository repository)
	{
		this.userRepository = repository;
	//	this.shelfId  = id;
	}
	
	@Override
	public void execute() 
	{

		Iterable<UserInterface> iterator = userRepository.getDatabaseElements();

		
			for (UserInterface element :  iterator) {

				System.out.println(element.getId() + " "  +element.toString());
			}
		


		
	}
}
