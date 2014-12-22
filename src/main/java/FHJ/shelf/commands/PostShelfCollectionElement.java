package main.java.FHJ.shelf.commands;

import java.lang.reflect.Method;
import java.util.Map;

import main.java.FHJ.shelf.commands.exceptions.CommandException;
import main.java.FHJ.shelf.model.AbstractElement;
import main.java.FHJ.shelf.model.Book;
import main.java.FHJ.shelf.model.BookCollection;
import main.java.FHJ.shelf.model.CD;
import main.java.FHJ.shelf.model.CDCollection;
import main.java.FHJ.shelf.model.DVD;
import main.java.FHJ.shelf.model.DVDCollection;
import main.java.FHJ.shelf.model.Shelf;
import main.java.FHJ.shelf.model.repos.ElementsRepository;
import main.java.FHJ.shelf.model.repos.ShelfRepository;
import main.java.FHJ.shelf.model.repos.UserRepository;

public class PostShelfCollectionElement extends BasePostCommand implements
		Command {

	public static final String ELEMENT_TYPE = "elementType";

	private static final String NAME = "name";

	private static final String AUTHOR = "author";

	private static final String TRACKSNUMBER = "tracksnumber";

	private static final String DURATION = "duration";

	/**
	 * Class that implements the {@link GetProducts} factory, according to the
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
			return new PostShelfCollectionElement(userRepo, shelfRepo,
					elementsRepo, parameters);
		}

	}

	private final ShelfRepository shelfRepo;

	private final ElementsRepository elementsRepo;

	public static String SID = "sid";
	public static String EID = "eid";

	public static final String[] DEMANDING_PARAMETERS = { SID, EID,
			ELEMENT_TYPE, NAME };

	/**
	 * 
	 * @param repository
	 * @param id
	 */
	private PostShelfCollectionElement(UserRepository userRepo,
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
	protected void validLoginPostExecute() throws CommandException {
		// forgive me god of java but its hammer time
		// https://www.youtube.com/watch?v=otCpCn0l4Wo

		String elementType = parameters.get(ELEMENT_TYPE);
		String name = parameters.get(NAME);

		AbstractElement p = null;

		String methodNameToCreateElement = "create" + elementType;

		Class<? extends PostShelfCollectionElement> c = this.getClass();

		Method creatorMethodToCreate;
		try {
			creatorMethodToCreate = c.getDeclaredMethod(
					methodNameToCreateElement, String.class);
			p = (AbstractElement) creatorMethodToCreate.invoke(this, name);
		} catch (Exception e) {
			throw new CommandException("Error finding method to create a "
					+ elementType, e);
		}

		elementsRepo.insert(p);

		System.out.println(new StringBuilder("ElementID: ").append(p.getId()));

		String methodNameToAddElement = "addTo" + elementType + "Collection";

		Class<? extends PostShelfCollectionElement> d = this.getClass();

		Method creatorMethodToAdd;
		try {
			creatorMethodToAdd = d.getDeclaredMethod(methodNameToAddElement,
					AbstractElement.class);
			creatorMethodToAdd.invoke(this, p);
		} catch (Exception e) {
			throw new CommandException("Error finding method to create a "
					+ elementType, e);
		}

	}

	@SuppressWarnings("unused")
	private AbstractElement createCD(String name) {
		int tracksNumber = getParameterAsInt(TRACKSNUMBER);
		return new CD(name, tracksNumber);
	}

	@SuppressWarnings("unused")
	private AbstractElement createDVD(String name) {
		int duration = getParameterAsInt(DURATION);
		return new DVD(name, duration);
	}

	@SuppressWarnings("unused")
	private AbstractElement createBook(String name) {
		return new Book(name, this.getParameterAsString(AUTHOR));
	}

	@SuppressWarnings("unused")
	private AbstractElement createCDCollection(String name) {
		return new CDCollection(name);
	}

	@SuppressWarnings("unused")
	private AbstractElement createDVDCollection(String name) {
		return new DVDCollection(name);
	}

	@SuppressWarnings("unused")
	private AbstractElement createBookCollection(String name) {
		return new BookCollection(name);
	}

	@SuppressWarnings("unused")
	private void addToCDCollection(AbstractElement element) {
		long eid = Long.parseLong(parameters.get(EID));
		DVDCollection col = (DVDCollection) elementsRepo.getElementById(eid);

		long sid = Long.parseLong(parameters.get(SID));
		Shelf shelf = (Shelf) shelfRepo.getShelfById(sid);

		shelf.remove(col);

		col.addElement((DVD) element);
	}

	@SuppressWarnings("unused")
	private void addToDVDCollection(AbstractElement element) {
		long eid = Long.parseLong(parameters.get(EID));
		DVDCollection col = (DVDCollection) elementsRepo.getElementById(eid);

		long sid = Long.parseLong(parameters.get(SID));
		Shelf shelf = (Shelf) shelfRepo.getShelfById(sid);

		shelf.remove(col);

		col.addElement((DVD) element);
	}

	@SuppressWarnings("unused")
	private void addToBookCollection(AbstractElement element) {

		long eid = Long.parseLong(parameters.get(EID));
		BookCollection col = (BookCollection) elementsRepo.getElementById(eid);

		long sid = Long.parseLong(parameters.get(SID));
		Shelf shelf = (Shelf) shelfRepo.getShelfById(sid);

		shelf.remove(col);

		col.addElement((Book) element);

		shelf.add(col);
	}

}