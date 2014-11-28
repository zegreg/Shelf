package OurSuggestion;


/**
 * Class whose instances represent a book. A book has a title and an author.
 * Instances of {@link Book} are ordered in lexicological order of their
 * author's name.
 * 
 * @author (original) Daniel Gomes, Filipe Maia, Pedro Antunes
 * @author (revisãoSOLID) Eva Gomes, Hugo Leal, Lucas Andrade
 */
public class Book extends RequestableElement
{
	
	// CAMPOS DE INSTÂNCIA
	
	/**
	 * The name of the author of the this instance of {@link Book}.
	 */
	private final String author;
	
	
	
	// CONSTRUTOR
	
	/**
	 * Creates an instance of {@link Book} with title {@code title} and whose
	 * author's name is {@code author}.
	 * 
	 * @param title
	 *            The title of this book.
	 * @param author
	 *            The name of the author of the book.
	 * @throws IllegalArgumentException
	 *             If {@code title} or {@code author} are {@code null}.
	 */
	public Book( String title, String author ) {
		
		super( title );
		
		if( author == null )
			throw new IllegalArgumentException( "The author cannot be null!" );
		
		this.author = author;
	}
	
	
	
	// OVERRIDES OF OBJECT
	
	/**
	 * Overrides the method {@link Element#compareTo(Object) compareTo of class
	 * Element}.
	 * <p>
	 * Instances of {@link Book} are ordered in lexicological order of their
	 * author's name; if two instances of {@link Book} have the same author,
	 * they are lexicologically ordered by their title.
	 * </p>
	 * <p>
	 * If {@code book} is not an instance of {@link Book}, it is returned the
	 * result of the {@link Element#compareTo(Element) compareTo of class
	 * Element}.
	 * </p>
	 * 
	 * @param book
	 *            The instance of {@link Element} with which to compare to.
	 * @throws IllegalArgumentException
	 *             If {@code element} is {@code null}.
	 */
	@Override
	public int compareTo( Element book ) {
		
		if( book == null )
			throw new IllegalArgumentException( "The book cannot be null!" );
		
		if( !getClass().equals( book.getClass() ) )
			return super.compareTo( book );
		
		int compareAuthor = author.compareTo( ((Book)book).getAuthor() );
		if( compareAuthor != 0 )
			return compareAuthor;
		
		return this.getTitle().compareTo( ((Book)book).getTitle() );
	}
	
	/**
	 * Override of the method {@link hashCode} from the class {@link Object}.
	 */
	@Override
	public int hashCode() {
		
		final int prime = 31;
		int result = 1;
		
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result
				+ ((this.getTitle() == null) ? 0 : this.getTitle().hashCode());
		
		return result;
	}
	
	/**
	 * Override of the method {@link equals} from the class {@code Object} to be
	 * consistent with the {@code comparaTo} method.
	 */
	@Override
	public boolean equals( Object book ) {
		
		if( !super.equals( book ) )
			return false;
		
		if( author != ((Book)book).author )
			return false;
		
		return true;
	}
	
	/**
	 * Implementation of the {@code printInformation} method from the
	 * {@code Element} class that will print the specific information regarding
	 * a Book. This information will be the title and the author of the book and
	 * its requested status.
	 */
	@Override
	public String toString() {
		
		return new StringBuilder( "\nBook Title: " ).append( this.getTitle() )
				.append( "\nBook Author: " ).append( this.author )
				.append( "\nIs Available: " ).append( this.isAvailable() )
				.toString();
	}
	
	/**
	 * @return author - returns the {@code author} of the Book.
	 */
	public String getAuthor() {
		return author;
	}
}
