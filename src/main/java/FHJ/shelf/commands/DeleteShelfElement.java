package main.java.FHJ.shelf.commands;

import java.util.Map;

import main.java.FHJ.shelf.commands.exceptions.CommandException;
import main.java.FHJ.shelf.model.Element;
import main.java.FHJ.shelf.model.Shelf;
import main.java.FHJ.shelf.model.repos.ElementsRepository;
import main.java.FHJ.shelf.model.repos.ShelfRepository;
import main.java.FHJ.shelf.model.repos.UserRepository;

public class DeleteShelfElement extends BasePostCommand implements Command {

	/**
	 * Class that implements the {@link PostElement} factory, according to the
	 * AbstratFactory design pattern.
	 */
	public static class Factory implements CommandFactory {

		private final ShelfRepository shelfRepo;
		private final ElementsRepository elementsRepo;
		private final UserRepository userRepo;

		public Factory(UserRepository userRepo, ShelfRepository shelfRepo,
				ElementsRepository elementsRepo) {
			this.userRepo = userRepo;
			this.shelfRepo = shelfRepo;
			this.elementsRepo = elementsRepo;
		}

		@Override
		public Command newInstance(Map<String, String> parameters) {
			return new DeleteShelfElement(userRepo, shelfRepo, elementsRepo,
					parameters);
		}

	}

	private final ShelfRepository shelfRepo;
	private final ElementsRepository elementsRepo;

	public static final String SID = "sid";

	public static final String EID = "eid";
	// private final long shelfId;

	private static final String[] DEMANDING_PARAMETERS = { SID, EID };

	/**
	 * 
	 * @param repository
	 * @param id
	 */
	private DeleteShelfElement(UserRepository userRepo,
			ShelfRepository shelfRepo, ElementsRepository elementsRepo,
			Map<String, String> parameters) {
		super(userRepo, parameters);
		this.shelfRepo = shelfRepo;
		this.elementsRepo = elementsRepo;
	}

	@Override
	protected String[] getMandatoryParameters() {
		return DEMANDING_PARAMETERS;
	}

	@Override
	protected String validLoginPostExecute() throws CommandException {
		long shelfID = Long.parseLong(parameters.get(SID));
		Shelf shelf = (Shelf) shelfRepo.getShelfById(shelfID);

		long elementsID = Long.parseLong(parameters.get(EID));
		Element element = (Element) elementsRepo.getElementById(elementsID);

		String result = "";

		if (shelf.remove(element)) {
			result = "Element successful remove from shelf ";
		}
		return result;
	}

}

