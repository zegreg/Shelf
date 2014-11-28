package OurSuggestion;


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
	public Element[] findElementsWithTheSameTypeAndTitleAs( Element element );
	
	/**
	 * Checks if this instance contains instances of {@link Element} that have
	 * the same type and title as {@code element} and returns their information
	 * in an array of {@link String}s.
	 * <p>
	 * Makes use of the method {@code toString()} implemented in the class that
	 * defines the type of {@code element}.
	 * </p>
	 * 
	 * @param element
	 *            The element whose type and title will be searched in this
	 *            instance.
	 * @return An array of {@link String}s in which each String corresponds to
	 *         the information of an instance of {@link Element} contained in this
	 *         {@link Searchable} instance that has the same type and title as
	 *         {@code element}; returns {@code null} if this {@link Searchable}
	 *         instance does not contain instances of {@link Element} with the
	 *         same type and title as {@code element}.
	 */
	public String[] getInfoAboutElementsWithTheSameTypeAndTitleAs(
			Element element );
	
}
