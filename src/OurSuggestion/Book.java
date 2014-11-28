package OurSuggestion;


public class Book extends RequestableElement
{
	
	/**
	 * @field author - String that represents the name of the author of the
	 *        {@code Book}.
	 */
	private final String author;
	
	/**
	 * Book constructor that allows the creation of instances of Book receiving
	 * as parameters two Strings with the title and the name of the author of
	 * the book.
	 * 
	 * It uses the constructor of it's super class {@code Element} to create and
	 * associate to the created object two Strings that will contain the title
	 * of the Book and a representation of its type and a boolean variable that
	 * will represent the status of the object in a Shelf (if it's requested or
	 * not).
	 * 
	 * @param title
	 *            - {@code title} of the Book.
	 * @param author
	 *            - name of the {@code author} of the Book. If it's null will
	 *            throw an {@link IllegalArgumentException}.
	 */
	public Book( String title, String author ) {
		
		super( title, "Book" );
		
		if( author == null )
			throw new IllegalArgumentException( "The author cannot be null!" );
		
		this.author = author;
	}
	
	/**
	 * Override of the method {@link compareTo}, from the {@link comparable}
	 * interface and used by the {@link TreeSet} class to automaticaly organize
	 * the its elements, that will allow the books to be alphabetically
	 * organized by {@code author} in a {@link BooksCllection}.
	 * 
	 * If their author is the same they will be organized alphabetically by
	 * their {@code title}. This is achieved by using the {@code compareTo}
	 * method of the String class to compare the author and titles of the books.
	 * 
	 * If the Element given as parameter is null will throw an
	 * {@code IllegalArgumentException} and if its not and instance of Book will
	 * throw a {@link ClassCAstException}.
	 * 
	 * @param book
	 *            - an {@code Element} must be given as parameter.
	 * 
	 * @return - returns an int number that will represent the position the book
	 *         will have relative to another in a {@code BooksCollection}. If
	 *         it's less than 0 it will come first and if it's bigger than zero
	 *         it will come after.
	 */
	@Override
	public int compareTo( Element book ) {
		
		int resultBase = super.compareTo( book );
		if( resultBase != 0 )
			return resultBase;
		
		return this.author.compareTo( ((Book)book).getAuthor() );
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
