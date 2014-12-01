package afterSOLIDrevisionEHL.tests;

import org.junit.Test;

import afterSOLIDrevisionEHL.model.Book;
import afterSOLIDrevisionEHL.model.BookCollection;
import afterSOLIDrevisionEHL.model.CD;
import afterSOLIDrevisionEHL.model.DVD;
import afterSOLIDrevisionEHL.model.Shelf;

public class NonUnitaryTests {

	@Test
	public void toStringBookVisualTest()
	{
		Book book = new Book("A book", "An author");
		System.out.println(book.toString());
	}

	@Test
	public void toStringCDVisualTest()
	{
		CD cd = new CD ("A CD", 10);
		System.out.println(cd.toString());
	}
	
	@Test
	public void toStringDVDVisualTest()
	{
		DVD dvd = new DVD ("A DVD", 120);
		System.out.println(dvd.toString());
	}
	
	@Test
	public void shouldReturnAStringWithTheInformationAboutAllTheElements()
	{
		Book book1 = new Book("Book1", "Author1");
		Book book2 = new Book("Book2", "Author2");
		
		BookCollection col = new BookCollection("Collection of Books");
		col.addElement(book1);
		col.addElement(book2);
		
		CD cd = new CD("CD1", 20);

		Shelf shelf = new Shelf(20);
		
		shelf.add(col);
		shelf.add(cd);
		System.out.println(shelf.toString());
	}
}
