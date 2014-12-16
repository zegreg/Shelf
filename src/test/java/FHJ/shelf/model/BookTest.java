package test.java.FHJ.shelf.model;

import static org.junit.Assert.*;
import main.java.FHJ.shelf.model.Book;
import main.java.FHJ.shelf.model.CD;

import org.junit.Before;
import org.junit.Test;

public class BookTest {

	Book book;
	Book book1;
	Book book2;
	CD book3;
	Book book4;
	
	@Before
	public void constructBook()
	{
		book = new Book("A book", "An author");
		book1 = new Book("A book", "author");
		book2 = new Book("SLB30", "Talisca");
		book3 = new CD("A book", 3);
		book4 = new Book("A book", "An author");
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void shouldReturnExceptionIfAuthorIsNull() {
		new Book("A book", null);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void shouldReturnExceptionIfNameIsNull() {
		new Book(null, "author");
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void shouldReturnExceptionIfBookIsNull() {
		book.compareTo(null);
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
		assertTrue(0 >= book.compareTo(book1));
		assertTrue(0 != book.compareTo(book2));
	}
		
	@Test
	public void shouldNotBeOfTheSameType()
	{
		assertTrue(0 <= book3.compareTo(book1));
	}
	
	@Test
	public void shouldHaveTheSameHashCode()
	{
		Book bookRef = book;

		assertTrue(bookRef.hashCode() == book.hashCode());
		assertTrue(book.hashCode() == book4.hashCode());
	}

	@Test
	public void shouldReturnObjectHashCode()
	{	
		int hashCodeExpected = 31*1 + book.getAuthor().hashCode();
		hashCodeExpected = 31*hashCodeExpected + book.getTitle().hashCode();

		assertTrue(hashCodeExpected == book.hashCode());
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
		assertFalse(book.equals(book1));
		assertFalse(book.equals(book2));
	}

	@Test
	public void shouldReturnTheBookAuthor()
	{
		assertTrue("An author".equals(book.getAuthor()));
	}

	@Test
	public void  shouldReturnBookInformation()
	{
		StringBuilder expected = new StringBuilder("\nBook Title: ").append(book2.getTitle())
											.append("\nBook Author: ").append(book2.getAuthor())
												.append("\nIs Available: ").append(book2.isAvailable());
									
		assertEquals(expected.toString(),book2.toString());
	}
}
