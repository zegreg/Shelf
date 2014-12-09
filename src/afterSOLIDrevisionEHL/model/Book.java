package afterSOLIDrevisionEHL.model;


/**
 * Class whose instances represent a book. A book has a title and an author.
 * Instances of {@link Book} are ordered in lexicological order of their
 * author's name.
 * 
 * @author (original) Daniel Gomes, Filipe Maia, Pedro Antunes
 * @author (revisionSOLID) Eva Gomes, Hugo Leal, Lucas Andrade
 */
public class Book extends SimpleElement
{
	
	// INSTANCE FIELDS
	
	/**
	 * The name of the author of the this instance of {@link Book}.
	 */
	private final String author;
	
	
	
	// CONSTRUCTOR
	
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
	public Book( String title, String author) {
		
		super(title);
		
		if( author == null )
			throw new IllegalArgumentException( "The author cannot be null!" );
		
		this.author = author;
	}
	
	
	
	// OVERRIDES OF Comparable<Element> AND Object METHODS
	
	/**
	 * Sorts instances of {@link Book} by lexicological order of their author's
	 * name (using the method {@link String#compareTo(String) compareTo of class
	 * String}); if two instances of {@link Book} have the same author, they
	 * will be sorted by lexicologically order of the title (using the method
	 * {@link String#compareTo(String) compareTo of class String}). </p>
	 * <p>
	 * If {@code book} is not an instance of {@link Book}, it is returned the
	 * result of the {@link AbstractElement#compareTo(AbstractElement) compareTo of class
	 * Element}.
	 * </p>
	 * 
	 * @param book
	 *            The instance of {@link AbstractElement} with which to compare to.
	 * @throws IllegalArgumentException
	 *             If {@code element} is {@code null}.
	 */
	@Override
	public int compareTo( AbstractElement book ) {
		
		if( book == null )
			throw new IllegalArgumentException( "The element cannot be null!" );
		
		if( !getClass().equals( book.getClass() ) )
			return super.compareTo( book );
		
		int compareAuthor = author.compareTo( ((Book)book).getAuthor() );
		if( compareAuthor != 0 )
			return compareAuthor;
		
		return this.getTitle().compareTo( ((Book)book).getTitle() );
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
		
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result
				+ ((this.getTitle() == null) ? 0 : this.getTitle().hashCode());
		
		return result;
	}
	
	/**
	 * Compares the type, title and author of {@code this} and {@code book}.
	 * 
	 * @param book
	 *            The instance to be compared with.
	 * @return {@code true} if {@code this} and {@code book} are two instances
	 *         of {@link Book} with the same {@code title} and author;<br>
	 *         {@code false} otherwise.
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
	 * Returns a {@link String} representation of this instance, consisting in
	 * its title, author and availability status.
	 * 
	 * @return A {@link String} representation of this instance.
	 */
	@Override
	public String toString() {
		
		return new StringBuilder( "\nBook Title: " ).append( this.getTitle() )
				.append( "\nBook Author: " ).append( this.author )
				.append( "\nIs Available: " ).append( this.isAvailable() )
				.toString();
	}
	
	
	
	// GETTER
	
	/**
	 * Returns this book's author.
	 * 
	 * @return This book's author.
	 */
	public String getAuthor() {
		return author;
	}
	
	


}
