package OurSuggestion;


public class CD extends RequestableElement
{
	
	/**
	 * @field tracksNumber - int number that represents the numbers of tracks
	 *        the {@code CD} has.
	 */
	private final int tracksNumber;
	
	/**
	 * CD constructor that allows the creation of instances of CD receiving as
	 * parameters a String with the title of the cd and the numbers of tracks it
	 * has.
	 * 
	 * It uses the constructor of it's super class {@code Element} to create and
	 * associate to the created object two Strings that will contain the title
	 * of the CD and a representation of its type and a boolean variable that
	 * will represent the status of the object in a Shelf (if it's requested or
	 * not).
	 * 
	 * @param title
	 *            - {@code title} of the DVD.
	 * @param tracksNumber
	 *            - numbers of tracks the DVD has. If it's less than 1 will
	 *            throw an {@link IllegalArgumentException}.
	 */
	public CD( String title, int tracksNumber ) {
		
		super( title, "CD" );
		
		if( tracksNumber < 1 )
			throw new IllegalArgumentException(
					"The minumum number of tracks is 1!" );
		
		this.tracksNumber = tracksNumber;
	}
	
	/**
	 * Override of the method {@link compareTo}, from the {@link comparable}
	 * interface and used by the {@link TreeSet} class to automaticaly organize
	 * the its elements, that will allow the CDs to be organized by descending
	 * order of it's {@code tracksNumber} in a {@link CDsCollection}.
	 * 
	 * If it's {@code tracksNumber} are the same they will be organized
	 * alphabetically by their {@code title}. This is achieved by using the
	 * {@code compareTo} method of the String class to compare the titles of the
	 * CDs.
	 * 
	 * If the Element given as parameter is null will throw an
	 * {@code IllegalArgumentException} and if its not and instance of CD will
	 * throw a {@link ClassCAstException}.
	 * 
	 * @param cd
	 *            - an {@code Element} must be given as parameter.
	 * 
	 * @return - returns an int number that will represent the position the cd
	 *         will have relative to another in a {@code CDsCollection}. If it's
	 *         less than 0 it will come first and if it's bigger than zero it
	 *         will come after.
	 */
	@Override
	public int compareTo( Element cd ) {
		
		if( cd == null )
			throw new IllegalArgumentException( "The element cannot be null!" );
		
		if( !getClass().equals( cd.getClass() ) )
			throw new ClassCastException( "The element is not a CD" );
		
		int compare = ((CD)cd).getTracksNumber() - this.tracksNumber;
		
		if( compare == 0 )
			return this.getTitle().compareTo( ((CD)cd).getTitle() );
		
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
		
		if( tracksNumber!= ((CD)cd).tracksNumber )
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
