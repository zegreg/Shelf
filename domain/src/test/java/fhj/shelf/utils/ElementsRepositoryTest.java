//package fhj.shelf.utils;
//
//import static org.junit.Assert.*;
//import fhj.shelf.utils.Book;
//import fhj.shelf.utils.BookCollection;
//import fhj.shelf.utils.CD;
//import fhj.shelf.utils.Element;
//import fhj.shelf.utils.Shelf;
//import fhj.shelf.utils.repos.InMemoryElementsRepository;
//
//import org.junit.Before;
//import org.junit.Test;
//
//import fhj.shelf.utils.repos.*;
//
//public class ElementsRepositoryTest {
//
//
//		private ElementsRepository elementsRepository;
//		
//		
//		Shelf shelf;
//		Book book1;
//		Book book2;
//		CD cd;
//		BookCollection col;
//		
//		@Before
//		public void createElement()
//		{
//			book1 = new Book("Book1", "Author1");
//			book2 = new Book("Book2", "Author2");
//			
//			col = new BookCollection("Collection of Books");
//			col.addElement(book1);
//			col.addElement(book2);
//			
//			cd = new CD("CD1", 20);
//					
//			elementsRepository = new InMemoryElementsRepository();
//			
//			
//			elementsRepository.add(col);
//			elementsRepository.add(cd);
//		}
//	
//	
//	@Test
//	public void shouldReturnIdElement()
//	{
//		assertEquals(book1.getId(),0);
//		assertEquals(book2.getId(),1);
//		assertEquals(col,elementsRepository.getDatabaseElementById(2));
//		assertEquals(cd,elementsRepository.getDatabaseElementById(3));
//		
//		
//	}
//	
//	
//	@Test
//	public void shouldRemoveAnElement()
//	{
//		elementsRepository.remove(col);
//		assertFalse(col.equals(elementsRepository.getDatabaseElementById(2)));
//		
//		
//		
//	}
//
//	
//}
