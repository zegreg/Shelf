package fhj.shelf.utils;

import static org.junit.Assert.*;
import fhj.shelf.utils.Book;
import fhj.shelf.utils.CD;
import fhj.shelf.utils.DVD;

import org.junit.Before;
import org.junit.Test;

public class RequestableElementTest {
	
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
	
	@Test
	public void hasSameNameAndType() {
		assertEquals(book, (new Book("A book", "An author")).isOrContainsElementsWithTheSameTypeAndTitleAs(book));
		assertEquals(dvd, (new DVD("A DVD", 90)).isOrContainsElementsWithTheSameTypeAndTitleAs(dvd));
		assertEquals(cd, (new CD("A CD", 20)).isOrContainsElementsWithTheSameTypeAndTitleAs(cd));
	}
	
	@Test
	public void hasNotSameNameAndType(){
		assertNull(book.isOrContainsElementsWithTheSameTypeAndTitleAs(cd));
		assertNull(book.isOrContainsElementsWithTheSameTypeAndTitleAs(dvd));
		assertNull(book.isOrContainsElementsWithTheSameTypeAndTitleAs(new Book("Other book", "Other author")));
		
		assertNull(cd.isOrContainsElementsWithTheSameTypeAndTitleAs(book));
		assertNull(cd.isOrContainsElementsWithTheSameTypeAndTitleAs(dvd));
		assertNull(cd.isOrContainsElementsWithTheSameTypeAndTitleAs(new CD("Other cd", 30)));
		
		assertNull(dvd.isOrContainsElementsWithTheSameTypeAndTitleAs(cd));
		assertNull(dvd.isOrContainsElementsWithTheSameTypeAndTitleAs(book));
		assertNull(dvd.isOrContainsElementsWithTheSameTypeAndTitleAs(new DVD("Other dvd", 180)));
	}
	
	@Test
	public void shouldBeItself()
	{
		assertEquals(book, book.isOrContains(book));
		assertEquals(dvd, dvd.isOrContains(dvd));
		assertEquals(cd, cd.isOrContains(cd));
	}
	
	@Test
	public void shouldNotBeOtherThingsOtherThanItself()
	{
		assertNull(book.isOrContains(cd));
		assertNull(book.isOrContains(dvd));
		assertNull(book.isOrContains(new Book("Other book", "Other author")));
		
		assertNull(cd.isOrContains(book));
		assertNull(cd.isOrContains(dvd));
		assertNull(cd.isOrContains(new CD("Other cd", 30)));
		
		assertNull(dvd.isOrContains(cd));
		assertNull(dvd.isOrContains(book));
		assertNull(dvd.isOrContains(new DVD("Other dvd", 180)));
	}
	
}
