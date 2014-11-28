package original.app;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import original.utils.Book;
import original.utils.BooksCollection;
import original.utils.CD;
import original.utils.CDsCollection;
import original.utils.Collection;
import original.utils.DVD;
import original.utils.DVDsCollection;
import original.utils.Element;
import original.utils.Shelf;

public class App {

	private static final String CREATE_ELEMENT = "create element";
	private static final String MANAGE_COLLECTIONS = "manage collections";
	private static final String MANAGE_SHELF = "manage shelf";
	private static final String HELP = "help";
	private static final String END_APP = "quit";

	private static final String CREATE_COLLECTION = "create collection";
	private static final String ADD_ELEMENT = "add element";
	private static final String REMOVE_ELEMENT = "remove element";
	private static final String BACK = "back";
	private static final String VERIFY_SIZE = "verify size";

	private static final String ADD_COLLECTION = "add collection";
	private static final String REMOVE_COLLECTION = "remove collection";
	private static final String FIND_NOT_REQUESTED_ELEMENT = "find not requested element";
	private static final String FIND_REQUESTED_ELEMENT = "find requested element";
	private static final String REQUEST_ELEMENT = "request element";
	private static final String RETURN_ELEMENT = "return element";
	private static final String SHOW_SHELF = "show shelf";
	private static final String VERIFY_FREE_SPACE = "verify free space";

	private String[] commands = { CREATE_ELEMENT, MANAGE_COLLECTIONS,
			MANAGE_SHELF, HELP, END_APP };

	private String[] collectionsCommands = { CREATE_COLLECTION, ADD_ELEMENT,
			REMOVE_ELEMENT, VERIFY_SIZE, HELP, BACK };

	private String[] shelfCommands = { ADD_ELEMENT, ADD_COLLECTION,
			REMOVE_COLLECTION, FIND_NOT_REQUESTED_ELEMENT,
			FIND_REQUESTED_ELEMENT, REQUEST_ELEMENT, RETURN_ELEMENT,
			SHOW_SHELF, VERIFY_FREE_SPACE, HELP, BACK };

	private Scanner scanner;
	private Shelf shelf;

	private List<Element> elements;
	private List<Collection> collections;

	private Collection collection;
	private Element element;

	private String type;
	private String title;

	public App() {

		elements = new ArrayList<Element>();
		collections = new ArrayList<Collection>();

		scanner = new Scanner(System.in);

		System.out.println("Please insert the maximum capacity of the shelf:");
		shelf = new Shelf(scanner.nextInt());
		System.out.print(scanner.nextLine());
	}

	public static void main(String[] args) {

		App app = new App();
		app.execute();
	}

	public void execute() {

		boolean exit = false;

		while (!exit) {

			showCommands(0);

			String command = readCommand();
			switch (command) {

			case CREATE_ELEMENT:

				if ((element = createElement()) != null)
					elements.add(element);

				break;

			case MANAGE_COLLECTIONS:

				executeManageCollections();

				break;

			case MANAGE_SHELF:

				executeManageShelf();

				break;

			case HELP:

				showCommands(0);
				break;

			case END_APP:

				exit = true;
				break;

			default:

				System.out.println("Invalid command");
			}
		}
	}

	private void executeManageCollections() {

		boolean back = false;

		while (!back) {

			showCommands(1);

			String command = readCommand();

			switch (command) {

			case CREATE_COLLECTION:

				if ((collection = createCollection()) != null)
					collections.add(collection);

				break;

			case ADD_ELEMENT:

				if ((collection = findCollection()) == null)
					break;

				if ((element = findElement(collection.getElementsType())) == null)
					break;

				if (collection.getCollection().contains(element)) {
					System.out
							.println("Element already exists in the Collection");
					break;
				}

				if (!collection.addElement(element))
					System.out.println("Shelf is full, Element not added");

				break;

			case REMOVE_ELEMENT:

				if ((collection = findCollection()) == null)
					break;

				if ((element = findElement(collection.getElementsType())) == null)
					break;

				if (!collection.removeElement(element))
					System.out
							.println("Element doesn't exist in the Collection or Collection not available");
				else if (collection.getCollection().size() == 0)
					System.out
							.println("Collection is empty and was removed from the Shelf");

				break;

			case VERIFY_SIZE:

				if ((collection = findCollection()) == null)
					break;

				System.out.println("Collection size: "
						+ collection.getCollection().size());

				break;

			case HELP:

				showCommands(1);
				break;

			case BACK:

				back = true;
				break;

			default:

				System.out.println("Invalid command");
			}
		}
	}

