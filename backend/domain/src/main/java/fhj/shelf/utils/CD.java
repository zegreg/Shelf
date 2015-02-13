package fhj.shelf.utils;


/**
 * Class whose instances represent a CD. A CD has a title and a number of
 * tracks. Instances of {@link CD} are ordered in descending order of number of
 * tracks.
 * 
 * @author (original) Daniel Gomes, Filipe Maia, Pedro Antunes
 * @author (revisionSOLID) Eva Gomes, Hugo Leal, Lucas Andrade
 */
public class CD extends SimpleElement
{
	
	// INSTANCE FIELDS
	
	/**
	 * The number of tracks of the this instance of {@link CD}.
	 */
	private int tracksNumber;
	
	
	
	// CONSTRUCTOR
	
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
	public CD(long cdId, String title, int tracksNumber) {
		
		super(cdId, title);
		
		if( tracksNumber < 1 )
			throw new IllegalArgumentException(
					"The minumum number of tracks is 1!" );
		
		this.tracksNumber = tracksNumber;
	}
	
	
	
	// OVERRIDES OF Comparable<Element> AND Object METHODS
	
	/**
	 * Sorts instances of {@link CD} by descending order of number of tracks; if
	 * two instances of {@link CD} have the same number of tracks, they are
	 * lexicologically ordered by their title. </p>
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
		result = prime * result + tracksNumber;
		
		return result;
	}
	
	/**
	 * Compares the type, title and number of tracks of {@code this} and
	 * {@code cd}.
	 * 
	 * @param cd
	 *            The instance to be compared with.
	 * @return {@code true} if {@code this} and {@code cd} are two instances of
	 *         {@link CD} with the same {@code title} and number of tracks;<br>
	 *         {@code false} otherwise.
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
	 * Returns a {@link String} representation of this instance, consisting in
	 * its title, number of tracks and availability status.
	 * 
	 * @return A {@link String} representation of this instance.
	 */
	@Override
	public String toString() {
		return new StringBuilder( "eid=" ).append(this.getId())
				.append("&type=").append( "CD" )
				.append( "&Number of Tracks: " ).append( this.tracksNumber )
				.append( "&Is Available: " ).append( this.isAvailable() )
				.toString();
	}
	
	
	
	// GETTER
	
	/**
	 * Returns this cd's number of tracks.
	 * 
	 * @return This cd's number of tracks.
	 */
	public int getTracksNumber() {
		return tracksNumber;
	}

	public void setTracksNumber(int newTracksNumber) {
	this.tracksNumber = newTracksNumber;
	}

}
