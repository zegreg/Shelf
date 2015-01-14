package test.java.FHJ.shelf.model;

import static org.junit.Assert.*;
import main.java.FHJ.shelf.model.Book;
import main.java.FHJ.shelf.model.BookCollection;
import main.java.FHJ.shelf.model.CD;
import main.java.FHJ.shelf.model.Shelf;

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
		book1 = new Book("Book1", "Author1");
		book2 = new Book("Book2", "Author2");
		book3 = new Book("Book1", "Author1");
		book4 = new Book("Book2", "Author2");
		book5 = new Book("O Maior", "SLB");
		
		col = new BookCollection("Collection of Books");
		col.addElement(book1);
		col.addElement(book2);
		
		col2 = new BookCollection("New Collection");
		col2.addElement(book3);
		col2.addElement(book4);
		
		col3 = new BookCollection("SLB");
		col4 = new BookCollection("O Maior");
		cd = new CD("CD1", 20);

		shelf = new Shelf(20);
		
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
