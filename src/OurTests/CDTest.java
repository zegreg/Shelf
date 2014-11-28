package OurTests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import OurSuggestion.Book;

public class CDTest {

Book book;
	
	@Before
	public void constructBook()
	{
		book = new Book("A book", "An author");
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void shouldReturnExceptionIfAuthorIsNull() {
		new Book("A book", null);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void shouldReturnExceptionIfNameIsNull() {
		new Book(null, "author");
	}
	
	@Test
	public void shouldBeTheSameBook()
	{
		assertEquals(0, book.compareTo(new Book("A book", "An author")));
		assertEquals(0, book.compareTo(book));
	}
	
	@Test
	public void shouldNotBeTheSameBook()
	{
		assertTrue(0 != book.compareTo(new Book("Other book", "Other author")));
	}
	
	@Test
	public void shouldBeEqual()
	{
		assertTrue(book.equals(new Book("A book", "An author")));
		assertTrue(book.equals(book));
	}
	
	@Test
	public void shouldNotBeEqual()
	{
		assertFalse(book.equals(new Book("Other book", "Other author")));
	}
	
	@Test
	public void toStringVisualTest()
	{
		System.out.println(book.toString());
	}

}
