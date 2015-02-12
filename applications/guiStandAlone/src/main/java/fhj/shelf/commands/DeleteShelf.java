package fhj.shelf.commands;

import java.util.Map;

import fhj.shelf.commandsDomain.EraseShelf;
import fhj.shelf.factorys.CommandPostFactoryWithParameters;
import fhj.shelf.repos.ShelfRepository;

public class DeleteShelf implements UIPostCommand {

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
			return new DeleteShelf(parameters);
		}
	}

	private Map<String, String> parameters;

	private static final String SHELFID = "id";

	private ShelfRepository shelfRepo = StandAloneDatabase
			.getShelfRepoInstance();

	public DeleteShelf(Map<String, String> parameters) {
		this.parameters = parameters;
	}

	@Override
	public String execute() throws NumberFormatException, Exception {
		return new EraseShelf(shelfRepo, Long.valueOf(parameters.get(SHELFID)))
				.call();
	}
}