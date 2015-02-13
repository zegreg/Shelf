package fhj.shelf.commands;

import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import model.fhj.shelf.model.Shelf;
import fhj.shelf.commandsDomain.GetAllShelfs;
import fhj.shelf.database.StandAloneDatabase;
import fhj.shelf.factorys.CommandGetFactoryWithoutParameters;
import fhj.shelf.inMemoryRepositories.ShelfRepository;

public class FindAllShelfs implements UIGetCommand {

	public static class Factory implements CommandGetFactoryWithoutParameters {

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
		public UIGetCommand newInstance() {
			return new FindAllShelfs();
		}
	}

	private ShelfRepository shelfRepo = StandAloneDatabase
			.getShelfRepoInstance();

	public FindAllShelfs() {

	}

	@Override
	public Map<String, String> execute() throws NumberFormatException,
			Exception {
		return formatExecuter(new GetAllShelfs(shelfRepo).call());
	}

	public Map<String, String> formatExecuter(Map<Long, Shelf> commandResult) {

		// [{"Shelf_id=01":"Elements:0&FreeSpace:10"}]}

		Map<String, String> map = new TreeMap<String, String>();
		map.put("Shelfs Container", null);

		for (Entry<Long, Shelf> entry : commandResult.entrySet()) {

			String shelfID = "Shelf_id=" + String.valueOf(entry.getKey());

			String shelfDetails = entry.getValue().details();

			map.put(shelfID, shelfDetails);
		}
		return map;
	}

}
