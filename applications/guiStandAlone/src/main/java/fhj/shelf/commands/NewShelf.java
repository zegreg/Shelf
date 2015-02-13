package fhj.shelf.commands;

import java.util.Map;

import fhj.shelf.commands.UIPostCommand;
import fhj.shelf.commandsDomain.CreateShelf;
import fhj.shelf.database.StandAloneDatabase;
import fhj.shelf.factorys.CommandPostFactoryWithParameters;
import fhj.shelf.repos.ShelfRepository;
import fhj.shelf.utils.mutation.ShelfCreationDescriptor;

public class NewShelf implements UIPostCommand {

	public static class Factory implements CommandPostFactoryWithParameters {

		/**
		 * This is the constructor for the class above, it defines the factory
		 * 
		 * @param userRepo
		 *            is an instance of UserRepository
		 * @param shelfRepo
		 *            is an instance of ShelfRepository
		 */
		public Factory() {

		}

		/**
		 * This is an override method of the base class, it returns a new
		 * instance of PostShelf
		 */
		@Override
		public UIPostCommand newInstance(Map<String, String> parameters) {
			return new NewShelf(parameters);
		}
	}

	private Map<String, String> parameters;

	private static final String NBELEMENTS = "nbElements";

	private ShelfRepository shelfRepo = StandAloneDatabase
			.getShelfRepoInstance();

	public NewShelf(Map<String, String> parameters) {
		this.parameters = parameters;
	}

	@Override
	public String execute() throws NumberFormatException, Exception {
		return new CreateShelf(shelfRepo, new ShelfCreationDescriptor(
				Integer.valueOf(parameters.get(NBELEMENTS)))).call();
	}

}