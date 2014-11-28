package OurSuggestion;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.TreeSet;
import OurSuggestion.Element;


public class Shelf implements Storage, RequestManager, Searchable
{
	
	private Collection< Element > shelf;
	private final int capacity;
	private int freeSpace;
	
	public Shelf( int capacity ) {
		if( capacity < 1 )
			throw new IllegalArgumentException(
					"The Shelf must have a capacity bigger than 0" );
		
		this.capacity = capacity;
		this.freeSpace = capacity;
		shelf = new TreeSet< Element >();
	}
	
	
	
	/**
	 * Returns the String representation of all the contents of this
	 * {@link Shelf}.
	 * 
	 * @return The String representation of all the contents of this
	 *         {@link Shelf}.
	 */
	public String toString() {
		
		StringBuilder builder = new StringBuilder( "SHELF CONTENTS\n\n\n" );
		
		Iterator< Element > iterator = shelf.iterator();
		while( iterator.hasNext() )
			builder.append( iterator.next().toString() ).append( "\n\n\n" );
		
		return builder.toString();
	}
	
	
	
	// OVERRIDES DA INTERFACE STORAGE
	
	@Override
	public boolean add( Element element ) {
		if( element == null || shelf.contains( element ) )
			return false;
		
		int elemSize = element.getSize();
		
		if( elemSize < 1 || elemSize > freeSpace )
			return false;
		
		shelf.add( element );
		freeSpace -= elemSize;
		element.isInAShelf( true );
		return true;
	}
	
	@Override
	public boolean remove( Element element ) {
		if( element == null || !shelf.contains( element ) )
			return false;
		
		if( !element.isAvailable() )
			return false;
		
		shelf.remove( element );
		freeSpace += element.getSize();
		element.isInAShelf( false );
		return true;
	}
	
	
	
	// OVERRIDES THE INTERFACE REQUESTMANAGER
	
	@Override
	public Element requestElement( Element element ) {
		
		if( element == null )
			return null;
		
		Element theElement = findFirstOfThisElement( element );
		if( theElement == null )
			return null;
		
		if( !theElement.isAvailable() )
			return null;
		
		theElement.changeAvailability();
		return theElement;
		
		
		
	}
	
	@Override
	public boolean returnElement( Element element ) {
		
		if( element == null )
			return false;
		
		Element theElement = findFirstOfThisElement( element );
		if( theElement == null )
			return false;
		
		if( theElement.isAvailable() )
			return false;
		
		theElement.changeAvailability();
		return true;
	}
	
	
	
	// OVERRIDES DA INTERFACE SEARCHABLE
	
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
	public Element[] findElementsWithTheSameTypeAndTitleAs( Element element ) {
		
		ArrayList< Element > ale = new ArrayList<>();
		
		for( Element e : shelf )
		{
			Element elem = e
					.isOrContainsElementsWithTheSameTypeAndTitleAs( element );
			if( elem != null )
				ale.add( elem );
		}
		
		return convertToArray( ale );
	}
	
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
	 *         the information of an instance of {@link Element} contained in
	 *         this {@link Searchable} instance that has the same type and title
	 *         as {@code element}; returns {@code null} if this
	 *         {@link Searchable} instance does not contain instances of
	 *         {@link Element} with the same type and title as {@code element}.
	 */
	public String[] getInfoAboutElementsWithTheSameTypeAndTitleAs(
			Element element ) {
		
		Element[] selectedElems = findElementsWithTheSameTypeAndTitleAs( element );
		
		if( selectedElems == null )
			return null;
		
		String[] informations = new String[selectedElems.length];
		for( int i = 0; i < selectedElems.length; ++i )
			informations[i] = selectedElems[i].toString();
		return informations;
	}
	
	
	
	// AUXILIAR METHODS
	
	// used in the method findElementsWithTheSameTypeAndTitleAs
	/**
	 * Transfers the non-{@code null} instances stored in an {@link ArrayList}
	 * of {@link Element}s to an array of {@link Element}s.
	 * 
	 * @param arrList
	 *            The {@link ArrayList} of {@link Elements}s to be converted.
	 * @return An array of {@link Element}s which has the same non-{@code null}
	 *         instances as {@code arrList}.
	 */
	private Element[] convertToArray( ArrayList< Element > arrList ) {
		
		if( arrList == null )
			return null;
		
		// cannot use size() in the following algorithm since i dont want to
		// store null entries in the result array; needs a counter
		int counter = 0;
		for( Element e : arrList )
			if( e != null )
				++counter;
		
		if( counter == 0 )
			return null;
		
		Element[] result = new Element[counter];
		int i = 0;
		for( Element e : arrList )
			if( e != null )
			{
				result[i] = e;
				++i;
			}
		
		return result;
	}
	
	// used in the methods requestElement and returnElement
	/**
	 * Returns the first instance stored in this instance of {@link Shelf} that
	 * equals {@code element}.
	 * 
	 * @param element
	 *            The instance of {@link Element} to be found in this instance
	 *            of {@link Shelf}.
	 * @return The first instance stored in this instance of {@link Shelf} that
	 *         equals {@code element}; returns {@code null} if there is no such
	 *         instance.
	 */
	public Element findFirstOfThisElement( Element element ) {
		
		Element theElement = null;
		
		for( Element e : shelf )
		{
			Element elem = e.isOrContains( element );
			if( elem != null )
			{
				theElement = elem;
				break;
			}
			
		}
		return theElement;
	}
}
