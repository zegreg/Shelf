package main.java.FHJ.shelf.commands;

import java.util.Map;
import java.util.TreeMap;

import main.java.FHJ.shelf.commands.exceptions.CommandException;
import main.java.FHJ.shelf.model.repos.UserInterface;
import main.java.FHJ.shelf.model.repos.UserRepository;



public class GetUser extends BaseGetCommand implements Command {
	
		/**
		 * Class that implements the {@link GetUser} factory, according to the 
		 * AbstratFactory design pattern. 
		 */
		public static class Factory implements CommandFactory {

			/**
		     * Holds the user repository to be used by the command
		     */
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

		/**
	     * Holds the user repository to be used by the command
	     */
		private final UserRepository userRepository;
		
		
		/**
		 * demaning parameter
		 */
		public static final String USERNAME = "username";

		/**
	     * The array containing all the demanding parameters of this command
	     */
		private static final String[] DEMANDING_PARAMETERS = {USERNAME};
		
		
		/**
	     * Initiates an instance with the given the repository{shelf} and command parameters
	     * 
	     * @param repository the repository to be used
	     * @param parameters the command's unparsed parameters
	     */
		private GetUser(UserRepository repository, Map<String, String> parameters)
		{
			super(parameters);
			this.userRepository = repository;
		}
		
		/**
		 * Return a parameter map result of the command execution
		 */
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
		
	/**
     * {@see Command#getMandatoryParameters()}
     */
		@Override
		protected String[] getMandatoryParameters() {
			return DEMANDING_PARAMETERS;
		}
}
