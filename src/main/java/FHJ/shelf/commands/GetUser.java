package main.java.FHJ.shelf.commands;

import java.util.Map;







import java.util.Map.Entry;
import java.util.TreeMap;

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
//			validateDemandingParameters(USERNAME);
			String username = parameters.get(USERNAME);
			UserInterface user = userRepository.getUserName(username);
			String result = user.toString();
			
			
			Map<String, String> map = getParameterAsMap(username, result);
			
			CreateFactory createFactory = new CreateFactory(map);
			createFactory.getCommand(format, "output");

			
			
//			System.out.println( result );
		}
		
		protected Map<String, String> getParameterAsMap(String key, String value) {
		Map<String, String > map = new TreeMap<String, String>();
		
		for(Entry<String, String> e : map.entrySet())
			  if(!map.containsKey(e.getKey())){
			    map.put(key, value);
			  }
			

		return map;
	}
		
		
		

		@Override
		protected String[] getMandatoryParameters() {
			return DEMANDING_PARAMETERS;
		}
}
