package original.utils;
/**
 * {@code Abstract} class that implements the {@link Comparable} interface.
 * 
 * Since this is an abstract class we cannot create objects of this type but we
 * can still use it to add properties and fields to its subclasses.
 * 
 * In this case, two Strings will be associated to their instances, that will
 * contain their title and a representation of their type, and a boolean
 * variable that will represent their status in a {@link Shelf} (if it is
 * requested or not) and the implementation of a few methods that will allow us
 * to use it porperly.
 */
public abstract class Element implements Comparable<Element> {

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
	private boolean requested;

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
	public Element(String title, String type) {

		if (title == null)
			throw new IllegalArgumentException("The title cannot be null!");

		this.title = title;
		this.type = type;

		this.requested = false;
	}

	/**
	 * Method that will validade if an {@code Element} can be requested from a
	 * {@code Shelf} or not. If the {@code Element} is already requested it will
	 * return false and if not it will return true and request the
	 * {@code Element} (change the value of {@code requested} to true).
	 * 
	 * @return - returns false if the Element is requested and true otherwhise.
	 */
	public boolean requestIt() {

		if (!requested)
			return requested = true;

		return false;

	}

	/**
	 * Method that will validade if an {@code Element} can be returned to a
	 * {@code Shelf} or not. If the {@code Element} is already in the
	 * {@code Shelf} it will return false and if not it will return true and
	 * return the {@code Element} (change the value of requested to false).
	 * 
	 * @return - returns false if the {@code Element} is already in the
	 *         {@code Shelf} or true otherwhise.
	 */
	public boolean returnIt() {

		if (requested) {
			requested = false;
			return true;
		}

		return false;
	}
	
	/**
	 * {@code Abstract} method that will be implemented by the subclasses of
	 * this class. It will have the purpose of printing the specific information
	 * regarding each of the different objects contained in a {@code Shelf}.
	 */
	public abstract void printInformation();

	/**
	 * @return requested - returns the current value of the requested variable
	 *         of an {@code Element}.
	 */
	public boolean isRequested() {
		return requested;
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
}