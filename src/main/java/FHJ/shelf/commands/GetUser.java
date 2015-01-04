package main.java.FHJ.shelf.commands;

import java.util.Map;
import java.util.TreeMap;

import main.java.FHJ.shelf.commands.exceptions.CommandException;
import main.java.FHJ.shelf.model.repos.UserInterface;
import main.java.FHJ.shelf.model.repos.UserRepository;



public class GetUser extends BaseGetCommand implements Command {
	
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
		protected Map<String, String> actionExecute() throws CommandException 
		{	

			String username = parameters.get(USERNAME);
			UserInterface user = userRepository.getUserName(username);
			
			Map<String, String> map = new TreeMap<String, String>();
			
			putCommandResultInAMap(map, user);
			
			return map;
			
//			System.out.println( result );
		}
		
	protected void putCommandResultInAMap(Map<String, String> containerToCommandResult, UserInterface user) {
		
		containerToCommandResult.put("User:", user.toString());
//		containerToCommandResult.put("password", user.getLoginPassword());
//		containerToCommandResult.put("fullName", user.getFullName());
//		containerToCommandResult.put("email", user.getEmail());

	}
		
		
		@Override
		protected String[] getMandatoryParameters() {
			return DEMANDING_PARAMETERS;
		}
}
