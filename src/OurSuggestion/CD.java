package OurSuggestion;


/**
 * Class whose instances represent a CD. A CD has a title and a number of
 * tracks. Instances of {@link CD} are ordered in descending order of number of
 * tracks.
 * 
 * @author (original) Daniel Gomes, Filipe Maia, Pedro Antunes
 * @author (revisãoSOLID) Eva Gomes, Hugo Leal, Lucas Andrade
 */
public class CD extends RequestableElement
{
	
	// CAMPOS DE INSTÂNCIA
	
	/**
	 * The number of tracks of the this instance of {@link CD}.
	 */
	private final int tracksNumber;
	
	
	
	// CONSTRUTOR
	
	/**
	 * Creates an instance of {@link CD} with title {@code title} and
	 * {@code tracksNumber} number of tracks.
	 * 
	 * @param title
	 *            The title of this CD.
	 * @param tracksNumber
	 *            The number of tracks of the CD.
	 * @throws IllegalArgumentException
	 *             If {@code title} or {@code tracksNumber} are {@code null}.
	 */
	public CD( String title, int tracksNumber ) {
		
		super( title );
		
		if( tracksNumber < 1 )
			throw new IllegalArgumentException(
					"The minumum number of tracks is 1!" );
		
		this.tracksNumber = tracksNumber;
	}
	
	
	
	// OVERRIDES OF OBJECT
	
	/**
	 * Overrides the method {@link Element#compareTo(Object) compareTo of class
	 * Element}.
	 * <p>
	 * Instances of {@link CD} are ordered in descending order of number of
	 * tracks; if two instances of {@link CD} have the same number of tracks,
	 * they are lexicologically ordered by their title.
	 * </p>
	 * <p>
	 * If {@code cd} is not an instance of {@link CD}, it is returned the result
	 * of the {@link Element#compareTo(Element) compareTo of class Element}.
	 * </p>
	 * 
	 * @param cd
	 *            The instance of {@link Element} with which to compare to.
	 * @throws IllegalArgumentException
	 *             If {@code element} is {@code null}.
	 */
	@Override
	public int compareTo( Element cd ) {
		
		if( cd == null )
			throw new IllegalArgumentException( "The book cannot be null!" );
		
		if( !getClass().equals( cd.getClass() ) )
			return super.compareTo( cd );
		
		int compareTracks = ((CD)cd).getTracksNumber() - this.tracksNumber;
		if( compareTracks != 0 )
			return compareTracks;
		
		return this.getTitle().compareTo( ((CD)cd).getTitle() );
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
		result = prime * result + tracksNumber;
		
		return result;
	}
	
	/**
	 * Override of the method {@link equals} from the class {@code Object} to be
	 * consistent with the {@code comparaTo} method.
	 */
	@Override
	public boolean equals( Object cd ) {
		
		if( !super.equals( cd ) )
			return false;
		
		if( tracksNumber != ((CD)cd).tracksNumber )
			return false;
		
		return true;
	}
	
	/**
	 * Implementation of the {@code printInformation} method from the
	 * {@code Element} class that will print the specific information regarding
	 * a CD. This information will be the title of the CD, its number of tracks
	 * and its requested status.
	 */
	@Override
	public String toString() {
		return new StringBuilder( "\nCD Title: " ).append( this.getTitle() )
				.append( "\nNumber of Tracks: " ).append( this.tracksNumber )
				.append( "\nIs Available: " ).append( this.isAvailable() )
				.toString();
	}
	
	/**
	 * @return tracksNumber - returns the numbers of tracks the CD has.
	 */
	public int getTracksNumber() {
		return tracksNumber;
	}
}
