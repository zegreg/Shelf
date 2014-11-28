package utils;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * Defines objects of the type {@code Shelf} that will contain a limited number
 * of elements. These elements will be set of of zero or more Collections of
 * zero or more unitary {@code Elements} that will ocupy the free space existing
 * in the {@code Shelf}. The maximum free space of a {@code Shelf} will be
 * determined upont it's creation and cannot be changed.
 */
public class Shelf {

	/**
	 * @field collections - a {@link TreeSet} that will store the all the
	 *        collections added to a {@code Shelf} and make sure they are always
	 *        properly ordered (it will use the overriden {@code comapeTo}
	 *        methods of the class {@code Collection} to do that).
	 */
	private TreeSet<Collection> shelfCollections;

	/**
	 * @field freeSpace - current free space the {@code Shelf} has. It will
	 *        start being equal to the {@code Shelf}'s capacity.
	 */
	private final int capacity;
	private int freeSpace;

	/**
	 * Shelf constructor that will receive a int value as a parameter that
	 * represents the maximum number of {@code Elements} a {@code Shelf} can
	 * contain. When the {@code Shelf} is created none of it's space is
	 * occupied.
	 * 
	 * @param capacity
	 *            - maximum number of {@code Elements} a {@code Shelf} can have.
	 *            If it's less than 1 will throw an
	 *            {@link IllegalArgumentException}.
	 */
	public Shelf(int capacity) {

		if (capacity < 1)
			throw new IllegalArgumentException(
					"The Shelf must have a capacity bigger than 0");

		this.capacity = capacity;
		this.freeSpace = capacity;

		shelfCollections = new TreeSet<Collection>();
	}

	/**
	 * Method used to add a {@code Collection} to a {@code Shelf}. If the
	 * {@code Collection} given as parameter is null, if is already contained by
	 * the {@code Shelf} or if the number of {@code Elements} stored in the
	 * {@code Collection} are more than the space available in the {@code Shelf}
	 * the {@code Collection} is not added. A empty {@code Collection} cannot be
	 * added to a {@code Shelf}.
	 * 
	 * Updates the {@code Shelf}'s free space if true.
	 * 
	 * @param collection
	 *            - {@code Collection} of {@code Elements}.
	 * 
	 * @return - returns true if the {@code Collection} is added and false
	 *         otherwhise.
	 */
	public boolean addCollection(Collection collection) {

		if (collection == null || shelfCollections.contains(collection))
			return false;

		int length = collection.getCollection().size();

		if (length > freeSpace || length == 0)
			return false;

		shelfCollections.add(collection);
		freeSpace -= length;

		collection.setShelf(this);

		return true;
	}

	/**
	 * Method used to remove a {@code Collection} from a {@code Shelf}. If the
	 * {@code Collection} given as parameter is null, if it's not contained by
	 * the {@code Shelf} or if any of its elements is requested the
	 * {@code Collection} will not be removed.
	 * 
	 * Updates the {@code Shelf}'s free space if true.
	 * 
	 * @param collection
	 *            - {@code Collection} of {@code Elements}.
	 * 
	 * @return - returns true if the {@code Collection} is added and false
	 *         otherwhise.
	 */
	public boolean removeCollection(Collection collection) {

		if (collection == null)
			return false;

		if (shelfCollections.contains(collection) && collection.isAvailable()) {

			shelfCollections.remove(collection);
			freeSpace += collection.getCollection().size();

			collection.setShelf(null);

			return true;
		}

		return false;
	}

	/**
	 * Method used to find an {@code Element} belonging to the {@code Shelf}
	 * that is not requested given its title and type as parameters. If an
	 * {@code Element} with the corresponding title and type does not exist or
	 * if it's requested returns null.
	 * 
	 * In case there are two elements with the same name and type it will only
	 * be able to return the last one if the first one is requested (Case not
	 * contemplated by the app).
	 * 
	 * @param title
	 *            - String with the title of the {@code Element} to find.
	 * @param type
	 *            - String representation of the type of the {@code Element}.
	 * 
	 * @return element - returns the first {@code Element} with the same type
	 *         and title if it's found in the {@code Shelf} and null otherwhise.
	 */
	public Element findNotRequestedElement(String type, String title) {

		Iterator<Collection> iter = shelfCollections.iterator();

		while (iter.hasNext()) {

			Collection collection = iter.next();

			if (collection.getElementsType().equals(type)) {

				Iterator<Element> iter2 = collection.getCollection().iterator();

				while (iter2.hasNext()) {

					Element element = iter2.next();

					if (!element.isRequested())

						if (element.getTitle().equals(title))
							return element;
				}
			}
		}

		return null;
	}

