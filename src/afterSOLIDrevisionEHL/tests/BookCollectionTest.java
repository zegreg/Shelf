package afterSOLIDrevisionEHL.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import afterSOLIDrevisionEHL.model.Book;
import afterSOLIDrevisionEHL.model.BookCollection;

public class BookCollectionTest {

	BookCollection col;
	Book book1;
	Book book2;
		
	@Before
	public void constructCollection()
	{
		book1 = new Book("SLB30", "Talisca");
		book2 = new Book("SLB10", "Gaitan");
			
		col = new BookCollection("O Maior Collection");
	
		col.addElement(book1);
		col.addElement(book2);
	}
		
	@Test
	public void shouldBeAvailable() 
	{
		assertTrue(col.isAvailable());
	}
		
	
	@Test
	public void toStringVisualTest()
	{
		System.out.println(col.toString());
	}
		
	@Test
	public void shouldAddNewElement()
	{
		assertTrue(col.addElement(new Book("SLB18", "Salvio")));
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
		assertFalse(col.removeElement(new Book("SLB", "SLB")));
	}
		
	@Test
	public void shouldSayTheCollectionsAreNotEqual()
	{
		assertFalse(constructOtherCollection().equals(col));
	}
		
	@Test
	public void shouldSayTheCollectionsAreEqual()
	{
		BookCollection col2 = new BookCollection("O Maior Collection");
			
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
		BookCollection col2 = new BookCollection("O Maior Collection");
			
		col2.addElement(book1);
		col2.addElement(book2);
			
		assertTrue(col.compareTo(col) == 0);
		assertTrue(col2.compareTo(col) == 0);
	}
		
	private BookCollection constructOtherCollection()
	{
		Book book3 = new Book("SLB", "SLB");
		Book book4 = new Book("SLB33", "SLB33");
			
		BookCollection col2 = new BookCollection("other collection");
		col2.addElement(book3);
		col2.addElement(book4);
			
		return col2;
	}
}


