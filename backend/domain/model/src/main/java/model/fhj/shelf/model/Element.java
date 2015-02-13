package model.fhj.shelf.model;

import fhj.shelf.repositories.DatabaseElements;



/**
 * Class whose instances represent elements that can be put in a shelf. Elements
 * have a title, they may or may not be in a shelf, they occupy a certain number
 * of units of space and they have an availability status.
 * 
 * @author (original) Daniel Gomes, Filipe Maia, Pedro Antunes
 * @author (revisionSOLID) Eva Gomes, Hugo Leal, Lucas Andrade
 */
public abstract class Element implements Requestable, Comparable< Element >, DatabaseElements
{
	
	// INSTANCE FIELDS
		
	private long elementId;
	/**
	 * The element's title.
	 */
	private String title;
	
	/**
	 * The element's status regarding if it was added to a shelf or not.
	 */
	private boolean isInAShelf;
	
	/**
	 * The element's status regarding if it was added to a collection or not.
	 */
	private boolean isInACollection;
	
	/**
	 * The element's availability status.
	 */
	private boolean isAvailable;
	
	/**
	 * The element's status regarding if it was requested or not from a shelf.
	 */
	private boolean isRequested;
	
	
	// CONSTRUCTOR
	
	
	/**
	 * Creates an instance of {@link Element} with title {@code title}.
	 * 
	 * @param title
	 *            The title of {@code this}.
	 * @throws IllegalArgumentException
	 *             If {@code title} is {@code null}.
	 */
	public Element(long elementId, String title) {
		
		if( title == null || title.trim().isEmpty())
			throw new IllegalArgumentException( "The title cannot be null!" );
		
//		// Beware of shared state... :P
//				id = ++lastId;
		
		this.elementId = elementId;
		this.title = title;
		this.isInAShelf = false;
		this.isInACollection = false;
		this.isAvailable = false;
		this.isRequested = false;
	
	}
	
	
	
	// OVERRIDES OF Comparable<Element> AND Object METHODS
	
	
	/**
	 * Sorts instances of {@link Element} by lexicological order of the field
	 * {@code title} (using the method {@link String#compareTo(String) compareTo
	 * of class String}); if two instances have the same {@code title} , they
	 * will be sorted by lexicological order of the {@link String}
	 * representation of their classes (obtained using the methods
	 * {@link Object#getClass() getClass of class Object},
	 * {@link Class#toString() toString of class Class} and
	 * {@link String#compareTo(String) compareTo of class String}).
	 * 
	 * @param element
	 *            The instance of {@link Element} with which to compare to.
	 * @return A value less than 0 if {@code this} is less than {@code element};
	 *         0 if {@code this} and {@code element} have the same {@code title}
	 *         and the same runtime type or a value greater than 0 if
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
	
	/**
	 * Compares the type and the title of {@code this} and {@code other} (using
	 * {@link Element#isInstanceWithTheSameTypeAndTitleAs(Object)
	 * isInstanceWithTheSameTypeAndTitleAs of class Element}).
	 * 
	 * @param other
	 *            The instance whose type and title are to be compared with.
	 * @return {@code true} if {@code this} and {@code other} are two instances
	 *         with the same type and {@code title};<br>
	 *         {@code false} otherwise.
	 */
	@Override
	public boolean equals( Object other ) {
		return isInstanceWithTheSameTypeAndTitleAs( other );
	}
	
	/**
	 * Returns a hash code value for {@code this}.
	 *
	 * @return A hash code value for {@code this}.
	 */
	
	@Override
	public int hashCode() {
		
		final int prime = 31;
		int result = 1;
		
		result = prime * result
				+ ((this.getTitle() == null) ? 0 : this.getTitle().hashCode());
		
		result = prime
				* result
				+ ((this.getClass().toString() == null) ? 0 : this.getClass()
						.toString().hashCode());
		
		
		return result;
	}
	
	/**
	 * Returns a {@link String} representation of {@code this}.
	 * 
	 * @return A {@link String} representation of {@code this}.
	 */
	@Override
	public abstract String toString();
	
	
	
