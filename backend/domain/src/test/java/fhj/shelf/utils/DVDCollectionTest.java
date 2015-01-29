package fhj.shelf.utils;

import static org.junit.Assert.*;
import fhj.shelf.utils.DVD;
import fhj.shelf.utils.DVDCollection;

import org.junit.Before;
import org.junit.Test;

public class DVDCollectionTest {

	DVDCollection col;
	DVD dvd1;
	DVD dvd2;
	
	@Before
	public void constructCollection()
	{
		dvd1 = new DVD("Movie1", 100);
		dvd2 = new DVD("Movie2", 120);
		
		col = new DVDCollection("My DVD collection");
		
		col.addElement(dvd1);
		col.addElement(dvd2);
	}
	
	@Test
	public void newInstancesShouldNotBeAvailable() {
		assertFalse(col.isAvailable());
	}
	
	@Test
	public void shouldAddNewElement()
	{
		assertTrue(col.addElement(new DVD("Movie3", 150)));
	}
	
	@Test
	public void shouldNotAddRepeatedElement()
	{
		assertFalse(col.addElement(dvd1));
	}
	
	@Test
	public void shouldRemoveAnElement()
	{
		assertTrue(col.removeElement(dvd1));
	}
	
	@Test
	public void shouldNotRemoveAnElementThatIsNotThere()
	{
		assertFalse(col.removeElement(new DVD("Movie3", 150)));
	}
	
	@Test
	public void shouldSayTheCollectionsAreNotEqual()
	{
		assertFalse(constructOtherCollection().equals(col));
	}
	
	@Test
	public void shouldSayTheCollectionsAreEqual()
	{
		DVDCollection col2 = new DVDCollection("My DVD collection");
		
		col2.addElement(dvd1);
		col2.addElement(dvd2);
		
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
		DVDCollection col2 = new DVDCollection("My DVD collection");
		
		col2.addElement(dvd1);
		col2.addElement(dvd2);
		
		assertTrue(col.compareTo(col) == 0);
		assertTrue(col2.compareTo(col) == 0);
	}
	
	private DVDCollection constructOtherCollection()
	{
		DVD dvd3 = new DVD("Movie3", 150);
		DVD dvd4 = new DVD("Movie4", 160);
		
		DVDCollection col2 = new DVDCollection("other collection");
		col2.addElement(dvd4);
		col2.addElement(dvd3);
		
		return col2;
	}
	
	@Test
	public void  shouldBeSortedByAscendingOrderDVDduration()
	{
		
		StringBuilder expected = new StringBuilder( "Collection: " )
				.append( col.getTitle() ).append("\n{\n")
							.append("\nDVD Title: ").append(dvd1.getTitle()).append("\n") 
								.append("DVD Duration: ").append(dvd1.getDuration()).append("\n")
									.append("Is Available: ").append(dvd1.isAvailable()).append("\n\n")
										.append("DVD Title: ").append(dvd2.getTitle()).append("\n")
											.append("DVD Duration: ").append(dvd2.getDuration()).append("\n")
												.append("Is Available: ").append(dvd2.isAvailable()).append("\n")
													.append("\n}");
		
		assertEquals(expected.toString(),col.toString());
	}
}
