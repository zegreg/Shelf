package commads;

import java.util.Map;

import exceptions.CommandException;
import Database.ShelfRepository;

import User.UserInterface;
import User.UserRepository;
import afterSOLIDrevisionEHL.model.AbstractShelf;

/**
 * 
 *
 */
public class GetUsers extends BaseCommand implements Command {

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
			return new GetUsers(repository, parameters);
		}
		
	}

	/**
	 * Gets a UserRepository instance
	 */
	private final UserRepository userRepository;
	
	/**
	 * Gets an array of parameters of the actual Command
	 */
	private static final String[] DEMANDING_PARAMETERS = {};
	/**
	 * 
	 * @param repository
	 * @param id
	 */
	private GetUsers(UserRepository repository, Map<String, String> parameters)
	{
		super(parameters);
		this.userRepository = repository;
	}
	
	@Override
	protected void internalExecute() throws CommandException {
		Iterable<UserInterface> iterator = userRepository.getDatabaseElements();
		
		for (UserInterface element :  iterator) {
			
			System.out.println(element.getLoginName() + " "  +element.toString());
		}		
		
	}
	
	@Override
	protected String[] getDemandingParametres() {
		return DEMANDING_PARAMETERS;
	}
}
