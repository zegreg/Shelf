package afterSOLIDrevisionEHL.model;


import java.util.Collection;
import java.util.TreeSet;


/**
 * Class whose instances represent collections that can be put in a shelf.
 * Collections have a title and contain other elements (either other collections
 * either simple elements), they occupy as many units of space in a shelf as the
 * quantity of elements they contain. Some examples of collections are
 * collections of books, collections of cds and collections of dvds.
 * 
 * @author (original) Daniel Gomes, Filipe Maia, Pedro Antunes
 * @author (revisionSOLID) Eva Gomes, Hugo Leal, Lucas Andrade
 */

public abstract class ComposedElement< T extends SimpleElement > extends
		AbstractElement
{
	
	// INSTANCE FIELDS
	
	/**
	 * The container of the elements that constitute this collection.
	 */
	private Collection< AbstractElement > elements;
	
	
	
	// CONSTRUCTOR
	
	/**
	 * Creates an instance of {@link ComposedElement} with title
	 * {@code collectionTitle}.
	 * 
	 * @param collectionTitle
	 *            The title of {@code this}.
	 * @throws IllegalArgumentException
	 *             If {@code title} is {@code null}.
	 */
	public ComposedElement( String collectionTitle, long  id) {
		
		super( collectionTitle, id );
		elements = new TreeSet< AbstractElement >();
	}
	
	
	
	// ADD, REMOVE
	
	/**
	 * Adds an instance of {@link T} to this collection. If added, we will say
	 * that {@code this} contains {@code element}.
	 * <p>
	 * The instance {@code element} will not be added to {@code this} if:
	 * <ul>
	 * <li>it is {@code null};</li>
	 * <li>it was previously added to a collection;</li>
	 * <li>it was previously added to a shelf;</li>
	 * <li>{@code this} is in a shelf or</li>
	 * <li>{@code this} already contains an instance that {@code equals}
	 * {@code element}.</li>
	 * </ul>
	 * </p>
	 * 
	 * @param element
	 *            The element to be added to {@code this}.
	 * @return {@code true} if the element was successfully added; <br>
	 *         {@code false} if the element was not added.
	 */
	public boolean addElement( T element ) {
		
		// This.isInAShef ????
		if( element == null || element.isInACollection()
				|| element.isInAShelf() || this.isInAShelf() )
			return false;
		
		if( elements.add( element ) )
		{
			element.isInACollection( true );
			return true;
		}
		return false;
	}
	
	/**
	 * Adds a collection of instances of {@link T} to this collection. If added,
	 * we will say that {@code this} contains {@code collection}.
	 * <p>
	 * The instance {@code collection} will not be added to {@code this} if:
	 * <ul>
	 * <li>it is {@code null};</li>
	 * <li>it was previously added to a collection;</li>
	 * <li>it was previously added to a shelf;</li>
	 * <li>{@code this} is in a shelf or</li>
	 * <li>{@code this} already contains an instance that {@code equals}
	 * {@code collection}.</li>
	 * </ul>
	 * </p>
	 * 
	 * @param collection
	 *            The collection to be added to {@code this}.
	 * @return {@code true} if the collection was successfully added; <br>
	 *         {@code false} if the collection was not added.
	 */
	public boolean addCollection( ComposedElement< T > collection ) {
		
		if( collection == null || collection.isInACollection()
				|| collection.isInAShelf() || this.isInAShelf() )
			return false;
		
		if( elements.add( collection ) )
		{
			collection.isInACollection( true );
			return true;
		}
		return false;
	}
	
	/**
	 * Removes the instance {@code element} from this collection.
	 * <p>
	 * The instance {@code element} will not be removed from {@code this} if:
	 * <ul>
	 * <li>it is {@code null};</li>
	 * <li>it was not contained in {@code this} or</li>
	 * <li>{@code this} is in a shelf.</li>
	 * </ul>
	 * </p>
	 * 
	 * @param element
	 *            The element to be removed from {@code this}.
	 * @return {@code true} if the element was successfully removed; <br>
	 *         {@code false} if the element was not removed.
	 */
	public boolean removeElement( T element ) {
		
		if( element == null || this.isInAShelf() )
			return false;
		
		if( elements.remove( element ) )
		{
			element.isInACollection( false );
			return true;
		}
		return false;
	}
	
	/**
	 * Removes the instance {@code collection} from this collection.
	 * <p>
	 * The instance {@code collection} will not be removed from {@code this} if:
	 * <ul>
	 * <li>it is {@code null};</li>
	 * <li>it was not contained in {@code this} or</li>
	 * <li>{@code this} is in a shelf.</li>
	 * </ul>
	 * </p>
	 * 
	 * @param collection
	 *            The collection to be removed from {@code this}.
	 * @return {@code true} if the collection was successfully removed; <br>
	 *         {@code false} if the collection was not removed.
	 */
	public boolean removeCollection( ComposedElement< T > collection ) {
		
		if( collection == null || this.isInAShelf() )
			return false;
		
		if( elements.remove( collection ) )
		{
			collection.isInACollection( false );
			return true;
		}
		return false;
	}
	
	
	
	// /**
	// * @return elements - returns the elements contained by a {@code
	// Collection}
	// *
	// */
	// public Collection< Element > getCollection() {
	// return elements;
	// }
	
	
	
	// OVERRIDES OF Object AND Element METHODS
	
	/**
	 * Returns a {@link String} representation of this collection, consisting in
	 * a list of the {@link String} representations of the elements contained in
	 * this collection.
	 * 
	 * @return A {@link String} representation of this instance.
	 */
	public String toString() {
		
		StringBuilder builder = new StringBuilder( "Collection: " ).append(
				getTitle() ).append( "\n{\n" );
		
		for( AbstractElement e : elements )
			builder.append( e.toString() ).append( "\n" );
		
		return builder.append( "\n}" ).toString();
	}
	
	/**
	 * Sets this element's status concerning on whether it is or is not
	 * available. If {@code b} is {@code true}, this element status changes to
	 * available; if {@code b} is {@code false}, this element is now
	 * unavailable.
	 * <p>
	 * Also, as {@code this} is a collection, the availability status of all the
	 * elements contained in {@code this} is changed to be the same as
	 * {@code this}.
	 * </p>
	 *
	 * @param b
	 *            The new availability status of {@code this} and its
	 *            constituents.
	 */
	@Override
	public void setAvailability( boolean b ) {
		
		super.setAvailability( b );
		for( AbstractElement e : elements )
			if( e != null )
				e.setAvailability( b );
	}
	
	/**
	 * Sets this element's status concerning on whether it is or is not
	 * requested. If {@code b} is {@code true}, this element status changes to
	 * requested; if {@code b} is {@code false}, this element was now returned.
	 * <p>
	 * Also, as {@code this} is a collection, the status of all the elements
	 * contained in {@code this} is changed to be the same as {@code this}.
	 * </p>
	 * 
	 * @param b
	 *            The new status of {@code this}.
	 */
	public void isRequested( boolean b ) {

		super.isRequested( b );
		for( AbstractElement e : elements )
			if( e != null )
				e.isRequested( b );
		
	}
	
	/**
	 * Returns the number of elements that this collection has.
	 * <p>
	 * If {@code this} contains simple elements and collections, its number of
	 * elements is the sum of the number of simple elements contained with the
	 * number of elements contained in all the collections contained in
	 * {@code this}.<br>
	 * E.g. if {@code this} contains 2 simple elements and a collection that has
	 * 3 simple elements, then {@code this} has size 5.
	 * </p>
	 * 
	 * @return The number of elements that this collection has.
	 */
	public int getSize() {
		
		int numberOfContents = 0;
		for( AbstractElement e : elements )
			if( e != null )
				numberOfContents += e.getSize();
		return numberOfContents;
		
	}
	
	/**
	 * Checks whether {@code this} is or contains an element with the same type
	 * and title as {@code element}.
	 * <p>
	 * First, it checks if {@code this} has the same runtime type and the same
	 * title as {@code element}; returns {@code this} if so. If not, it is
	 * checked whether any of the elements contained in {@code this} is or
	 * contains an element with the same type and title as {@code element}. This
	 * happens recursively (since every collection may contain other
	 * collections) and if an element with the same runtime type and title as
	 * {@code element} is found, it is returned.
	 * </p>
	 * 
	 * @param element
	 *            The instance of {@link AbstractElement} with which to compare the type
	 *            and the title.
	 * @return {@code this} if {@code this} has the same runtime type and the
	 *         same title as {@code element};<br>
	 *         the first element that is contained in {@code this} and has the
	 *         same runtime type and the same title as {@code element}, if
	 *         founded;<br>
	 *         {@code null} if the collection is not and does not contain an
	 *         element with the same runtime type and the same title as
	 *         {@code element}.
	 */
	public AbstractElement isOrContainsElementsWithTheSameTypeAndTitleAs(
			AbstractElement element ) {
		
		if( this.isInstanceWithTheSameTypeAndTitleAs( element ) )
			return this;
		
		for( AbstractElement e : elements )
		{
			AbstractElement elem = e
					.isOrContainsElementsWithTheSameTypeAndTitleAs( element );
			
			if( elem != null )
				return elem;
		}
		return null;
	}
	
	/**
	 * Checks whether {@code this} is or contains an element that equals
	 * {@code element}.
	 * <p>
	 * First, it checks if {@code this.equals(element)}; returns {@code this} if
	 * so. If not, it is checked whether any of the elements contained in
	 * {@code this} is or contains an element than equals {@code element}. This
	 * happens recursively (since every collection may contain other
	 * collections) and if an element that equals {@code element} is found, it
	 * is returned.
	 * </p>
	 * 
	 * @param element
	 *            The instance of {@link AbstractElement} with which to compare.
	 * @return {@code this} if {@code this.equals(element)};<br>
	 *         the first element that is contained in {@code this} and equals
	 *         {@code element}, if founded;<br>
	 *         {@code null} if the collection is not and does not contain an
	 *         element that equals {@code element}.
	 */
	public AbstractElement isOrContains( AbstractElement element ) {
		
		if( this.equals( element ) )
			return this;
		
		
		for( AbstractElement e : elements )
		{
			AbstractElement elem = e.isOrContains( element );
			
			if( elem != null )
				return elem;
		}
		return null;
	}
	
	
}