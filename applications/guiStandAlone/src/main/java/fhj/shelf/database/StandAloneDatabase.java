package fhj.shelf.database;

import fhj.shelf.inMemoryRepositories.ElementsRepository;
import fhj.shelf.inMemoryRepositories.InMemoryElementsRepository;
import fhj.shelf.inMemoryRepositories.InMemoryShelfRepository;
import fhj.shelf.inMemoryRepositories.InMemoryUserRepository;
import fhj.shelf.inMemoryRepositories.ShelfRepository;
import fhj.shelf.repositories.UserRepository;


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