	/**
	 * Method used to find an {@code Element} belonging to the {@code Shelf}
	 * that is requested given its title and type as parameters. If an
	 * {@code Element} with the corresponding title and type does not exist or
	 * if it's not requested returns null.
	 * 
	 * In case there are two elements with the same name and type it will only
	 * be able to return the last one if the first one is not requested (Case
	 * not contemplated by the app).
	 * 
	 * @param title
	 *            - String with the title of the {@code Element} to find.
	 * @param type
	 *            - String representation of the type of the {@code Element}.
	 * 
	 * @return element - returns the first {@code Element} with the same type
	 *         and title if it's found in the {@code Shelf} and null otherwhise.
	 */
	public Element findRequestedElement(String type, String title) {

		Iterator<Collection> iter = shelfCollections.iterator();

		while (iter.hasNext()) {

			Collection collection = iter.next();

			if (!collection.isAvailable()
					&& collection.getElementsType().equals(type)) {

				Iterator<Element> iter2 = collection.getCollection().iterator();

				while (iter2.hasNext()) {

					Element element = iter2.next();

					if (element.getTitle().equals(title)
							&& element.isRequested())
						return element;
				}
			}
		}

		return null;
	}

	/**
	 * Method used to request an {@code Element} that is stored in a
	 * {@code Shelf} if it's not already requested or if the {@code Collection}
	 * he belongs to is available. Reveices as parameters the title and type of
	 * the {@code Element}.
	 * 
	 * In case there are two elements with the same name and type it will only
	 * be able to request the last one if the first one is not requested (Case
	 * not contemplated by the app).
	 * 
	 * @param title
	 *            - String with the title of the {@code Element} to find.
	 * @param type
	 *            - String representation of the type of the {@code Element}.
	 * 
	 * @return element - returns the first {@code Element} found in the
	 *         {@code Shelf}, with the same title and type as the parameters
	 *         given, that can be requested or null otherwhise.
	 */
	public Element requestElement(String type, String title) {

		Iterator<Collection> iter = shelfCollections.iterator();

		while (iter.hasNext()) {

			Collection collection = iter.next();

			if (collection.isAvailable()
					&& collection.getElementsType().equals(type)) {

				Iterator<Element> iter2 = collection.getCollection().iterator();

				while (iter2.hasNext()) {

					Element element = iter2.next();

					if (element.getTitle().equals(title) && element.requestIt()) {

						collection.updateAvailability();
						return element;
					}
				}
			}
		}

		return null;
	}

	/**
	 * Method used to return an {@code Element} that is stored in a
	 * {@code Shelf} if it's already requested. Reveices as parameters the title
	 * and type of the {@code Element}.
	 * 
	 * In case there are two elements with the same name and type it will only
	 * be able to return the last one if the first one was already returned or
	 * wasn't requested (Case not contemplated by the app).
	 * 
	 * @param title
	 *            - String with the title of the {@code Element} to find.
	 * @param type
	 *            - String representation of the type of the {@code Element}.
	 * 
	 * @return element - returns the first {@code Element} found in the
	 *         {@code Shelf}, with the same title and type as the parameters
	 *         given, that can be returned or null otherwhise.
	 */
	public Element returnElement(String type, String title) {

		Iterator<Collection> iter = shelfCollections.iterator();

		while (iter.hasNext()) {

			Collection collection = iter.next();

			if (!collection.isAvailable()
					&& collection.getElementsType().equals(type)) {

				Iterator<Element> iter2 = collection.getCollection().iterator();

				while (iter2.hasNext()) {

					Element element = iter2.next();

					if (element.getTitle().equals(title) && element.returnIt()) {

						collection.updateAvailability();
						return element;
					}
				}
			}
		}

		return null;
	}

	/**
	 * Void method that will print all the information regarding all the
	 * elements stored in a {@code Shelf}.
	 */
	public void showShelf() {

		Iterator<Collection> iter = shelfCollections.iterator();

		while (iter.hasNext()) {

			Collection collection = iter.next();

			Iterator<Element> iter2 = collection.getCollection().iterator();

			System.out.println("\nCollection Title: "
					+ collection.getCollectionTitle());

			while (iter2.hasNext()) {

				Element element = iter2.next();

				element.printInformation();
				System.out.println("Is Avalable: " + collection.isAvailable());
			}
		}
	}

	/**
	 * @return freeSpace - returns the free space available in a {@code Shelf}.
	 */
	public int getFreeSpace() {
		return freeSpace;
	}

	/**
	 * Method that will allow us to alter the free space of a {@code Shelf} if
	 * we add or remove elements from a {@code Collection} that is already
	 * inside a {@code Shelf}.
	 * 
	 * Throws an IllegalArgumentException if someone tries to update the free
	 * space to be bigger that the capacity or lass than zero.
	 * 
	 * @param updatedFreeSpace
	 *            - a int variable that will contain the updated free space of
	 *            the {@code Shelf}.
	 */
	public void setFreeSpace(int updatedFreeSpace) {

		if (updatedFreeSpace > capacity)
			throw new IllegalArgumentException(
					"The Shelf capacity cannot be changed");

		if (updatedFreeSpace < 0)
			throw new IllegalArgumentException(
					"The Shelf free space cannot be less than 0");

		this.freeSpace = updatedFreeSpace;
	}

	/**
	 * @return selfCollections - returns the collections contained by a
	 *         {@code Shelf}.
	 */
	public TreeSet<Collection> getShelfCollections() {
		return shelfCollections;
	}
}
