package OurSuggestion;


public abstract class Element implements Comparable< Element >
{
	
	/**
	 * @field title - String containing the title of each {@code Element}.
	 * @field type - String representation of the {@code type} of the objects
	 *        created by its subclasses.
	 */
	private final String title;
	private final String type;
	
	/**
	 * @field requested - boolean variable that will allow us to determine if an
	 *        {@code Element} contained in a {@code Shelf} is requested or not.
	 *        The default value will be false.
	 */
	private boolean isAvailable;
	
	
	private boolean isInAShelf;
	
	/**
	 * {@code Constructor} that will be used by its subclasses to add
	 * characteristics to their objects. If the title received as parameter is
	 * null it will throw an {@link IllegalArgumentException}.
	 * 
	 * @param title
	 *            - title of the {@code Element}.
	 * @param type
	 *            - a String representation of the {@code type} of the
	 *            {@code Element}.
	 */
	public Element( String title, String type ) {
		
		if( title == null )
			throw new IllegalArgumentException( "The title cannot be null!" );
		
		this.title = title;
		this.type = type;
		this.isAvailable = true;
		this.isInAShelf = false;
	}
	
	
	// Veio da versão do Daniel...
	
	/**
	 * Override of the method {@link compareTo}, from the {@link comparable}
	 * interface and used by the {@link TreeSet} class to automaticaly organize
	 * the its elements, that will allow the collections to be alphabetically
	 * organized by their title in a {@link Shelf}.
	 * 
	 * If their title is the same they will be organized alphabetically by their
	 * type. This is achieved by using the {@code compareTo} method of the
	 * String class to compare the String representation of the type of elements
	 * the collections can store.
	 * 
	 * If both their title and type are the same, they will be ordered by
	 * ascending order of size or, if the size is also the same, by using the
	 * same {@code compareTo} evaluation of their first different element.(Even
	 * tho this cases are considered here, the created app doesn't allow the
	 * creation of two collections with the same name and type!!!).
	 * 
	 * If the Element given as parameter is null will throw an
	 * {@code IllegalArgumentException}.
	 * 
	 * @param element
	 *            - an {@code Collection} must be given as parameter.
	 * 
	 * @return - returns an int number that will represent the position the
	 *         collection will have relative to another in a Collection. If it's
	 *         less than 0 it will come first and if it's bigger than zero it
	 *         will come after.
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
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		
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
	
	/**
	 * @return type - returns a String representation of the {@code type} of an
	 *         {@code Element}.
	 */
	public String getType() {
		return type;
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
