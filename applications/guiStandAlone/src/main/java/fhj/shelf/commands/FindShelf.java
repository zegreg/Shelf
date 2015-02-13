package fhj.shelf.commands;

import java.util.Map;
import java.util.TreeMap;

import fhj.shelf.commands.UIGetCommand;
import fhj.shelf.commandsDomain.GetOneShelf;
import fhj.shelf.database.StandAloneDatabase;
import fhj.shelf.factorys.CommandGetFactoryWithParameters;
import fhj.shelf.repos.ShelfRepository;
import fhj.shelf.utils.Shelf;

public class FindShelf implements UIGetCommand {

	public static class Factory implements CommandGetFactoryWithParameters {

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
		public UIGetCommand newInstance(Map<String, String> parameters) {
			return new FindShelf(parameters);
		}
	}

	private ShelfRepository shelfRepo = StandAloneDatabase
			.getShelfRepoInstance();

	private Map<String, String> parameters;

	private static final String SHELFID = "id";

	public FindShelf(Map<String, String> parameters) {
		this.parameters = parameters;
	}

	@Override
	public Map<String, String> execute() throws NumberFormatException,
			Exception {
		return formatExecuter(new GetOneShelf(shelfRepo,
				Long.valueOf(parameters.get(SHELFID))).call());
	}

	public Map<String, String> formatExecuter(Shelf shelf) {

		Map<String, String> map = new TreeMap<String, String>();

		map.put("Capacity", String.valueOf(shelf.getCapacity()));
		map.put("FreeSpace", String.valueOf(shelf.getCapacity()));

		return map;
	}

}
