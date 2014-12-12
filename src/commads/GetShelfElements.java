package commads;

import java.util.Arrays;
import java.util.Map;

import exceptions.CommandException;
import Database.ShelfRepository;
import afterSOLIDrevisionEHL.model.Shelf;

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
		protected String[] getDemandingParametres() {
			return DEMANDING_PARAMETERS;
		}
		
	}