	private void executeManageShelf() {

		boolean back = false;

		while (!back) {

			showCommands(2);

			String command = readCommand();

			switch (command) {

			case ADD_ELEMENT:

				AddElementToShelf();

				break;

			case ADD_COLLECTION:

				if ((collection = findCollection()) == null)
					break;

				if (!shelf.addCollection(collection)) {
					if (collection.getCollection().size() == 0) {

						System.out.println("Collection is empty");
						break;
					}

					if (collection.getCollection().size() > shelf
							.getFreeSpace())
						System.out.println("Collection is too big");
					else
						System.out
								.println("Collection is already in the Shelf");
				}

				break;

			case REMOVE_COLLECTION:

				if ((collection = findCollection()) == null)
					break;

				if (!shelf.removeCollection(collection))
					if (!collection.isAvailable())
						System.out.println("Collection is not available");
					else
						System.out
								.println("Collection doesn't exist in the Shelf");

				break;

			case FIND_NOT_REQUESTED_ELEMENT:

				decideElementTypeAndTitle();

				if ((element = shelf.findNotRequestedElement(type, title)) == null) {
					if (element != null && element.isRequested())
						System.out.println("Element is requested");
					else
						System.out
								.println("Element doesn't exist in the Shelf");

				} else
					element.printInformation();

				break;

			case FIND_REQUESTED_ELEMENT:

				decideElementTypeAndTitle();

				if ((element = shelf.findRequestedElement(type, title)) == null) {
					if (element != null && !element.isRequested())
						System.out.println("Element is not requested");
					else
						System.out
								.println("Element doesn't exist in the Shelf");

				} else
					element.printInformation();

				break;

			case REQUEST_ELEMENT:

				decideElementTypeAndTitle();

				if ((element = shelf.requestElement(type, title)) == null)
					System.out
							.println("Element doesn't exist in the Shelf or Collection is not available");
				else
					element.printInformation();

				break;

			case RETURN_ELEMENT:

				decideElementTypeAndTitle();

				if ((element = shelf.returnElement(type, title)) == null) {
					if (element != null && !element.isRequested())
						System.out.println("Element is not requested");
					else
						System.out
								.println("Element doesn't exist in the Shelf");

				} else
					element.printInformation();

				break;

			case SHOW_SHELF:

				shelf.showShelf();

				break;

			case VERIFY_FREE_SPACE:

				System.out.println("Shelf free space: " + shelf.getFreeSpace());

				break;

			case HELP:

				showCommands(2);
				break;

			case BACK:

				back = true;
				break;

			default:

				System.out.println("Invalid command");
			}
		}
	}

	private void AddElementToShelf() {

		System.out.println("Type of Element ('Book', 'CD' or 'DVD')?");
		type = scanner.nextLine();

		if ((element = findElement(type)) != null) {

			if (type.equals("Book"))
				collection = new BooksCollection(title);

			if (type.equals("CD"))
				collection = new CDsCollection(title);

			if (type.equals("DVD"))
				collection = new DVDsCollection(title);
		}

		collection.addElement(element);
		
		if (!shelf.addCollection(collection))
			if (collection.getCollection().size() > shelf.getFreeSpace())
				System.out.println("Shefl is full, Element not added");
	}

	private void showCommands(int menu) {

		if (menu == 0)
			for (int i = 0; i < commands.length; i++)
				System.out.println(((i == 0) ? "\n" : "") + commands[i]
						+ ((i == commands.length - 1) ? "\n" : ""));

		if (menu == 1)
			for (int i = 0; i < collectionsCommands.length; i++)
				System.out.println(((i == 0) ? "\n" : "")
						+ collectionsCommands[i]
						+ ((i == collectionsCommands.length - 1) ? "\n" : ""));

		if (menu == 2)
			for (int i = 0; i < shelfCommands.length; i++)
				System.out.println(((i == 0) ? "\n" : "") + shelfCommands[i]
						+ ((i == shelfCommands.length - 1) ? "\n" : ""));
	}

	private String readCommand() {

		return scanner.nextLine();
	}

	private Element createElement() {

		decideElementTypeAndTitle();

		element = findElement(type, title);

		if (element != null) {
			System.out
					.println("Element with the same Title and Type already exists");
			return null;
		}

		if (type.equals("Book")) {

			System.out.println("Author?");
			return new Book(title, scanner.nextLine());
		}

		if (type.equals("CD")) {

			System.out.println("Number of Tracks?");
			int tracks = scanner.nextInt();
			System.out.print(scanner.nextLine());

			return new CD(title, tracks);
		}

		if (type.equals("DVD")) {

			System.out.println("Duration of the DVD?");
			int duration = scanner.nextInt();
			System.out.print(scanner.nextLine());

			return new DVD(title, duration);
		}

		System.out.println("Invalid Type");
		return null;
	}

	private void decideElementTypeAndTitle() {

		System.out.println("Type of Element ('Book', 'CD' or 'DVD')?");
		type = scanner.nextLine();

		System.out.println("Title?");
		title = scanner.nextLine();
	}

	private Element findElement(String type, String title) {

		for (int i = 0; i < elements.size(); i++) {
			if (elements.get(i).getTitle().equals(title)
					&& elements.get(i).getType().equals(type))
				return elements.get(i);
		}

		return null;
	}

	private Element findElement(String type) {

		System.out.println("Element Title?");
		String title = scanner.nextLine();

		for (int i = 0; i < elements.size(); i++) {
			if (elements.get(i).getTitle().equals(title)
					&& elements.get(i).getType().equals(type))
				return elements.get(i);
		}

		System.out.println("No Element found for the given parameters");
		return null;
	}

	private Collection createCollection() {

		decideCollectionTypeAndTitle();

		collection = findCollection(type, title);

		if (collection != null) {
			System.out
					.println("Collection with the same Title and Type already exists");
			return null;
		}

		if (type.equals("Book"))
			return new BooksCollection(title);

		if (type.equals("CD"))
			return new CDsCollection(title);

		if (type.equals("DVD"))
			return new DVDsCollection(title);

		System.out.println("Invalid Type");
		return null;
	}

	private void decideCollectionTypeAndTitle() {

		System.out.println("Type of Collection ('Book', 'CD' or 'DVD')?");
		type = scanner.nextLine();

		System.out.println("Collection Title?");
		title = scanner.nextLine();
	}

	private Collection findCollection(String type, String title) {

		for (int i = 0; i < collections.size(); i++) {
			if (collections.get(i).getCollectionTitle().equals(title)
					&& collections.get(i).getElementsType().equals(type))
				return collections.get(i);
		}

		return null;
	}

	private Collection findCollection() {

		decideCollectionTypeAndTitle();

		for (int i = 0; i < collections.size(); i++) {
			if (collections.get(i).getCollectionTitle().equals(title)
					&& collections.get(i).getElementsType().equals(type))
				return collections.get(i);
		}

		System.out.println("No Collection found for the given parameters");
		return null;
	}
}