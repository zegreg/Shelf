package original.utils;
public class BooksCollection extends Collection {

	/**
	 * BooksCollection constructor that allows the creation of instances of
	 * BooksCollection. This Collections will be a set of zero or more Books.
	 * 
	 * It uses the constructor of it's super class {@code Collection} to create
	 * and associate to the created object two Strings, that will contain the
	 * title of the Collection and a representation of the type of elements it
	 * can store, and a boolean variable, that will represent the status of the
	 * Collection in a Shelf (if it's available or not).
	 * 
	 * It will alse create a {@link TreeSet} where the Collection elements will
	 * be stored and a {@code Shelf} variable that, in case the Collection is in
	 * a Shelf, will have a reference to it, enabling us to change, if needed,
	 * the Shelf free space. This will only happen if we add or remove elements
	 * from a Collection already in a Shelf.
	 * 
	 * @param collectionTitle
	 *            - String containing the title of the {@code Collection}.
	 */
	public BooksCollection(String collectionTitle) {

		super(collectionTitle, "Book");
	}

	/**
	 * Implementation of the {@code addElement} method from the
	 * {@code Collection} class that will have the purpose of adding an
	 * {@code Element} to a specific {@code BooksCollection}. This will only
	 * happen if the {@code Element} is a {@code Book}, if it's not null and if
	 * it's not contained already by the {@code Collection}. In case the
	 * {@code Collection} is already in a {@code Shelf} the {@code Book} will
	 * only be added if the {@code Shelf} has free space available.
	 * 
	 * @param book
	 *            - must receive a {@code Book} as a parameter.
	 * 
	 * @return - return true if the {@code Book} is added to the
	 *         {@code BooksCollection} and false otherwhise.
	 */
	@Override
	public boolean addElement(Element book) {

		if (book == null || !(book instanceof Book)
				|| this.getCollection().contains(book))
			return false;

		if (this.getShelf() != null && getShelf().getFreeSpace() == 0)
			return false;

		if (this.getShelf() != null)
			this.getShelf().setFreeSpace(this.getShelf().getFreeSpace() - 1);

		this.getCollection().add(book);

		return true;
	}
}
