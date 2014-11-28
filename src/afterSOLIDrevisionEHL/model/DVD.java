package afterSOLIDrevisionEHL.model;


/**
 * Class whose instances represent a DVD. A DVD has a title and duration.
 * Instances of {@link DVD} are ordered in ascending ordered of their duration.
 * 
 * @author (original) Daniel Gomes, Filipe Maia, Pedro Antunes
 * @author (revisãoSOLID) Eva Gomes, Hugo Leal, Lucas Andrade
 */
public class DVD extends RequestableElement
{
	
	// CAMPOS DE INSTÂNCIA
	
	/**
	 * The time length of the this instance of {@link DVD}.
	 */
	private final int duration;
	
	
	
	// CONSTRUTOR
	
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
	public DVD( String title, int duration ) {
		
		super( title );
		
		if( duration < 1 )
			throw new IllegalArgumentException(
					"The duration of a DVD cannot be less than 0 seconds!" );
		
		this.duration = duration;
	}
	
	
	
	// OVERRIDES OF OBJECT
	
	/**
	 * Overrides the method {@link Element#compareTo(Object) compareTo of class
	 * Element}.
	 * <p>
	 * Instances of {@link DVD} are ordered in ascending ordered of their
	 * duration; if two instances of {@link DVD} have the same duration, they
	 * are lexicologically ordered by their title.
	 * </p>
	 * <p>
	 * If {@code cd} is not an instance of {@link DVD}, it is returned the
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
	 * Overrides the method {@link Element#hashCode() hashCode of class Element}
	 * .
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
	 * Overrides the method {@link Element#equals() equals of class Element}.
	 * 
	 * @return {@code true} if {@code this} and {@code dvd} are two instances of
	 *         {@link DVD} with the same {@code title} and {@code duration}; {@code false} otherwise.
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
	 * Overrides the method {@link Element#toString() toString of class Element}.
	 * 
	 * @return A {@link String} representation of this instance of {@link CD}
	 *         containing its title, duration and availability.
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
}
