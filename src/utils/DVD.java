package utils;
/**
 * Class that extends the class {@link Element} and defines objects of the the
 * type {@code DVD}.
 */
public class DVD extends Element {

	/**
	 * @field duration - int number that represents duration of the {@code DVD}.
	 */
	private final int duration;

	/**
	 * DVD constructor that allows the creation of instances of DVD receiving as
	 * parameters a String with the title of the cd and its duration in minutes.
	 * 
	 * It uses the constructor of it's super class {@code Element} to create and
	 * associate to the created object two Strings that will contain the title
	 * of the DVD and a representation of its type and a boolean variable that
	 * will represent the status of the object in a Shelf (if it's requested or
	 * not).
	 * 
	 * @param title
	 *            - {@code title} of the DVD.
	 * @param duration
	 *            - the duration of a DVD. If it's less than 1 will throw an
	 *            {@link IllegalArgumentException}.
	 */
	public DVD(String title, int duration) {

		super(title, "DVD");

		if (duration < 1)
			throw new IllegalArgumentException(
					"The duration of a DVD cannot be less than 0 seconds!");

		this.duration = duration;
	}

	/**
	 * Override of the method {@link compareTo}, from the {@link comparable}
	 * interface and used by the {@link TreeSet} class to automaticaly organize
	 * the its elements, that will allow the DVDs to be organized by ascending
	 * order of it's {@code duration} in a {@link DVDsCollection}.
	 * 
	 * If their duration is the same they will be organized alphabetically by
	 * their {@code title}. This is achieved by using the {@code compareTo}
	 * method of the String class to compare titles of the DVDs.
	 * 
	 * If the Element given as parameter is null will throw an
	 * {@code IllegalArgumentException} and if its not and instance of DVD will
	 * throw a {@link ClassCAstException}.
	 * 
	 * @param dvd
	 *            - an {@code Element} must be given as parameter.
	 * 
	 * @return - returns an int number that will represent the position the dvd
	 *         will have relative to another in a {@code DVDsCollection}. If
	 *         it's less than 0 it will come first and if it's bigger than zero
	 *         it will come after.
	 */
	@Override
	public int compareTo(Element dvd) {

		if (dvd == null)
			throw new IllegalArgumentException("The element cannot be null!");

		if (!getClass().equals(dvd.getClass()))
			throw new ClassCastException("The element is not a DVD");

		int compare = this.duration - ((DVD) dvd).getDuration();

		if (compare == 0)
			return this.getTitle().compareTo(((DVD) dvd).getTitle());

		return compare;
	}

	/**
	 * Override of the method {@link hashCode} from the class {@link Object}.
	 */
	@Override
	public int hashCode() {

		final int prime = 31;
		int result = 1;

		result = prime * result
				+ ((this.getTitle() == null) ? 0 : this.getTitle().hashCode());
		result = prime * result + duration;

		return result;
	}

	/**
	 * Override of the method {@link equals} from the class {@code Object} to be
	 * consistent with the {@code comparaTo} method.
	 */
	@Override
	public boolean equals(Object dvd) {

		if (this == dvd)
			return true;

		if (dvd == null)
			return false;

		if (!getClass().equals(dvd.getClass()))
			return false;

		if (this.compareTo((DVD) dvd) != 0)
			return false;

		return true;
	}

	/**
	 * Implementation of the {@code printInformation} method from the
	 * {@code Element} class that will print the specific information regarding
	 * a DVD. This information will be the title of the DVD, its duration and
	 * its requested status.
	 */
	@Override
	public void printInformation() {
		System.out.println("\nDVD Title: " + this.getTitle()
				+ "\nDVD Duration (min): " + this.duration + "\nIs Requested: "
				+ this.isRequested());
	}

	/**
	 * @return duration - returns {@code duration} of the DVD.
	 */
	public int getDuration() {
		return duration;
	}
}