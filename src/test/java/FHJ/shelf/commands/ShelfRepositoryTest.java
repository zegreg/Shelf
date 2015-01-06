package test.java.FHJ.shelf.commands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import main.java.FHJ.shelf.model.Book;
import main.java.FHJ.shelf.model.BookCollection;
import main.java.FHJ.shelf.model.CD;

import main.java.FHJ.shelf.model.Shelf;

import main.java.FHJ.shelf.model.repos.InMemoryShelfRepository;
import main.java.FHJ.shelf.model.repos.ShelfRepository;

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
		
		
		shelfRepository.insert(shelf);
		
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
