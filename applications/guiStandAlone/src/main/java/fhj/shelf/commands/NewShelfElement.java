package fhj.shelf.commands;

import java.util.Map;

import fhj.shelf.commandsDomain.CreateAnElementInAShelf;
import fhj.shelf.commandsDomain.ElementCreationDescriptorWizard;
import fhj.shelf.factorys.CommandPostFactoryWithParameters;
import fhj.shelf.repos.ElementsRepository;
import fhj.shelf.repos.ShelfRepository;

public class NewShelfElement implements UIPostCommand {

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
			return new NewShelfElement(parameters);
		}
	}

	private Map<String, String> parameters;

	private static final String SHELFID = "sheldId";

	public static final String ELEMENT_TYPE = "elementType";

	private static final String NAME = "name";

	private static final String AUTHOR = "author";

	private static final String TRACKSNUMBER = "tracksnumber";

	private static final String DURATION = "duration";

	private ShelfRepository shelfRepo = StandAloneDatabase
			.getShelfRepoInstance();

	private ElementsRepository elementsRepo = StandAloneDatabase
			.getElementRepoInstance();

	public NewShelfElement(Map<String, String> parameters) {
		this.parameters = parameters;
	}

	@Override
	public String execute() throws NumberFormatException, Exception {
		String elementType = parameters.get(ELEMENT_TYPE);
		long shelfID = Long.valueOf(parameters.get(SHELFID));
		String name = parameters.get(NAME);
		String author = parameters.get(AUTHOR);

		int tracksNumber;
		if (parameters.get(TRACKSNUMBER) != null) {
			tracksNumber = Integer.valueOf(parameters.get(TRACKSNUMBER));
		} else {
			tracksNumber = 0;
		}

		int duration;
		if (parameters.get(DURATION) != null) {
			duration = Integer.valueOf(parameters.get(DURATION));
		} else {
			duration = 0;
		}

		return new CreateAnElementInAShelf(shelfRepo, elementsRepo, shelfID,
				new ElementCreationDescriptorWizard(elementType, name, author,
						tracksNumber, duration).create()).call();

	}

}
