package main.java.FHJ.shelf.commands;


import java.util.Map;
import java.util.TreeMap;
import main.java.FHJ.shelf.commands.exceptions.CommandException;
import main.java.FHJ.shelf.model.Shelf;
import main.java.FHJ.shelf.model.repos.ShelfRepository;

public class GetShelfElements extends BaseGetCommand implements Command {

		/**
		 * Class that implements the {@link PostElement} factory, according to the 
		 * AbstratFactory design pattern. 
		 */
		public static class Factory implements CommandFactory {

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

		private final ShelfRepository shelfRepo;
		
		public static final String SID = "sid";
		
		private static final String[] DEMANDING_PARAMETERS = {SID};
		/**
		 * 
		 * @param repository
		 * @param id
		 */
		private GetShelfElements(ShelfRepository shelfRepo, Map<String, String> parameters)
		{
			super(parameters);
			this.shelfRepo = shelfRepo;
		}

		@Override
		protected String[] getMandatoryParameters() {
			return DEMANDING_PARAMETERS;
		}

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



