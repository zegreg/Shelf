package main.java.FHJ.shelf.commands;

import java.util.Map;

import main.java.FHJ.shelf.commands.exceptions.CommandException;
import main.java.FHJ.shelf.model.repos.UserInterface;
import main.java.FHJ.shelf.model.repos.UserRepository;

public class PatchUsers extends BaseCommand implements Command {

	
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
				return new PatchUsers(repository, parameters);
			}
			
		}

		public static final String USERNAME = "username";

		public static final String OLD_USER = "olduser";

		public static final String NEW_USER = "newuser";

		/**
		 * Gets a UserRepository instance
		 */
		private final UserRepository userRepository;
		
		/**
		 * Gets an array of parameters of the actual Command
		 */
		private static final String[] DEMANDING_PARAMETERS = {USERNAME,OLD_USER,NEW_USER};
		/**
		 * 
		 * @param repository
		 * @param id
		 */
		private PatchUsers(UserRepository repository, Map<String, String> parameters)
		{
			super(parameters);
			this.userRepository = repository;
		}
		
		@Override
		protected void internalExecute() throws CommandException {
			

			String username = parameters.get(USERNAME);
			String old_user = parameters.get(OLD_USER);
			String new_user = parameters.get(NEW_USER);

			UserInterface user = userRepository.getUserName(username);
			
			if (user.getLoginPassword().equals(old_user)) {
				user.setLoginPassword(new_user);
			};
			
			System.out.println(user.toString());


		}


		
		@Override
		protected String[] getMandatoryParameters() {
			return DEMANDING_PARAMETERS;
		}
	}

	
	
	
	
	

