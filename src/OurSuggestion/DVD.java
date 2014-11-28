package OurSuggestion;


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
	public boolean equals( Object dvd ) {
		
		if( !super.equals( dvd ) )
			return false;
		
		if( duration != ((DVD)dvd).duration )
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
	public String toString() {
		return new StringBuilder( "\nDVD Title: " ).append( this.getTitle() )
				.append( "\nDVD Duration: " ).append( this.duration )
				.append( "\nIs Available: " ).append( this.isAvailable() )
				.toString();
	}
	
	/**
	 * @return duration - returns {@code duration} of the DVD.
	 */
	public int getDuration() {
		return duration;
	}
}
