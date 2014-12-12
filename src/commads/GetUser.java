package commads;

import java.util.Map;

import Database.ShelfRepository;
import User.AbstractUser;
import User.UserInterface;
import User.UserRepository;
import afterSOLIDrevisionEHL.model.AbstractShelf;
import exceptions.CommandException;

public class GetUser extends BaseCommand implements Command {
	
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
				return new GetUser(repository, parameters);
			}
			
		}

		private final UserRepository userRepository;
		
		
		
		public static final String USERNAME = "username";
		
		private static final String[] DEMANDING_PARAMETERS = {USERNAME};
		/**
		 * 
		 * @param repository
		 * @param id
		 */
		private GetUser(UserRepository repository, Map<String, String> parameters)
		{
			super(parameters);
			this.userRepository = repository;
		}
		
		@Override
		protected void internalExecute() throws CommandException 
		{	
			String username = parameters.get(USERNAME);
			
			UserInterface user = userRepository.getUserName(username);
			System.out.println(user.toString());
		}

		@Override
		protected String[] getDemandingParametres() {
			return DEMANDING_PARAMETERS;
		}
}
