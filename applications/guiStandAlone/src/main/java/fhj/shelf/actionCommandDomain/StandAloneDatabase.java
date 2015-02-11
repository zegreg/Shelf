package fhj.shelf.actionCommandDomain;

import fhj.shelf.repos.ElementsRepository;
import fhj.shelf.repos.InMemoryElementsRepository;
import fhj.shelf.repos.InMemoryShelfRepository;
import fhj.shelf.repos.InMemoryUserRepository;
import fhj.shelf.repos.ShelfRepository;
import fhj.shelf.repos.UserRepository;

public final class StandAloneDatabase {

	private static ShelfRepository shelfRepo;

	private static ElementsRepository elementsRepo;

	private static UserRepository usersRepo;

	private StandAloneDatabase() {

	}

	public static ShelfRepository getShelfRepoInstance() {
		if (shelfRepo == null) {
			shelfRepo = new InMemoryShelfRepository();
		}
		return shelfRepo;
	}

	public static UserRepository getUserRepoInstance() {
		if (usersRepo == null) {
			usersRepo = new InMemoryUserRepository();
		}
		return usersRepo;
	}

	public static ElementsRepository getElementRepoInstance() {
		if (elementsRepo == null) {
			elementsRepo = new InMemoryElementsRepository();
		}
		return elementsRepo;
	}
}
