package afterSOLIDrevisionEHL.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import afterSOLIDrevisionEHL.model.Book;
import afterSOLIDrevisionEHL.model.BookCollection;
import afterSOLIDrevisionEHL.model.CD;
import afterSOLIDrevisionEHL.model.Shelf;

public class ComposedElementTest {

	Shelf shelf;
	Book book1;
	Book book2;
	CD cd;
	BookCollection col;
	BookCollection col2;
	
	@Before
	public void test() {
		book1 = new Book("Book1", "Author1");
		book2 = new Book("Book2", "Author2");
		
		col = new BookCollection("Collection of Books");
		col.addElement(book1);
		col.addElement(book2);
		
		col2 = new BookCollection("New Collection");
		col2.addElement(book1);
		col2.addElement(book2);
		
		cd = new CD("CD1", 20);

		
		shelf = new Shelf(20);
		
		shelf.add(col2);
		shelf.add(col);
		shelf.add(cd);
	}
	
	@Test
	public void shouldRemoveACollection()
	{
		col.addCollection(col2);
		
		assertFalse(col.removeCollection(col2));
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
