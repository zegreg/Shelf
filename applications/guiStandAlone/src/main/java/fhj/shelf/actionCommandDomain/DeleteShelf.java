package fhj.shelf.actionCommandDomain;

import java.util.Map;

import fhj.shelf.commandsDomain.EraseShelf;
import fhj.shelf.repos.ShelfRepository;

public class DeleteShelf {

	public static String DeleteShelfInformation(
			ShelfRepository shelfRepository, Map<String, String> params)
			throws Exception {

		return new EraseShelf(shelfRepository, Long.valueOf(params.get("id")))
				.call();
	}
}