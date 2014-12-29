package main.java.FHJ.shelf.commands;

import java.util.Map;







import main.java.FHJ.shelf.commands.exceptions.CommandException;
import main.java.FHJ.shelf.model.repos.UserInterface;
import main.java.FHJ.shelf.model.repos.UserRepository;
import main.java.FHJ.shelf.output.CreateFactory;


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
		protected void ExecuteUser(String format) throws CommandException 
		{	
			validateDemandingParameters(USERNAME);
//			String username = parameters.get(USERNAME);

			CreateFactory createFactory = new CreateFactory(userRepository, parameters);
			createFactory.getCommand(format, "output");
			
//			UserInterface user = userRepository.getUserName(username);
//			
//			String result = user.toString();
//			
//			System.out.println( result );
		}

		@Override
		protected String[] getMandatoryParameters() {
			return DEMANDING_PARAMETERS;
		}
}
