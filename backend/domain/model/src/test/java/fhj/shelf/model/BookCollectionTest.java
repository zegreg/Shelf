package fhj.shelf.model;

import static org.junit.Assert.*;
import model.fhj.shelf.model.Book;
import model.fhj.shelf.model.BookCollection;

import org.junit.Before;
import org.junit.Test;


public class BookCollectionTest {

	BookCollection col;
	Book book1;
	Book book2;
		
	@Before
	public void constructCollection()
	{
		book1 = new Book(1,"SLB30", "Talisca");
		book2 = new Book(2,"SLB10", "Gaitan");
			
		col = new BookCollection(3,"O Maior Collection");
	
		col.addElement(book1);
		col.addElement(book2);
	}
		
	@Test
	public void newInstancesShouldNotBeAvailable() 
	{
		assertFalse(col.isAvailable());
	}
				
	@Test
	public void shouldAddNewElement()
	{
		assertTrue(col.addElement(new Book(3, "SLB18", "Salvio")));
	}
		
	@Test
	public void shouldNotAddRepeatedElement()
	{
		assertFalse(col.addElement(book1));
	}
		
	@Test
	public void shouldRemoveAnElement()
	{
		assertTrue(col.removeElement(book1));
	}
		
	@Test
	public void shouldNotRemoveAnElementThatIsNotThere()
	{
		assertFalse(col.removeElement(new Book(4,"SLB", "SLB")));
	}
		
	@Test
	public void shouldSayTheCollectionsAreNotEqual()
	{
		assertFalse(constructOtherCollection().equals(col));
	}
		
	@Test
	public void shouldSayTheCollectionsAreEqual()
	{
		BookCollection col2 = new BookCollection(5, "O Maior Collection");
			
		col2.addElement(book1);
		col2.addElement(book2);
			
		assertTrue(col.equals(col));
		assertTrue(col2.equals(col));
	}
		
	@Test
	public void shouldCompareTheDifferentCollections()
	{
		assertFalse(constructOtherCollection().compareTo(col) == 0);
	}
		
	@Test
	public void shouldCompareTheEqualCollections()
	{
		BookCollection col2 = new BookCollection(6, "O Maior Collection");
			
		col2.addElement(book1);
		col2.addElement(book2);
			
		assertTrue(col.compareTo(col) == 0);
		assertTrue(col2.compareTo(col) == 0);
	}
		
	private BookCollection constructOtherCollection()
	{
		Book book3 = new Book(1,"SLB", "SLB");
		Book book4 = new Book(2,"SLB33", "SLB33");
			
		BookCollection col2 = new BookCollection(3,"other collection");
		col2.addElement(book3);
		col2.addElement(book4);
			
		return col2;
	}
	
	@Test
	public void  shouldBeByAlphabeticalOrderOfAuthor()
	{
		
		StringBuilder expected = new StringBuilder( "Collection: " )
				.append( col.getTitle() ).append("\n{\n")
							.append("\nBook Title: ").append(book2.getTitle())
								.append("\nBook Author: ").append(book2.getAuthor())
									.append("\nIs Available: ").append(book2.isAvailable()).append("\n")
										.append("\nBook Title: ").append(book1.getTitle())
											.append("\nBook Author: ").append(book1.getAuthor())
												.append("\nIs Available: ").append(book1.isAvailable()).append("\n")
													.append("\n}");

		assertEquals(expected.toString(),col.toString());
	}
}


