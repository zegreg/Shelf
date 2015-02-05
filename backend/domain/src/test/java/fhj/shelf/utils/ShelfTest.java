package fhj.shelf.utils;

import static org.junit.Assert.*;

import java.util.Arrays;

import fhj.shelf.utils.Book;
import fhj.shelf.utils.BookCollection;
import fhj.shelf.utils.CD;
import fhj.shelf.utils.DVD;
import fhj.shelf.utils.Element;
import fhj.shelf.utils.Shelf;

import org.junit.Before;
import org.junit.Test;

public class ShelfTest {

	Shelf shelf;
	Book book1;
	Book book2;
	CD cd;
	BookCollection col;
	
	@Before
	public void constructShelf()
	{
		book1 = new Book("Book1", "Author1");
		book2 = new Book("Book2", "Author2");
		
		col = new BookCollection("Collection of Books");
		col.addElement(book1);
		col.addElement(book2);
		
		cd = new CD("CD1", 20);

		shelf = new Shelf(1, 20);
		
		shelf.add(col);
		shelf.add(cd);
	}
	
	@Test
	public void shouldNotBeAbleToAddAnExistingElementToShelf()
	{
		assertFalse(shelf.add(cd));
	}
	
	@Test
	public void shouldNotBeAbleToAddAnElementExistingInACollectionToShelf()
	{
		assertFalse(shelf.add(book1));
	}
	
	@Test
	public void shouldNotBeAbleToAddAnElementBecauseNotEnoughFreeSpace()
	{
		Shelf littleShelf = new Shelf (2, 1);
		assertFalse(littleShelf.add(col));
	}
	
	@Test
	public void shouldNotBeAbleToAddANullElement()
	{
		assertFalse(shelf.add(null));
	}
	
//	@Test
//	public void shouldNotBeAbleToAddAnElementWithSizeBelowOne()
//	{
//		BookCollection fakeCollection = new BookCollection("Fake Collection");
//		assertFalse(shelf.add(fakeCollection));
//	}
	
	@Test
	public void shouldRemoveTheElement() {
		assertTrue(shelf.remove(cd));
	}
	
	@Test
	public void shouldNotRemoveAnElementThatIsNotThere()
	{
		assertFalse(shelf.remove(new DVD("dvd1",20)));
	}
	
	@Test
	public void shouldNotBeAbleToRemoveANullElement()
	{
		assertFalse(shelf.remove(null));
	}
	
	@Test
	public void shouldRequestTheElement()
	{
		assertEquals(cd, shelf.requestElement(cd));
		assertEquals(book1, shelf.requestElement(book1));
		
		assertNull(shelf.requestElement(cd));
		assertNull(shelf.requestElement(book1));
		assertNull(shelf.requestElement(null));
	}
	
	@Test
	public void shouldNotBeAbleToRemoveARequestedElement()
	{
		shelf.requestElement(cd);
		assertFalse(shelf.remove(cd));
	}
	
	@Test
	public void cantRequestAnElementThatIsNotInTheShelf()
	{
		DVD dvd = new DVD("dvd1",20);
		assertNull(shelf.requestElement(dvd));
	}
	
	@Test
	public void shouldNotBeAbleToReturnANullElementOfACollection()
	{
		BookCollection fakeCollection = new BookCollection("Fake Collection");
		assertFalse(shelf.returnElement(fakeCollection));
	}
	
	@Test
	public void availabilityShouldBeFalseAfterRequesting()
	{
		assertEquals(book1, shelf.requestElement(book1));
		assertTrue(shelf.returnElement(book1));
		assertFalse(shelf.returnElement(cd));
		assertFalse(shelf.returnElement(null));
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void shouldThrowAnException()
	{
		new Shelf(3, -4);
	}
	
	@Test
	public void shouldCheckIfTheShelfHasElementsWithSameTitleAndType()
	{
		Element[] ale = new Element[1];
		ale[0] = cd;
		
		assertArrayEquals(ale, (shelf.findElementsWithTheSameTypeAndTitleAs(cd)));
	}
	
	@Test 
	public void shouldGetInformationAboutElementsWithSameTypeAndTitle()
	{
		String[] ale = new String[1];
		ale[0] = cd.toString();
		
		assertArrayEquals(ale, (shelf.getInfoAboutElementsWithTheSameTypeAndTitleAs(cd)));
		assertNull(shelf.getInfoAboutElementsWithTheSameTypeAndTitleAs(null));
	}
	
	@Test
	public void shouldReturnInfoOfAllElements()
	{
		Shelf littleShelf = new Shelf(4, 2);
		DVD dvd1 = new DVD("SLB", 33);
		littleShelf.add(dvd1);
		
		StringBuilder expected = new StringBuilder("[").append( "\nDVD Title: " )
		.append( dvd1.getTitle() ).append( "\nDVD Duration: " )
		.append( dvd1.getDuration() ).append( "\nIs Available: " )
		.append( dvd1.isAvailable() ).append("]");
		
		assertEquals(expected.toString(),Arrays.toString(littleShelf.getInfoAboutAllElementsContained()));
	}

}
