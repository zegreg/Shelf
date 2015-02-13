package model.fhj.shelf.model;

/**
 * Interface that imposes operations of adding and removing elements.
 * 
 * @author (original) Daniel Gomes, Filipe Maia, Pedro Antunes
 * @author (revisionSOLID) Eva Gomes, Hugo Leal, Lucas Andrade
 */
public interface Storage {

	/**
	 * Adds an element to {@code this}.
	 * 
	 * @param element
	 *            The element to be added.
	 * @return {@code true} if the adding operation was successful;<br>
	 *         {@code false} if it couldn't be added.
	 */
	public boolean add(Element element);

	/**
	 * Removes an element from {@code this}.
	 * 
	 * @param element
	 *            The element to be removed.
	 * @return {@code true} if the removing operation was successful;<br>
	 *         {@code false} if it couldn't be removed.
	 */
	public boolean remove(Element element);

}
