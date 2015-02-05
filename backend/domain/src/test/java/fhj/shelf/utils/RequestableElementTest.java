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
		book = new Book(1,"A book", "An author");
		cd = new CD(2,"A CD", 20);
		dvd = new DVD(3,"A DVD", 90);
	}
	
	@Test
	public void hasSameNameAndType() {
		assertEquals(book, (new Book(1,"A book", "An author")).isOrContainsElementsWithTheSameTypeAndTitleAs(book));
		assertEquals(dvd, (new DVD(2,"A DVD", 90)).isOrContainsElementsWithTheSameTypeAndTitleAs(dvd));
		assertEquals(cd, (new CD(3,"A CD", 20)).isOrContainsElementsWithTheSameTypeAndTitleAs(cd));
	}
	
	@Test
	public void hasNotSameNameAndType(){
		assertNull(book.isOrContainsElementsWithTheSameTypeAndTitleAs(cd));
		assertNull(book.isOrContainsElementsWithTheSameTypeAndTitleAs(dvd));
		assertNull(book.isOrContainsElementsWithTheSameTypeAndTitleAs(new Book(1,"Other book", "Other author")));
		
		assertNull(cd.isOrContainsElementsWithTheSameTypeAndTitleAs(book));
		assertNull(cd.isOrContainsElementsWithTheSameTypeAndTitleAs(dvd));
		assertNull(cd.isOrContainsElementsWithTheSameTypeAndTitleAs(new CD(2,"Other cd", 30)));
		
		assertNull(dvd.isOrContainsElementsWithTheSameTypeAndTitleAs(cd));
		assertNull(dvd.isOrContainsElementsWithTheSameTypeAndTitleAs(book));
		assertNull(dvd.isOrContainsElementsWithTheSameTypeAndTitleAs(new DVD(3,"Other dvd", 180)));
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
		assertNull(book.isOrContains(new Book(1,"Other book", "Other author")));
		
		assertNull(cd.isOrContains(book));
		assertNull(cd.isOrContains(dvd));
		assertNull(cd.isOrContains(new CD(2,"Other cd", 30)));
		
		assertNull(dvd.isOrContains(cd));
		assertNull(dvd.isOrContains(book));
		assertNull(dvd.isOrContains(new DVD(3,"Other dvd", 180)));
	}
	
}
