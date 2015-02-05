package fhj.shelf.commandsDomain;

import java.util.Map;
import java.util.concurrent.Callable;

import fhj.shelf.repos.ShelfRepository;
import fhj.shelf.utils.Shelf;

/**
 * Class whose instances represent the command that gets all shelfs that are in
 * a shelf repository
 * 
 * @author Filipa Estiveira, Hugo Leal, José Oliveira
 */
public class GetAllShelfs implements Callable<Map<Long, Shelf>> {

	/**
	 * Holds the associated shelf repository
	 */
	private final ShelfRepository shelfRepository;

	/**
	 * Creates a command instance with the given shelf repository
	 * 
	 * @param shelfRepo
	 */
	public GetAllShelfs(ShelfRepository shelfRepo) {
		this.shelfRepository = shelfRepo;
	}

	/**
	 * 
	 * @return all the shelfs that are in the repository
	 */
	@Override
	public Map<Long, Shelf> call() throws Exception {
		return shelfRepository.getShelfs();
	}
}
