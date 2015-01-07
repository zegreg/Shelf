package main.java.FHJ.shelf.commands;


import java.util.Map;
import java.util.TreeMap;
import main.java.FHJ.shelf.commands.exceptions.CommandException;
import main.java.FHJ.shelf.model.Shelf;
import main.java.FHJ.shelf.model.repos.ShelfRepository;

public class GetShelfElements extends BaseGetCommand implements Command {

		/**
		 * Class that implements the {@link GetShelfElements} factory, according to the 
		 * AbstratFactory design pattern. 
		 */
		public static class Factory implements CommandFactory {

			/**
		     * Holds the shelf repository to be used by the command
		     */
			private final ShelfRepository shelfRepo;

			public Factory(ShelfRepository shelfRepo)
			{
				this.shelfRepo = shelfRepo;
			}

			@Override
			public Command newInstance(Map<String, String> parameters) 
			{
				return new GetShelfElements(shelfRepo, parameters);
			}
			
		}

		/**
	     * Holds the shelf repository to be used by the command
	     */
		private final ShelfRepository shelfRepo;
		
		/**
	     * The name of the parameter holding the shelf's identifier
	     */
		public static final String SID = "sid";
		
		/**
	     * The array containing all the demanding parameters of this command
	     */
		private static final String[] DEMANDING_PARAMETERS = {SID};
		
		
		/**
	     * Initiates an instance with the given the repository{shelf} and command parameters
	     * 
	     * @param repository the repository to be used
	     * @param parameters the command's unparsed parameters
	     */
		private GetShelfElements(ShelfRepository shelfRepo, Map<String, String> parameters)
		{
			super(parameters);
			this.shelfRepo = shelfRepo;
		}

		
		/**
	     * {@see Command#getMandatoryParameters()}
	     */
		@Override
		protected String[] getMandatoryParameters() {
			return DEMANDING_PARAMETERS;
		}

		
		/**
		 * Return a parameter map result of the command execution
		 */
		@Override
		protected Map<String, String> actionExecute() throws CommandException {

			
			long shelfID = Long.parseLong(parameters.get(SID));
			
			Shelf shelf =  (Shelf) shelfRepo.getShelfById(shelfID);
		
			Map<String, String> map = new TreeMap<String, String>();
			
			putCommandResultInAMap(map, shelf);
			
			return map;
		}
		
		protected void putCommandResultInAMap(
				Map<String, String> containerToCommandResult, Shelf shelf) {

			String shelfID = String.valueOf(shelf.getId());


			containerToCommandResult.put("Shelf Content ID:"+shelfID, shelf.toString());
		}
	}



