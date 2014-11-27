package OurSuggestion;


import java.util.Iterator;
import java.util.TreeSet;


/**
 * {@code Abstract} class that implements the {@link Comparable} interface.
 * 
 * Since this is an abstract class we cannot create objects of this type but we
 * can still use it to add properties and fields to its subclasses.
 */
public abstract class ComposedElement<T extends RequestableElement> extends Element 
{
	
//	/**
//	 * @field collectionTitle - title of the {@code Collection}.
//	 * @field elementsType - String representation of the type of elements
//	 *        {@code Collection} the can contain.
//	 */
//	private final String collectionTitle;
//	//private final String elementsType;
	
	/**
	 * @field elements - {@link TreeSet} that will store the elements added to
	 *        the {@code Collection} and make sure they are always properly
	 *        ordered (it will use the overriden {@code comapeTo} methods to do
	 *        that).
	 */
	private TreeSet<T> elements;
	
	/**
	 * @field available - boolean variable that will allow us to determine if a
	 *        {@code Collection} contained in a {@code Shelf} is available or
	 *        not. A collection is available only if none of its elements is
	 *        requested. The default value will be false.
	 */
	private boolean available;
	
	/**
	 * {@code Constructor} that will be used by its subclasses to add
	 * characteristics to their objects. If the {@code collectionTitle} received
	 * as parameter is null it will throw an {@link IllegalArgumentException}.
	 * 
	 * @param collectionTitle
	 *            - title of the {@code Collection}.
	 * @param type
	 *            - String representation of the type of elements
	 *            {@code Collection} the can contain.
	 */
	public ComposedElement( String collectionTitle) {
		
		super(collectionTitle, "collection");
		
		if( collectionTitle == null )
			throw new IllegalArgumentException( "The title cannot be null!" );
		
		elements = new TreeSet< T >();
		available = true;
	}
	
	/**
	 * Method that will update the availability of a {@code Collection}
	 * every time one of its elements is requested or returned.
	 */
	void updateAvailability() {
		available = !available;
	}
	
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
	 * @param collection
	 *            - an {@code Collection} must be given as parameter.
	 * 
	 * @return - returns an int number that will represent the position the
	 *         collection will have relative to another in a Collection. If it's
	 *         less than 0 it will come first and if it's bigger than zero it
	 *         will come after.
	 */
	public int compareTo( ComposedElement<T> collection ) {
		
		if( collection == null )
			throw new IllegalArgumentException(
					"The collection cannot be null!" );
		
		int compareTitle = getTitle().compareTo( collection
				.getTitle() );
		
		if( compareTitle != 0 )
			return compareTitle;
		
		//TODO
//		int compareType = this.elementsType.compareTo( collection.elementsType );
		
//		if( compareType != 0 )
//			return compareType;
		
		int compareSize = this.getCollection().size() - collection.getCollection().size();
		
		if( compareSize != 0 )
			return compareSize;
		
		Iterator< T > iter = elements.iterator();
		Iterator< T > iter2 = collection.getCollection().iterator();
		
		while( iter.hasNext() )
		{
			
			Element thisElement = iter.next();
			Element collectionElement = iter2.next();
			
			// Collections are always ordered!!!
			int compare = thisElement.compareTo( collectionElement );
			
			if( compare != 0 )
				return compare;
		}
		
		return 0;
	}
	
	/**
	 * Override of the method {@link hashCode} from the class {@link Object}.
	 */
	@Override
	public int hashCode() {
		
		final int prime = 31;
		int result = 1;
		
		result = prime * result + (available ? 1231 : 1237);
		
		result = prime * result
				+ ((getTitle() == null) ? 0 : getTitle().hashCode());
		
		result = prime * result
				+ ((elements == null) ? 0 : elements.hashCode());
		
//		result = prime * result
//				+ ((elementsType == null) ? 0 : elementsType.hashCode());
		
		result = prime * result + ((super.getShelf() == null) ? 0 : getShelf().hashCode());
		
		return result;
	}
	
	/**
	 * Override of the method {@link equals} from the class {@code Object} to be
	 * consistent with the {@code comparaTo} method.
	 */
	@Override
	public boolean equals( Object collection ) {
		
		if( this == collection )
			return true;
		
		if( collection == null )
			return false;
		
		if( !getClass().equals( collection.getClass() ) )
			return false;
		
		if( this.compareTo( (ComposedElement<T>)collection ) != 0 ) //TODO este cast dá me comichoes por todos os lados
			return false;
		
		return true;
	}
	
	/**
	 * Method that will allow an {@code Element} to be removed from a
	 * {@code Collection}. If the given parameter is null, if it's not contained
	 * by the {@code Collection} or if the collection is not available no
	 * element will be removed and will return false. In case the
	 * {@code Collection} is in a {@code Shelf} it will also free one space in
	 * it.
	 * 
	 * If the {@code Collection} already contains elements from a specific type
	 * and an element of another type tries to be removed it will throw a
	 * {@link ClassCastException}.
	 * 
	 * If the removed {@code Element} is the last {@code Element} of the
	 * {@code Collection} the {@code Collection} will be removed from the
	 * {@code Shelf}.
	 * 
	 * @param element
	 *            - an {@code Element} must be given as parameter.
	 * 
	 * @return - returns true if the {@code Element} is successfully removed and
	 *         false otherwhise.
	 */
	public boolean removeElement( T element ) {
		
		
		return elements.remove(element);
		
//		if( element == null || !this.getCollection().contains( element ) )
//			return false;
//		
//		if( this.getShelf() != null && !this.isAvailable() )
//			return false;
//		
//		this.getCollection().remove( element );
//		
//		if( this.getShelf() != null )
//			this.getShelf().setFreeSpace( this.getShelf().getFreeSpace() + 1 );
//		
//		if( this.getShelf() != null && this.getCollection().size() == 0 )
//			this.getShelf().removeCollection( this );
//		
//		return true;
	}
	
	/**
	 * adds an element to the collection
	 * @param element - the element to add
	 * @return true if the element was successfully added
	 * @return false if the element was not added
	 */
	public boolean addElement( T element )
	{
		return elements.add(element);
	}
	
	/**
	 * @return elements - returns the elements contained by a {@code Collection}
	 *         
	 */
	public TreeSet< T > getCollection() {
		return elements;
	}
	
//	/**
//	 * @return elementsType - returns a String representation of the type of
//	 *         elements {@code Collection} the can contain.
//	 */
//	public String getElementsType() {
//		return elementsType;
//	}
	
	/**
	 * @return available - returns the availability status of a
	 *         {@code Collection}.
	 */
	public boolean isAvailable() {
		return available;
	}
	
	/**
	 * @return a string with information about all the elements of the collection
	 */
	public String toString()
	{
		Iterator<T> iterator = elements.iterator();
		StringBuilder builder = new StringBuilder();
		
		while(iterator.hasNext())
		{
			builder.append(iterator.next().toString()).append("\n");
		}
		
		return builder.toString();
	}
}