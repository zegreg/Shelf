package fhj.shelf.utils;

import static org.junit.Assert.*;
import fhj.shelf.utils.Book;
import fhj.shelf.utils.BookCollection;
import fhj.shelf.utils.CD;
import fhj.shelf.utils.DVD;

import org.junit.Before;
import org.junit.Test;

public class ElementTest {

	Book book;
	CD cd;
	DVD dvd;
	
	@Before
	public void constructBook()
	{
		book = new Book(1,"A book", "An author");
		cd = new CD(2,"A CD", 20);
		dvd = new DVD(3,"A DVD", 90);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void doesNotAcceptNullTittle()
	{
		new BookCollection(1,null);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void doesNotAcceptNullParameter()
	{
		book.compareTo(null);
	}
	
	@Test
	public void shouldBeTheSameAsItself() {
		assertEquals(0, book.compareTo(book));
		assertEquals(0, book.compareTo(new Book(1,"A book", "An author")));
		
		assertEquals(0, cd.compareTo(cd));
		assertEquals(0, cd.compareTo(new CD(2,"A CD", 20)));
		
		assertEquals(0, dvd.compareTo(dvd));
		assertEquals(0, dvd.compareTo(new DVD(3,"A DVD", 90)));
	}

	@Test
	public void shouldNotBeTheSameAsOtherElement()
	{
		assertTrue(0 != book.compareTo(cd));
		assertTrue(0 != book.compareTo(dvd));
		assertTrue(0 != book.compareTo(new Book(1,"Other book", "Other author")));
		
		assertTrue(0 != cd.compareTo(book));
		assertTrue(0 != cd.compareTo(dvd));
		assertTrue(0 != cd.compareTo(new CD(2,"Other cd", 30)));
		
		assertTrue(0 != dvd.compareTo(cd));
		assertTrue(0 != dvd.compareTo(book));
		assertTrue(0 != dvd.compareTo(new DVD(3,"Other dvd", 180)));
	}
	
	@Test
	public void shouldBeEqualToItself()
	{
		assertTrue(book.equals(book));
		assertTrue(book.equals(new Book(1,"A book", "An author")));
		
		assertTrue(cd.equals(cd));
		assertTrue(cd.equals(new CD(2,"A CD", 20)));
		
		assertTrue(dvd.equals(dvd));
		assertTrue(dvd.equals(new DVD(3,"A DVD", 90)));
	}
	
	@Test
	public void shouldNotBeEqualToOtherObjects()
	{
		assertTrue(!book.equals(cd));
		assertTrue(!book.equals(dvd));
		assertTrue(!book.equals(new Book(1,"Other book", "Other author")));
		
		assertTrue(!cd.equals(book));
		assertTrue(!cd.equals(dvd));
		assertTrue(!cd.equals(new CD(2,"Other cd", 30)));
		
		assertTrue(!dvd.equals(cd));
		assertTrue(!dvd.equals(book));
		assertTrue(!dvd.equals(new DVD(3,"Other dvd", 180)));
	}
	
	@Test
	public void newInstancesShouldntBeAvailableBeforeAddingToAShelf()
	{
		assertFalse(book.isAvailable());
		assertFalse(cd.isAvailable());
		assertFalse(dvd.isAvailable());
	}
	
	@Test
	public void shouldChangeAvailability()
	{
		book.setAvailability( false );
		cd.setAvailability( false );
		dvd.setAvailability( false );
		
		assertFalse(book.isAvailable());
		assertFalse(cd.isAvailable());
		assertFalse(dvd.isAvailable());
		
		book.setAvailability( true );
		assertTrue(book.isAvailable());
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

	@Test (expected = IllegalArgumentException.class)
	public void shouldNotCompareWithANullElement()
	{
		book.compareTo(null);
	}
	
	@Test
	public void shouldReturnTheCorrectTitle()
	{
		assertEquals("A book", book.getTitle());
		assertEquals("A CD", cd.getTitle());
		assertEquals("A DVD", dvd.getTitle());
	}
	
	@Test
	public void shouldNotBeInACollection()
	{
		assertFalse(book.isInACollection());
		assertFalse(cd.isInACollection());
		assertFalse(dvd.isInACollection());
	}
	
	@Test
	public void shouldBeInACollection()
	{
		book.isInACollection(true);
		cd.isInACollection(true);
		dvd.isInACollection(true);
		
		assertTrue(book.isInACollection());
		assertTrue(cd.isInACollection());
		assertTrue(dvd.isInACollection());
	}
	
	@Test
	public void shouldNotBeRequested()
	{
		assertFalse(book.isRequested());
		assertFalse(cd.isRequested());
		assertFalse(dvd.isRequested());
	}
	
	@Test
	public void shouldBeRequested()
	{
		book.isRequested(true);
		cd.isRequested(true);
		dvd.isRequested(true);
		
		assertTrue(book.isRequested());
		assertTrue(cd.isRequested());
		assertTrue(dvd.isRequested());
	}
	
	@Test
	public void shouldNotBeNull()
	{
		assertFalse(book.isInstanceWithTheSameTypeAndTitleAs(null));
		assertFalse(cd.isInstanceWithTheSameTypeAndTitleAs(null));
		assertFalse(dvd.isInstanceWithTheSameTypeAndTitleAs(null));
	}
}
