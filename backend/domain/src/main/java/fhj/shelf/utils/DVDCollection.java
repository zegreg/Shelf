package fhj.shelf.utils;


/**
 * Class whose instances represent collections of DVDs.
 * 
 * @author (original) Daniel Gomes, Filipe Maia, Pedro Antunes
 * @author (revisionSOLID) Eva Gomes, Hugo Leal, Lucas Andrade
 */
public class DVDCollection extends ComposedElement< DVD >
{
	
//	private long eid;
	/**
	 * Creates an instance of {@link DVDCollection} with title
	 * {@code collectionTitle}.
	 * 
	 * @param collectionTitle
	 *            The title of {@code this}.
	 * @throws IllegalArgumentException
	 *             If {@code title} is {@code null}.
	 */
	public DVDCollection(String collectionTitle) {
		super(collectionTitle);
	}

	

}
