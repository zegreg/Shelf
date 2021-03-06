package fhj.shelf.utils;


/**
 * Class whose instances represent collections of CDs.
 * 
 * @author (original) Daniel Gomes, Filipe Maia, Pedro Antunes
 * @author (revisionSOLID) Eva Gomes, Hugo Leal, Lucas Andrade
 */
public class CDCollection extends ComposedElement< CD >
{
	
//	private long eid;
	/**
	 * Creates an instance of {@link CDCollection} with title
	 * {@code collectionTitle}.
	 * 
	 * @param collectionTitle
	 *            The title of {@code this}.
	 * @throws IllegalArgumentException
	 *             If {@code title} is {@code null}.
	 */
	public CDCollection(long cdCollectionId, String collectionTitle) {
		super(cdCollectionId, collectionTitle);
	}



}
