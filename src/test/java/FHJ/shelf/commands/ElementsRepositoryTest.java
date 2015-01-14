package test.java.FHJ.shelf.commands;

import static org.junit.Assert.*;
import main.java.FHJ.shelf.model.Book;
import main.java.FHJ.shelf.model.BookCollection;
import main.java.FHJ.shelf.model.CD;
import main.java.FHJ.shelf.model.Element;
import main.java.FHJ.shelf.model.Shelf;
import main.java.FHJ.shelf.model.repos.InMemoryElementsRepository;


import org.junit.Before;
import org.junit.Test;

import main.java.FHJ.shelf.model.repos.*;
public class ElementsRepositoryTest {


		private ElementsRepository elementsRepository;
		
		
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
					
			elementsRepository = new InMemoryElementsRepository();
			
			
			elementsRepository.insert(col);
			elementsRepository.insert(cd);
		}
	
	
	@Test
	public void shouldReturnIdElement()
	{
		assertEquals(book1.getId(),0);
		assertEquals(book2.getId(),1);
		assertEquals(col,(Element)elementsRepository.getElementById(2));
		assertEquals(cd,(Element)elementsRepository.getElementById(3));
		
		
	}
	
	
	@Test
	public void shouldRemoveAnElement()
	{
		elementsRepository.remove(col);
		assertFalse(col.equals((Element)elementsRepository.getElementById(2)));
		
		
		
	}

	
}
