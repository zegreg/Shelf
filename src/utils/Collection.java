package utils;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * {@code Abstract} class that implements the {@link Comparable} interface.
 * 
 * Since this is an abstract class we cannot create objects of this type but we
 * can still use it to add properties and fields to its subclasses.
 */
public abstract class Collection implements Comparable<Collection> {

	/**
	 * @field collectionTitle - title of the {@code Collection}.
	 * @field elementsType - String representation of the type of elements
	 *        {@code Collection} the can contain.
	 */
	private final String collectionTitle;
	private final String elementsType;

	/**
	 * @field elements - {@link TreeSet} that will store the elements added to
	 *        the {@code Collection} and make sure they are always properly
	 *        ordered (it will use the overriden {@code comapeTo} methods to do
	 *        that).
	 */
	private TreeSet<Element> elements;

	/**
	 * @field available - boolean variable that will allow us to determine if a
	 *        {@code Collection} contained in a {@code Shelf} is available or
	 *        not. A collection is available only if none of its elements is
	 *        requested. The default value will be false.
	 */
	private boolean available;

	/**
	 * @field shelf - reference to the {@code Shelf} were the {@code Collection}
	 *        will be stored. If the {@code Collection} isn't in a {@code Shelf}
	 *        it will be null.
	 */
	private Shelf shelf;

	/**
	 * {@code Constructor} that will be used by its subclasses to add
	 * characteristics to their objects. If the {@code collectionTitle} received
	 * as parameter is null it will throw an {@link IllegalArgumentException}.
	 * 
	 * @param collectionTitle
	 *            - title of the {@code Collection}.
	 * @param type
	 *            - String representation of the type of elements
	 *            {@code Collection} the can contain.
	 */
	public Collection(String collectionTitle, String type) {

		if (collectionTitle == null)
			throw new IllegalArgumentException("The title cannot be null!");

		this.collectionTitle = collectionTitle;
		this.elementsType = type;

		elements = new TreeSet<Element>();
		available = true;
		shelf = null;
	}

	/**
	 * Method that will update the availability of a {@code Collection}
	 * everytime one of its elements is requested or returned.
	 */
	public void updateAvailability() {

		if (available)
			available = false;
		else
			available = true;
	}

	/**
	 * Override of the method {@link compareTo}, from the {@link comparable}
	 * interface and used by the {@link TreeSet} class to automaticaly organize
	 * the its elements, that will allow the collections to be alphabetically
	 * organized by their title in a {@link Shelf}.
	 * 
	 * If their title is the same they will be organized alphabetically by their
	 * type. This is achieved by using the {@code compareTo} method of the
	 * String class to compare the String representation of the type of elements
	 * the collections can store.
	 * 
	 * If both their title and type are the same, they will be ordered by
	 * ascending order of size or, if the size is also the same, by using the
	 * same {@code compareTo} evaluation of their first different element.(Even
	 * tho this cases are considered here, the created app doesn't allow the
	 * creation of two collections with the same name and type!!!).
	 * 
	 * If the Element given as parameter is null will throw an
	 * {@code IllegalArgumentException}.
	 * 
	 * @param collection
	 *            - an {@code Collection} must be given as parameter.
	 * 
	 * @return - returns an int number that will represent the position the
	 *         collection will have relative to another in a Collection. If it's
	 *         less than 0 it will come first and if it's bigger than zero it
	 *         will come after.
	 */
	@Override
	public int compareTo(Collection collection) {

		if (collection == null)
			throw new IllegalArgumentException("The collection cannot be null!");

		int compareTitle = this.collectionTitle.compareTo(collection
				.getCollectionTitle());

		if (compareTitle != 0)
			return compareTitle;

		int compareType = this.elementsType.compareTo(collection.elementsType);

		if (compareType != 0)
			return compareType;

		int compareSize = this.getCollection().size()
				- collection.getCollection().size();

		if (compareSize != 0)
			return compareSize;

		Iterator<Element> iter = this.getCollection().iterator();
		Iterator<Element> iter2 = collection.getCollection().iterator();

		while (iter.hasNext()) {

			Element thisElement = iter.next();
			Element collectionElement = iter2.next();

			// Collections are always ordered!!!
			int compare = thisElement.compareTo(collectionElement);

			if (compare != 0)
				return compare;
		}

		return 0;
	}

