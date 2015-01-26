package fhj.shelf.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import fhj.shelf.utils.Book;
import fhj.shelf.utils.BookCollection;
import fhj.shelf.utils.CD;

import fhj.shelf.utils.Shelf;

import fhj.shelf.utils.repos.InMemoryShelfRepository;
import fhj.shelf.utils.repos.ShelfRepository;

import org.junit.Before;
import org.junit.Test;

public class ShelfRepositoryTest {

	private ShelfRepository shelfRepository;
	
	
	Shelf shelf;
	Book book1;
	Book book2;
	CD cd;
	BookCollection col;
	
	@Before
	public void createElement()
	{
		book1 = new Book("Book1", "Author1");
		book2 = new Book("Book2", "Author2");
		
		col = new BookCollection("Collection of Books");
		col.addElement(book1);
		col.addElement(book2);
		
		cd = new CD("CD1", 20);
			
		shelf = new Shelf(10);
		shelf.add(col);
		shelf.add(cd);
		
		shelfRepository = new InMemoryShelfRepository();
		
		
		shelfRepository.add(shelf);
		
	}


@Test
public void shouldReturnIdElement()
{
	assertEquals(shelf,(Shelf)shelfRepository.getShelfById(0));

	
	
}


@Test
public void shouldRemoveAnElement()
{
	shelfRepository.remove(shelf);;
	assertFalse(shelf.equals((Shelf)shelfRepository.getShelfById(0)));
	
	
	
}

}
