package main.java.FHJ.shelf.commands;

import java.util.Arrays;
import java.util.Map;

import main.java.FHJ.shelf.commands.exceptions.CommandException;
import main.java.FHJ.shelf.model.Shelf;
import main.java.FHJ.shelf.model.repos.ShelfRepository;

public class GetShelfElements extends BaseCommand implements Command {

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
		protected void internalExecute() throws CommandException 
		{	
			long shelfID = Long.parseLong(parameters.get(SID));
			Shelf shelf =  (Shelf) shelfRepo.getShelfById(shelfID);
			
			System.out.println(Arrays.toString(shelf.getInfoAboutAllElementsContained()));
		}

		@Override
		protected String[] getMandatoryParameters() {
			return DEMANDING_PARAMETERS;
		}
		
	}



