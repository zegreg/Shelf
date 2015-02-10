package fhj.shelf.actionCommandDomain;

import java.util.Map;
import java.util.TreeMap;

import fhj.shelf.commandsDomain.GetOneShelf;
import fhj.shelf.repos.ShelfRepository;
import fhj.shelf.utils.Shelf;

public class FindAShelf {

	private ShelfRepository shelfRepo = StandAloneDatabase.getShelfRepoInstance();

	private Map<String, String> parameters;

	private static final String SHELFID = "id";

	public FindAShelf(Map<String, String> parameters) {
		this.parameters = parameters;
	}

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
