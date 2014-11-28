package afterSOLIDrevisionEHL.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import afterSOLIDrevisionEHL.model.Book;
import afterSOLIDrevisionEHL.model.CD;
import afterSOLIDrevisionEHL.model.DVD;

public class ElementTest {

	Book book;
	CD cd;
	DVD dvd;
	
	@Before
	public void constructBook()
	{
		book = new Book("A book", "An author");
		cd = new CD("A CD", 20);
		dvd = new DVD("A DVD", 90);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void doesNotAcceptNullParameter()
	{
		book.compareTo(null);
	}
	
	@Test
	public void shouldBeTheSameAsItself() {
		assertEquals(0, book.compareTo(book));
		assertEquals(0, book.compareTo(new Book("A book", "An author")));
		
		assertEquals(0, cd.compareTo(cd));
		assertEquals(0, cd.compareTo(new CD("A CD", 20)));
		
		assertEquals(0, dvd.compareTo(dvd));
		assertEquals(0, dvd.compareTo(new DVD("A DVD", 90)));
	}

	@Test
	public void shouldNotBeTheSameAsOtherElement()
	{
		assertTrue(0 != book.compareTo(cd));
		assertTrue(0 != book.compareTo(dvd));
		assertTrue(0 != book.compareTo(new Book("Other book", "Other author")));
		
		assertTrue(0 != cd.compareTo(book));
		assertTrue(0 != cd.compareTo(dvd));
		assertTrue(0 != cd.compareTo(new CD("Other cd", 30)));
		
		assertTrue(0 != dvd.compareTo(cd));
		assertTrue(0 != dvd.compareTo(book));
		assertTrue(0 != dvd.compareTo(new DVD("Other dvd", 180)));
	}
	
	@Test
	public void shouldBeEqualToItself()
	{
		assertTrue(book.equals(book));
		assertTrue(book.equals(new Book("A book", "An author")));
		
		assertTrue(cd.equals(cd));
		assertTrue(cd.equals(new CD("A CD", 20)));
		
		assertTrue(dvd.equals(dvd));
		assertTrue(dvd.equals(new DVD("A DVD", 90)));
	}
	
	@Test
	public void shouldNotBeEqualToOtherObjects()
	{
		assertTrue(!book.equals(cd));
		assertTrue(!book.equals(dvd));
		assertTrue(!book.equals(new Book("Other book", "Other author")));
		
		assertTrue(!cd.equals(book));
		assertTrue(!cd.equals(dvd));
		assertTrue(!cd.equals(new CD("Other cd", 30)));
		
		assertTrue(!dvd.equals(cd));
		assertTrue(!dvd.equals(book));
		assertTrue(!dvd.equals(new DVD("Other dvd", 180)));
	}
	
	@Test
	public void shouldBeAvailable()
	{
		assertTrue(book.isAvailable());
		assertTrue(cd.isAvailable());
		assertTrue(dvd.isAvailable());
	}
	
	@Test
	public void shouldChangeAvailability()
	{
		book.changeAvailability();
		cd.changeAvailability();
		dvd.changeAvailability();
		
		assertFalse(book.isAvailable());
		assertFalse(cd.isAvailable());
		assertFalse(dvd.isAvailable());
	}
	
	@Test
	public void shouldNotBeInAShelf()
	{
		assertFalse(book.isInAShelf());
		assertFalse(cd.isInAShelf());
		assertFalse(dvd.isInAShelf());
	}
	
	@Test
	public void shouldGetInAShelf()
	{
		book.isInAShelf(true);
		cd.isInAShelf(true);
		dvd.isInAShelf(true);
		
		assertTrue(book.isInAShelf());
		assertTrue(cd.isInAShelf());
		assertTrue(dvd.isInAShelf());
	}
}
