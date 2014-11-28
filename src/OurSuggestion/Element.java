package OurSuggestion;


public abstract class Element implements Comparable< Element >
{
	
	/**
	 * @field title - String containing the title of each {@code Element}.
	 * @field type - String representation of the {@code type} of the objects
	 *        created by its subclasses.
	 */
	private final String title;
	
	/**
	 * @field requested - boolean variable that will allow us to determine if an
	 *        {@code Element} contained in a {@code Shelf} is requested or not.
	 *        The default value will be false.
	 */
	private boolean isAvailable;
	
	
	private boolean isInAShelf;
	
	
	
	// CONSTRUTOR
	
	/**
	 * Creates an instance of {@link Element} with title {@code title}.
	 * 
	 * @param title
	 *            The title of this book.
	 * @param author
	 *            The name of the author of the book.
	 * @throws IllegalArgumentException
	 *             If {@code title} are {@code null}.
	 */
	public Element( String title ) {
		
		if( title == null )
			throw new IllegalArgumentException( "The title cannot be null!" );
		
		this.title = title;
		this.isAvailable = true;
		this.isInAShelf = false;
	}
	
	
	// Veio da versão do Daniel...
	
	/**
	 * Overrides the method {@link Comparable#compareTo(Object) compareTo} of
	 * the interface {@link Comparable}. Orders instances of {@link Element} by
	 * lexicological order of the field {@code title} (obtained using the method
	 * {@link String#compareTo(String) compareTo of class String}); if two
	 * instances have the same {@code title}, they will be organized by
	 * lexicological order of the {@link String} representation of their classes
	 * (obtained using the methods {@link Object#getClass() getClass da classe
	 * Object}, {@link Class#toString() toString of class Class} and
	 * {@link String#compareTo(String) compareTo of class String}).
	 * 
	 * @param element
	 *            The instance of {@link Element} with which to compare to.
	 * @return A value less than 0 if {@code this} is less than {@code element};
	 *         0 if {@code this} and {@code element} have the same {@code title}
	 *         and the same runtime type or and a value greater than 0 if
	 *         {@code this} is greater than {@code element}.
	 * @throws IllegalArgumentException
	 *             If {@code element} is {@code null}.
	 */
	@Override
	public int compareTo( Element element ) {
		
		if( element == null )
			throw new IllegalArgumentException( "This element cannot be null!" );
		
		int compareTitle = this.title.compareTo( element.title );
		
		if( compareTitle != 0 )
			return compareTitle;
		
		return getClass().toString().compareTo( element.getClass().toString() );
	}
	
	@Override
	public int hashCode() {
		
		final int prime = 31;
		
		int result = 1;
		
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		
		return result;
	}
	
	@Override
	public boolean equals( Object other ) {
		
		return isInstanceOfTheSameTypeAndHasTheSameTitleAs( other );
	}
	
	// ...até aqui!
	
	public boolean isInstanceOfTheSameTypeAndHasTheSameTitleAs( Object other ) {
		
		if( this == other )
			return true;
		
		if( other == null )
			return false;
		
		if( !getClass().equals( other.getClass() ) )
			return false;
		
		if( !this.title.equals( ((Element)other).title ) )
			return false;
		
		return true;
	}
	
	
	
	/**
	 * {@code Abstract} method that will be implemented by the subclasses of
	 * this class. It will have the purpose of printing the specific information
	 * regarding each of the different objects contained in a {@code Shelf}.
	 */
	@Override
	public abstract String toString();
	
	/**
	 * @return requested - returns the current value of the requested variable
	 *         of an {@code Element}.
	 */
	public boolean isAvailable() {
		return isAvailable;
	}
	
	public void changeAvailability() {
		isAvailable = !isAvailable;
	}
	
	/**
	 * @return title - returns the title of an {@code Element}.
	 */
	public String getTitle() {
		return title;
	}
	
	
	public abstract int getSize();
	
	
	public boolean isInAShelf() {
		return isInAShelf;
	}
	
	
	public void isInAShelf( boolean b ) {
		isInAShelf = b;
	}
	
	public abstract Element isOrContainsElementsWithTheSameTypeAndTitleAs(
			Element element );
	
	public abstract Element isOrContains( Element element );
	
}
