package main.java.FHJ.shelf.model;


/**
 * Class whose instances represent a DVD. A DVD has a title and duration.
 * Instances of {@link DVD} are ordered in ascending ordered of their duration.
 * 
 * @author (original) Daniel Gomes, Filipe Maia, Pedro Antunes
 * @author (revisionSOLID) Eva Gomes, Hugo Leal, Lucas Andrade
 */
public class DVD extends SimpleElement
{
	
	// INSTANCE FIELDS
	
	/**
	 * The time length of the this instance of {@link DVD}.
	 */
	private int duration;
	
	
	
	// CONSTRUCTOR
	
	/**
	 * Creates an instance of {@link DVD} with title {@code title} whose time
	 * length is {@code duration}.
	 * 
	 * @param title
	 *            The title of this DVD.
	 * @param duration
	 *            The time length of the DVD.
	 * @throws IllegalArgumentException
	 *             If {@code title} or {@code duration} are {@code null}.
	 */
	public DVD( String title, int duration) {
		
		super( title);
		
		if( duration < 1 )
			throw new IllegalArgumentException(
					"The duration of a DVD cannot be less than 0 seconds!" );
		
		this.duration = duration;
	}
	
	
	
	// OVERRIDES OF Comparable<Element> AND Object METHODS
	
	/**
	 * Sorts instances of {@link DVD} by ascending ordered of their duration; if
	 * two instances of {@link DVD} have the same duration, they are
	 * lexicologically ordered by their title. </p>
	 * <p>
	 * If {@code dvd} is not an instance of {@link DVD}, it is returned the
	 * result of the {@link Element#compareTo(Element) compareTo of class
	 * Element}.
	 * </p>
	 * 
	 * @param dvd
	 *            The instance of {@link Element} with which to compare to.
	 * @throws IllegalArgumentException
	 *             If {@code element} is {@code null}.
	 */
	@Override
	public int compareTo( Element dvd ) {
		
		if( dvd == null )
			throw new IllegalArgumentException( "The book cannot be null!" );
		
		if( !getClass().equals( dvd.getClass() ) )
			return super.compareTo( dvd );
		
		int compareDuration = this.duration - ((DVD)dvd).duration;
		if( compareDuration != 0 )
			return compareDuration;
		
		return this.getTitle().compareTo( ((DVD)dvd).getTitle() );
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
		result = prime * result + duration;
		
		return result;
	}
	
	/**
	 * Compares the type, title and duration of {@code this} and {@code other}.
	 * 
	 * @param other
	 *            The instance to be compared with.
	 * @return {@code true} if {@code this} and {@code dvd} are two instances of
	 *         {@link DVD} with the same {@code title} and {@code duration};<br>
	 *         {@code false} otherwise.
	 */
	@Override
	public boolean equals( Object dvd ) {
		
		if( !super.equals( dvd ) )
			return false;
		
		if( duration != ((DVD)dvd).duration )
			return false;
		
		return true;
	}
	
	/**
	 * Returns a {@link String} representation of this instance, consisting in
	 * its title, duration and availability status.
	 * 
	 * @return A {@link String} representation of this instance.
	 */
	@Override
	public String toString() {
		return new StringBuilder( "\nDVD Title: " ).append( this.getTitle() )
				.append( "\nDVD Duration: " ).append( this.duration )
				.append( "\nIs Available: " ).append( this.isAvailable() )
				.toString();
	}
	
	
	
	// GETTER
	
	/**
	 * Returns this dvd's duration.
	 * 
	 * @return This dvd's duration.
	 */
	public int getDuration() {
		return duration;
	}
	
	public void setDuration(int newDuration){
		this.duration = newDuration;
	}

}
