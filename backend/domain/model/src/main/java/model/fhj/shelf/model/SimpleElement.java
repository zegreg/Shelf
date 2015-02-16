package model.fhj.shelf.model;

/**
 * Class whose instances represent simple elements that can be put in a shelf.
 * Simple elements have a title, they are not sets (they do not contain other
 * elements) and they occupy 1 unit of space in a shelf. Some examples of simple
 * elements are books, cds and dvds.
 * 
 * @author (original) Daniel Gomes, Filipe Maia, Pedro Antunes
 * @author (revisionSOLID) Eva Gomes, Hugo Leal, Lucas Andrade
 */
public abstract class SimpleElement extends Element {

	// CONSTRUCTOR

	/**
	 * Creates an instance of {@link RequestableElement} with title
	 * {@code title}.
	 * 
	 * @param title
	 *            The title of this element.
	 * @throws IllegalArgumentException
	 *             If {@code title} is {@code null}.
	 */
	public SimpleElement(long elementId, String title) {
		super(elementId, title);

	}

	// METHODS INHERIT FROM Element

	/**
	 * Returns 1. Simple elements represented by instances of
	 * {@link RequestableElement} always have size 1 since they occupy only 1
	 * unit of space in a shelf.
	 * 
	 * @return 1.
	 */
	public int getSize() {
		return 1;
	}

	/**
	 * Checks whether {@code this} has the same runtime type and the same title
	 * as {@code element}; returns {@code this} if so or {@code null} otherwise.
	 * <p>
	 * Since this class's instances represent simple elements, they do not
	 * contain other elements, this method checks only whether {@code this}
	 * <b>is</b> an instance of {@link Element} with the same type and title as
	 * {@code element}.
	 * </p>
	 * 
	 * @param element
	 *            The instance of {@link Element} with which to compare the type
	 *            and the title.
	 * @return {@code this} if {@code this} has the same runtime type and the
	 *         same title as {@code element};<br>
	 *         {@code null} otherwise.
	 */
	public Element isOrContainsElementsWithTheSameTypeAndTitleAs(Element element) {

		if (isInstanceWithTheSameTypeAndTitleAs(element))
			return this;

		return null;
	}

	/**
	 * Checks if {@code this.equals(element)} is {@code true}; returns
	 * {@code this} if so or {@code null} otherwise.
	 * <p>
	 * Since this class's instances represent simple elements, they do not
	 * contain other elements, this method checks only whether {@code this}
	 * <b>is</b> {@code element}.
	 * </p>
	 * 
	 * @return {@code this} if {@code this.equals(element)};<br>
	 *         {@code null} otherwise.
	 */
	public Element isOrContains(Element element) {

		if (equals(element))
			return this;

		return null;
	}

}