	/**
	 * Override of the method {@link hashCode} from the class {@link Object}.
	 */
	@Override
	public int hashCode() {

		final int prime = 31;
		int result = 1;

		result = prime * result + (available ? 1231 : 1237);

		result = prime * result
				+ ((collectionTitle == null) ? 0 : collectionTitle.hashCode());

		result = prime * result
				+ ((elements == null) ? 0 : elements.hashCode());

		result = prime * result
				+ ((elementsType == null) ? 0 : elementsType.hashCode());

		result = prime * result + ((shelf == null) ? 0 : shelf.hashCode());

		return result;
	}

	/**
	 * Override of the method {@link equals} from the class {@code Object} to be
	 * consistent with the {@code comparaTo} method.
	 */
	@Override
	public boolean equals(Object collection) {

		if (this == collection)
			return true;

		if (collection == null)
			return false;

		if (!getClass().equals(collection.getClass()))
			return false;

		if (this.compareTo((Collection) collection) != 0)
			return false;

		return true;
	}

	/**
	 * Method that will allow an {@code Element} to be removed from a
	 * {@code Collection}. If the given parameter is null, if it's not contained
	 * by the {@code Collection} or if the collection is not available no
	 * element will be removed and will return false. In case the
	 * {@code Collection} is in a {@code Shelf} it will also free one space in
	 * it.
	 * 
	 * If the {@code Collection} already contains elements from a specific type
	 * and an element of another type tries to be removed it will throw a
	 * {@link ClassCastException}.
	 * 
	 * If the removed {@code Element} is the last {@code Element} of the
	 * {@code Collection} the {@code Collection} will be removed from the
	 * {@code Shelf}.
	 * 
	 * @param element
	 *            - an {@code Element} must be given as parameter.
	 * 
	 * @return - returns true if the {@code Element} is successfully removed and
	 *         false otherwhise.
	 */
	public boolean removeElement(Element element) {

		if (element == null || !this.getCollection().contains(element))
			return false;

		if (this.getShelf() != null && !this.isAvailable())
			return false;

		this.getCollection().remove(element);

		if (this.getShelf() != null)
			this.getShelf().setFreeSpace(this.getShelf().getFreeSpace() + 1);

		if (this.getShelf() != null && this.getCollection().size() == 0)
			this.getShelf().removeCollection(this);
			
		return true;
	}

	/**
	 * {@code Abstract} method that will be implemented by the subclasses of
	 * this class. It will have the purpose of adding an {@code Element} to a
	 * specific {@code Collection}.
	 */
	public abstract boolean addElement(Element element);

	/**
	 * @return elements - returns the elements contained by a {@code Collection}
	 *         .
	 */
	public TreeSet<Element> getCollection() {
		return elements;
	}

	/**
	 * @return elementsType - returns a String representation of the type of
	 *         elements {@code Collection} the can contain.
	 */
	public String getElementsType() {
		return elementsType;
	}

	/**
	 * @return available - returns the availability status of a
	 *         {@code Collection}.
	 */
	public boolean isAvailable() {
		return available;
	}

	/**
	 * @return collectionTitle - returns the title of the {@code Collection}.
	 */
	public String getCollectionTitle() {
		return collectionTitle;
	}

	/**
	 * 
	 * @return shlef = returns a reference to the {@code Shelf} where the
	 *         {@code Collection} is contained. If the {@code Collection} is not
	 *         in a one it will be null;
	 */
	public Shelf getShelf() {
		return shelf;
	}

	/**
	 * Method that will allow us to alter {@code Collection} field shelf in case
	 * the {@code Collection} is added to or removed from a {@code Shelf}.
	 * 
	 * @param shelf
	 *            - receives a {@code Shelf} has a parameter.
	 */
	void setShelf(Shelf shelf) {
		this.shelf = shelf;
	}
}