package afterSOLIDrevisionEHL.model;


/**
 * Interface that imposes operations of requesting and returning elements.
 * 
 * @author (original) Daniel Gomes, Filipe Maia, Pedro Antunes
 * @author (revisionSOLID) Eva Gomes, Hugo Leal, Lucas Andrade
 */
public interface RequestManager
{
	
	/**
	 * Requests an element from {@code this}.
	 * 
	 * @param element
	 *            The element to be requested.
	 * @return {@code element} if the requiring operation was successful;<br>
	 *         {@code null} if it couldn't be requested.
	 */
	public AbstractElement requestElement( AbstractElement element );
	
	/**
	 * Returns an element to {@code this}, if it was previously requested.
	 * 
	 * @param element
	 *            The element to be returned.
	 * @return {@code true} if the returning operation was successful;<br>
	 *         {@code false} if it couldn't be returned.
	 */
	public boolean returnElement( AbstractElement element );
}
