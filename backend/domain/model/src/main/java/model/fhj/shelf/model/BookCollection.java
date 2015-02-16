package model.fhj.shelf.model;

/**
 * Class whose instances represent collections of books.
 * 
 * @author (original) Daniel Gomes, Filipe Maia, Pedro Antunes
 * @author (revisionSOLID) Eva Gomes, Hugo Leal, Lucas Andrade
 */
public class BookCollection extends ComposedElement<Book> {

	/**
	 * Creates an instance of {@link BookCollection} with title
	 * {@code collectionTitle}.
	 * 
	 * @param collectionTitle
	 *            The title of {@code this}.
	 * @throws IllegalArgumentException
	 *             If {@code title} is {@code null}.
	 */
	public BookCollection(long bookCollectionId, String collectionTitle) {
		super(bookCollectionId, collectionTitle);
	}

}
