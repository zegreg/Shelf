package afterSOLIDrevisionEHL.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import afterSOLIDrevisionEHL.model.Book;

public class BookTest2 {
	
	Book book1;
	Book book2;
	Book book3;
	
	@Before
	public void constructBook() 
	{
		book1 = new Book("SLB30", "Talisca");
		book2 = new Book("SLB30", "Talisca");
		book3 = new Book("SLB10", "Gaitan");
	}

	@Test
	public void shouldCompareTheEqualBook()
	{
		assertTrue(book2.compareTo(book1) == 0);
	}
	
	
	@Test
	public void shouldReturnTheBookAuthor()
	{
		assertTrue("Talisca".equals(book1.getAuthor()));
	}
	
	@Test
	public void shouldVerifyIfIsTheSameBook()
	{
		assertTrue(book1.equals(book2));
	}
	
	@Test
	public void shouldVerifyIfTheBooksAreDifferent()
	{
		assertFalse(book1.equals(book3));
	}
	
}
