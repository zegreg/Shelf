package fhj.shelf.utils;

import static org.junit.Assert.*;
import fhj.shelf.utils.Book;
import fhj.shelf.utils.BookCollection;
import fhj.shelf.utils.CD;
import fhj.shelf.utils.Shelf;

import org.junit.Before;
import org.junit.Test;

public class ComposedElementTest {

	Shelf shelf;
	Book book1;
	Book book2;
	Book book3;
	Book book4;
	Book book5;
	CD cd;
	BookCollection col;
	BookCollection col2;
	BookCollection col3;
	BookCollection col4;
	
	@Before
	public void test() {
		book1 = new Book(1,"Book1", "Author1");
		book2 = new Book(2,"Book2", "Author2");
		book3 = new Book(3,"Book1", "Author1");
		book4 = new Book(4,"Book2", "Author2");
		book5 = new Book(5,"O Maior", "SLB");
		
		col = new BookCollection(6,"Collection of Books");
		col.addElement(book1);
		col.addElement(book2);
		
		col2 = new BookCollection(7,"New Collection");
		col2.addElement(book3);
		col2.addElement(book4);
		
		col3 = new BookCollection(8,"SLB");
		col4 = new BookCollection(9,"O Maior");
		cd = new CD(10,"CD1", 20);

		shelf = new Shelf(6, 20);
		
		shelf.add(col2);
		shelf.add(col);
		shelf.add(cd);
	}
	
	@Test
	public void shouldNotAddANullElementToCollection()
	{
		assertFalse(col2.addElement(null));
	}
	
	@Test
	public void shouldNotAddAnElementOfOtherCollection()
	{
		assertFalse(col2.addElement(book1));
	}
	
	@Test
	public void shouldAddACollectionToAnotherCollection()
	{
		assertTrue(col4.addCollection(col3));
	}
	
	@Test
	public void shouldRemoveACollection()
	{
		col.addCollection(col2);
		
		assertFalse(col.removeCollection(col2));
	}
	
	@Test
	public void shouldRemoveAElementOfACollection()
	{
		col4.addCollection(col3);
		
		assertFalse(col4.removeElement(book5));
	}
	
	@Test 
	public void shouldChangeAvailability()
	{		
		col2.setAvailability( false );
		assertFalse(shelf.remove(col2));
		col2.setAvailability( true );
		assertTrue(shelf.remove(col2));
	}

}
