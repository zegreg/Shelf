package fhj.shelf.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * Class whose instances represent a shelf.
 *
 * @author (original) Daniel Gomes, Filipe Maia, Pedro Antunes
 * @author (revisãoSOLID) Eva Gomes, Hugo Leal, Lucas Andrade
 */
public class Shelf extends AbstractShelf implements Storage, RequestManager,
		Searchable {

	// INSTANCE FIELDS

	/**
	 * The elements container.
	 */
	private Collection<Element> shelf;

	/**
	 * The maximum number of elements this shelf can store.
	 */
	private final int capacity;

	/**
	 * The capacity minus the number of elements already stored in this shelf.
	 */
	private int freeSpace;

	// CONSTRUCTOR

	/**
	 * Creates an instance of {@link Shelf} that stores a maximum number of
	 * {@code capacity} elements.
	 * 
	 * @param capacity
	 *            The maximum number of elements this shelf can store.
	 * @throws IllegalArgumentException
	 *             If {@code capacity} is less than 1.
	 */
	public Shelf(int capacity) {

		if (capacity < 1)
			throw new IllegalArgumentException(
					"The Shelf must have a capacity bigger than 0");

		this.capacity = capacity;
		this.freeSpace = capacity;
		shelf = new TreeSet<Element>();
	}

	// OVERRIDES OF Object METHODS

	/**
	 * Returns the String representation of all the contents of this
	 * {@link Shelf}.
	 * 
	 * @return The String representation of all the contents of this
	 *         {@link Shelf}.
	 */
	public String toString() {

		StringBuilder builder = new StringBuilder("SHELF CONTENTS\n\n\n");

		Iterator<Element> iterator = shelf.iterator();
		while (iterator.hasNext())
			builder.append(iterator.next().toString()).append("\n\n\n");

		return builder.toString();
	}

	// OVERRIDES OF Storage METHODS

	/**
	 * Adds an instance of {@link Element} to this shelf. If added, we will say
	 * that {@code this} contains {@code element}.
	 * <p>
	 * The instance {@code element} will not be added to {@code this} if:
	 * <ul>
	 * <li>it is {@code null};</li>
	 * <li>it was previously added to a collection;</li>
	 * <li>it was previously added to a shelf;</li>
	 * <li>{@code this} already contains an instance that {@code equals}
	 * {@code element}or</li>
	 * <li>{@code element} has size 0 or occupies more space than the currently
	 * free space of this shelf.</li>
	 * </ul>
	 * </p>
	 * 
	 * @param element
	 *            The element to be added to {@code this}.
	 * @return {@code true} if the element was successfully added; <br>
	 *         {@code false} if the element was not added.
	 */
	@Override
	public boolean add(Element element) {

		if (element == null || element.isInACollection()
				|| element.isInAShelf() || shelf.contains(element))
			return false;

		int elemSize = element.getSize();

		if (elemSize > freeSpace) 
			return false;

		if (shelf.add(element)) {
			freeSpace -= elemSize;
			element.isInAShelf(true);
			element.setAvailability(true);
			element.isRequested(false);
			return true;
		}
		return false;
	}

	/**
	 * Removes the instance {@code element} from this shelf.
	 * <p>
	 * The instance {@code element} will not be removed from {@code this} if:
	 * <ul>
	 * <li>it is {@code null};</li>
	 * <li>it is not contained in {@code this} or</li>
	 * <li>it is not available.</li>
	 * </ul>
	 * </p>
	 * 
	 * @param element
	 *            The element to be removed from {@code this}.
	 * @return {@code true} if the element was successfully removed; <br>
	 *         {@code false} if the element was not removed.
	 */
	@Override
	public boolean remove(Element element) {

		if (element == null || !shelf.contains(element)
				|| !element.isAvailable())
			return false;

		if (shelf.remove(element)) {
			freeSpace += element.getSize();
			element.isInAShelf(false);
			element.setAvailability(false);
			element.isRequested(false);
			return true;
		}
		return false;
	}

	// OVERRIDES OF RequestManager METHODS

	/**
	 * Requests an instance that equals {@code element} from this shelf.
	 * <p>
	 * The operation will not succeed if:
	 * <ul>
	 * <li>{@code element} is {@code null};</li>
	 * <li>no element of {@code this} equals {@code element} or</li>
	 * <li>no element of {@code this} that equals {@code element} is available.</li>
	 * </ul>
	 * </p>
	 * 
	 * @param element
	 *            The element to be removed from {@code this}.
	 * @return {@code true} if an element was successfully requested; <br>
	 *         {@code false} if the element was not requested.
	 */
	@Override
	public Element requestElement(Element element) {

		if (element == null)
			return null;

		for (Element e : shelf) {
			if (e != null && e.isAvailable()) {
				Element elem = e.isOrContains(element);
				if (elem != null) {
					e.setAvailability(false);
					elem.isRequested(true);
					return elem;
				}
			}
		}
		return null;

	}

	/**
	 * Returns the instance {@code element} to this shelf.
	 * <p>
	 * The instance {@code element} will not be returned to {@code this} if:
	 * <ul>
	 * <li>it is {@code null};</li>
	 * <li>it is not an element of {@code this} shelf or</li>
	 * <li>it is not requested.</li>
	 * </ul>
	 * </p>
	 * 
	 * @param element
	 *            The element to be returned to {@code this}.
	 * @return {@code true} if the element was successfully returned; <br>
	 *         {@code false} if the element was not returned.
	 */
	@Override
	public boolean returnElement(Element element) {

		if (element == null)
			return false;

		for (Element e : shelf) {
			if (e != null && !e.isAvailable()) {
				Element elem = e.isOrContains(element);
				if (elem != null) {
					e.setAvailability(true);
					elem.isRequested(false);
					return true;
				}
			}
		}
		return false;
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
	public Element[] findElementsWithTheSameTypeAndTitleAs(Element element) {

		ArrayList<Element> ale = new ArrayList<>();

		for (Element e : shelf) {
			Element elem = e
					.isOrContainsElementsWithTheSameTypeAndTitleAs(element);
			if (elem != null)
				ale.add(elem);
		}

		return convertToArray(ale);
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
			Element element) {

		Element[] selectedElems = findElementsWithTheSameTypeAndTitleAs(element);

		if (selectedElems == null)
			return null;

		String[] informations = new String[selectedElems.length];
		for (int i = 0; i < selectedElems.length; ++i)
			informations[i] = selectedElems[i].toString();
		return informations;
	}

	/**
	 * Gets information about all the elements stored.
	 * <p>
	 * Makes use of the method {@code toString()} implemented in the class that
	 * defines the type of {@code element}.
	 * </p>
	 * 
	 * @return An array of {@link String}s in which each String corresponds to
	 *         the information of an instance of {@link Element} contained in
	 *         this {@link Searchable} instance.
	 */
	public String[] getInfoAboutAllElementsContained() {

		String[] infos = new String[capacity - freeSpace];
		int index = 0;
		for (Element e : shelf)
			if (e != null) {
				infos[index] = e.toString();
				++index;
			}

		return infos;
	}

	@Override
	public int getCapacity() {
		return capacity;
	}
	
	
	@Override
	public int getFreeSpace() {
		return capacity-freeSpace;
	}
	
	
	// AUXILIAR METHOD

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
	private Element[] convertToArray(ArrayList<Element> arrList) {

		if (arrList == null)
			return null;

		// cannot use size() in the following algorithm since i dont want to
		// store null entries in the result array; needs a counter
		int counter = 0;
		for (Element e : arrList)
			if (e != null)
				++counter;

		if (counter == 0)
			return null;

		Element[] result = new Element[counter];
		int i = 0;
		for (Element e : arrList)
			if (e != null) {
				result[i] = e;
				++i;
			}

		return result;
	}

	/**
	 * Gets information about shelf capacity
	 * 
	 * @return a String with information about shelf capacity
	 */
	@Override
	public String details() {

		StringBuilder builder = new StringBuilder("Shelf Content\n\n\n ");

		builder.append("Elements in Shelf: ").append(capacity - freeSpace).append("\nFree Space: ")
				.append(freeSpace);

		return builder.toString();
	}

	@Override
	public boolean contains(AbstractElement element) {
		return shelf.contains(element);
	}

}
