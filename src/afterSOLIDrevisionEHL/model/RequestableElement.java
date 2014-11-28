package afterSOLIDrevisionEHL.model;


/**
 * Class whose instances represent simple elements that can be be put in a
 * shelf. Simple elements are not sets of other elements. Some examples of
 * simple elements are books, cds and dvds.
 * 
 * @author (original) Daniel Gomes, Filipe Maia, Pedro Antunes
 * @author (revisãoSOLID) Eva Gomes, Hugo Leal, Lucas Andrade
 */
public abstract class RequestableElement extends Element
{
	
	// CONSTRUTOR
	
	/**
	 * Creates an instance of {@link RequestableElement} with title
	 * {@code title}.
	 * 
	 * @param title
	 *            The title of this element.
	 * @throws IllegalArgumentException
	 *             If {@code title} or {@code author} are {@code null}.
	 */
	public RequestableElement( String title ) {
		super( title );
	}
	
	
	
	// OTHER METHODS
	
	/**
	 * Simple elements represented by instances of {@link RequestableElement}
	 * always have size 1, since they occupy only one space in a shelf.
	 * 
	 * @return 1.
	 */
	public int getSize() {
		return 1;
	}
	
	
	public Element isOrContainsElementsWithTheSameTypeAndTitleAs(
			Element element ) {
		
		if( isInstanceOfTheSameTypeAndHasTheSameTitleAs( element ) )
			return this;
		
		return null;
	}
	
	public Element isOrContains( Element element ) {
		
		if( equals( element ) )
			return this;
		
		return null;
	}
	
	
}
