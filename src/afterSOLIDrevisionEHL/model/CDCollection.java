package afterSOLIDrevisionEHL.model;


/**
 * Class whose instances represent collections of CDs.
 * 
 * @author (original) Daniel Gomes, Filipe Maia, Pedro Antunes
 * @author (revisionSOLID) Eva Gomes, Hugo Leal, Lucas Andrade
 */
public class CDCollection extends ComposedElement< CD >
{
	
	/**
	 * Creates an instance of {@link CDCollection} with title
	 * {@code collectionTitle}.
	 * 
	 * @param collectionTitle
	 *            The title of {@code this}.
	 * @throws IllegalArgumentException
	 *             If {@code title} is {@code null}.
	 */
	public CDCollection( String collectionTitle) {
		super( collectionTitle);
	}



}