	// GETTERs AND SETTERs
	

	
	/**
	 * Returns this element's title.
	 * 
	 * @return This element's title.
	 */
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String newName) {
		this.title = newName;
	}
	
	/**
	 * Returns {@code true} if this element is already in a shelf or
	 * {@code false} otherwise.
	 * 
	 * @return {@code true} if this element is in a shelf; <br>
	 *         {@code false} otherwise.
	 */
	public boolean isInAShelf() {
		return isInAShelf;
	}
	
	/**
	 * Sets this element's status concerning on whether it is or is not in a
	 * shelf. If {@code b} is {@code true}, this element status changes to
	 * "is in a shelf"; if {@code b} is {@code false}, this element is now
	 * "not in a shelf".
	 * 
	 * @param b
	 *            The new status of {@code this}.
	 */
	public void isInAShelf( boolean b ) {
		isInAShelf = b;
	}
	
	/**
	 * Returns {@code true} if this element is already in a collection or
	 * {@code false} otherwise.
	 * 
	 * @return {@code true} if this element is in a collection; <br>
	 *         {@code false} otherwise.
	 */
	public boolean isInACollection() {
		return isInACollection;
	}
	
	/**
	 * Sets this element's status concerning on whether it is or is not in a
	 * collection. If {@code b} is {@code true}, this element status changes to
	 * "is in a collection"; if {@code b} is {@code false}, this element is now
	 * "not in a collection".
	 * 
	 * @param b
	 *            The new status of {@code this}.
	 */
	public void isInACollection( boolean b ) {
		isInACollection = b;
	}
	
	/**
	 * Returns this element's availability.
	 * 
	 * @return {@code true} if {@code this} is available;<br>
	 *         {@code false} if it's not.
	 */
	public boolean isAvailable() {
		return isAvailable;
	}
	
	/**
	 * Sets this element's status concerning on whether it is or is not
	 * available. If {@code b} is {@code true}, this element status changes to
	 * available; if {@code b} is {@code false}, this element is now
	 * unavailable.
	 *
	 * @param b
	 *            The new availability status of {@code this}.
	 */
	public void setAvailability( boolean b ) {
		isAvailable = b;
	}
	
	/**
	 * Returns this element's status regarding if it was requested or not from a
	 * shelf.
	 * 
	 * @return {@code true} if {@code this} is requested;<br>
	 *         {@code false} if it's not.
	 */
	public boolean isRequested() {
		return isRequested;
	}
	
	/**
	 * Sets this element's status concerning on whether it is or is not
	 * requested. If {@code b} is {@code true}, this element status changes to
	 * requested; if {@code b} is {@code false}, this element was now returned.
	 *
	 * @param b
	 *            The new availability status of {@code this}.
	 */
	public void isRequested( boolean b ) {
		isRequested = b;
	}
	
	
	
	// OTHER METHODS
	
	/**
	 * Compares the type and the title of {@code this} and {@code other}.
	 * <p>
	 * This method produces the same result as the method
	 * {@link Element#equals(Object) equals of class Element} yet its existence
	 * ensures the comparison of two instances of {@link Element} by looking
	 * only at their type and title while; if using the method
	 * {@link Element#equals(Object) equals of class Element} it might be
	 * overriden and the comparison criteria could be different.
	 * </p>
	 * 
	 * @param other
	 *            The instance whose type and title are to be compared with.
	 * @return {@code true} if {@code this} and {@code other} are two instances
	 *         with the same type and {@code title};<br>
	 *         {@code false} otherwise.
	 */
	public boolean isInstanceWithTheSameTypeAndTitleAs( Object other ) {
		
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
	
	
	
	// UNIMPLEMENTED METHODS
	
	/**
	 * Returns the number of units of space occupied by this element in a shelf.
	 * 
	 * @return The number of units of space occupied by this element in a shelf.
	 */
	public abstract int getSize();
	
	/**
	 * Checks whether {@code this} has the same runtime type and the same title
	 * as {@code element} or whether {@code this} contains an instance of
	 * {@link Element} with the same runtime type and the same title as
	 * {@code element}.
	 * 
	 * @param element
	 *            The instance of {@link Element} with which to compare the type
	 *            and the title.
	 * @return An instance with the same runtime type and the same title as
	 *         {@code element};<br>
	 *         {@code null} if {@code this} is not and does not contain such
	 *         instance.
	 */
	public abstract Element isOrContainsElementsWithTheSameTypeAndTitleAs(
			Element element );
	
	/**
	 * Checks whether {@code this} equals {@code element} or whether
	 * {@code this} "contains" an instance of {@link Element} that equals
	 * {@code element}.
	 * 
	 * @param element
	 *            The instance of {@link Element} with which to compare.
	 * @return An instance that equals {@code element};<br>
	 *         {@code null} if {@code this} is not and does not contain such
	 *         instance.
	 */
	public abstract Element isOrContains( Element element );
	
	public  long getId() {
		return elementId;
	}
	
}
