package afterSOLIDrevisionEHL.model;


/**
 * Interface that imposes operations of searching elements with the same type
 * and title, getting information of a contained element with a certain type and
 * a certain title and getting information about all the elements contained.
 * 
 * @author (original) Daniel Gomes, Filipe Maia, Pedro Antunes
 * @author (revisionSOLID) Eva Gomes, Hugo Leal, Lucas Andrade
 */
public interface Searchable
{
	
	/**
	 * Checks if this instance contains instances of {@link Element} that have
	 * the same type and title as {@code element} and returns them in an array
	 * of {@link Element}s.
	 * 
	 * @param element
	 *            The element whose type and title will be searched in this
	 *            instance.
	 * @return An array of {@link Element}s that are contained in this instance
	 *         and have the same type and title as {@code element}; returns
	 *         {@code null} if this instance does not contain instances of
	 *         {@link Element} with the same type and title as {@code element}.
	 */
	public abstract Element[] findElementsWithTheSameTypeAndTitleAs( Element element );
	
	

	
	/**
	 * Checks if this instance contains instances of {@link Element} that have
	 * the same type and title as {@code element} and returns their information
	 * in an array of {@link String}s.
	 * 
	 * @param element
	 *            The element whose type and title will be searched in this
	 *            instance.
	 * @return An array of {@link String}s in which each String corresponds to
	 *         the information of an instance of {@link Element} contained in
	 *         this {@link Searchable} instance that has the same type and title
	 *         as {@code element}; returns {@code null} if this
	 *         {@link Searchable} instance does not contain instances of
	 *         {@link Element} with the same type and title as {@code element}.
	 */
	public abstract String[] getInfoAboutElementsWithTheSameTypeAndTitleAs(
			Element element );
	
	/**
	 * Gets information about all the elements stored.
	 * 
	 * @return An array of {@link String}s in which each String corresponds to
	 *         the information of an element contained in this
	 *         {@link Searchable} instance.
	 */
	public abstract String[] getInfoAboutAllElementsContained();
	
	
	
	
	
}
