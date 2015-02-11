package fhj.shelf.actionCommandDomain;

import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

import fhj.shelf.commandsDomain.GetAllShelfs;
import fhj.shelf.repos.InMemoryShelfRepository;
import fhj.shelf.repos.ShelfRepository;
import fhj.shelf.utils.Shelf;

public class FindAllShelfs implements StandAloneCommand {

	private ShelfRepository shelfRepo = new InMemoryShelfRepository();

	public FindAllShelfs() {

	}

	public Map<String, String> execute() throws NumberFormatException,
			Exception {
		return formatExecuter(new GetAllShelfs(shelfRepo).call());
	}

	public Map<String, String> formatExecuter(Map<Long, Shelf> commandResult) {

		//[{"Shelf_id=01":"Elements:0&FreeSpace:10"}]}
	
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
