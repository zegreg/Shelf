package fhj.shelf.utils;

import fhj.shelf.utils.Book;
import fhj.shelf.utils.BookCollection;
import fhj.shelf.utils.CD;
import fhj.shelf.utils.DVD;
import fhj.shelf.utils.DVDCollection;

import org.junit.Test;

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
	public void toStringBookCollectionVisualTest()
	{
		Book book1 = new Book("SLB30", "Talisca");
		Book book2 = new Book("SLB10", "Gaitan");
			
		BookCollection col = new BookCollection("O Maior Collection");
	
		col.addElement(book1);
		col.addElement(book2);
		System.out.println(col.toString());
	}
	
//	@Test
//	public void toStringCDCollectionVisualTest()
//	{
//		CD cd1 = new CD("CD1", 10);
//		CD cd2 = new CD("CD2", 20);
//		
//		CDCollection col = new CDCollection("My CD collection");
//		
//		col.addElement(cd1);
//		col.addElement(cd2);
//		
//		System.out.println(col.toString());
//	}
	
	@Test
	public void toStringDVDCollectionVisualTest()
	{
		DVD dvd1 = new DVD("Movie1", 100);
		DVD dvd2 = new DVD("Movie2", 120);
		
		DVDCollection col = new DVDCollection("My DVD collection");
		
		col.addElement(dvd1);
		col.addElement(dvd2);
		System.out.println(col.toString());
	}
	
//	@Test
//	public void shouldReturnAStringWithTheInformationAboutAllTheElements()
//	{
//		Book book1 = new Book("Book1", "Author1");
//		Book book2 = new Book("Book2", "Author2");
//		
//		BookCollection col = new BookCollection("Collection of Books");
//		col.addElement(book1);
//		col.addElement(book2);
//		
//		CD cd = new CD("CD1", 20);
//
//		Shelf shelf = new Shelf(20);
//		
//		shelf.add(col);
//		shelf.add(cd);
//		System.out.println(shelf.toString());
//	}
}
