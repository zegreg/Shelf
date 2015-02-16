package fhj.shelf.model;

import static org.junit.Assert.*;
import model.fhj.shelf.model.CD;
import model.fhj.shelf.model.CDCollection;

import org.junit.Before;
import org.junit.Test;

public class CDCollectionTest {

	CDCollection col;
	CD cd1;
	CD cd2;
	
	@Before
	public void constructCollection()
	{
		cd1 = new CD(1,"CD1", 10);
		cd2 = new CD(2,"CD2", 20);
		
		col = new CDCollection(3,"My CD collection");
		
		col.addElement(cd1);
		col.addElement(cd2);
	}
	
	@Test
	public void newInstancesShouldBeNotAvailable() {
		assertFalse(col.isAvailable());
	}
	
	@Test
	public void shouldAddNewElement()
	{
		assertTrue(col.addElement(new CD(4,"CD3", 15)));
	}
	
	@Test
	public void shouldNotAddRepeatedElement()
	{
		assertFalse(col.addElement(cd1));
	}
	
	@Test
	public void shouldRemoveAnElement()
	{
		assertTrue(col.removeElement(cd1));
	}
	
	@Test
	public void shouldNotRemoveAnElementThatIsNotThere()
	{
		assertFalse(col.removeElement(new CD(1,"CD3", 15)));
	}
	
	@Test
	public void shouldSayTheCollectionsAreNotEqual()
	{
		assertFalse(constructOtherCollection().equals(col));
	}
	
	@Test
	public void shouldSayTheCollectionsAreEqual()
	{
		CDCollection col2 = new CDCollection(1,"My CD collection");
		
		col2.addElement(cd1);
		col2.addElement(cd2);
		
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
		CDCollection col2 = new CDCollection(1,"My CD collection");
		
		col2.addElement(cd1);
		col2.addElement(cd2);
		
		assertTrue(col.compareTo(col) == 0);
		assertTrue(col2.compareTo(col) == 0);
	}
	
	private CDCollection constructOtherCollection()
	{
		CD cd3 = new CD(1,"CD3", 15);
		CD cd4 = new CD(2,"CD4", 16);
		
		CDCollection col2 = new CDCollection(3,"other collection");
		col2.addElement(cd4);
		col2.addElement(cd3);
		
		return col2;
	}
	/*
	@Test
	public void  shouldBeSortedByDescendingOrderTheNumberOfTracks()
	{
		
		StringBuilder expected = new StringBuilder( "Collection: " )
				.append( col.getTitle() ).append("\n{\n")
							.append("\nCD Title: ").append(cd2.getTitle())
								.append("\nNumber of Tracks: ").append(cd2.getTracksNumber())
									.append("\nIs Available: ").append(cd2.isAvailable()).append("\n")
										.append("\nCD Title: ").append(cd1.getTitle())
											.append("\nNumber of Tracks: ").append(cd1.getTracksNumber())
												.append("\nIs Available: ").append(cd1.isAvailable()).append("\n")
													.append("\n}");
		
		assertEquals(expected.toString(),col.toString());
	}*/
	
	@Test
	public void shouldAddANewCollection()
	{
		CDCollection cdCol2 = makeAnotherCollection();
		
		assertTrue(col.addCollection(cdCol2));
	}
	
	@Test
	public void shouldNotAddNullCollection()
	{
		assertFalse(col.addCollection(null));
	}
	
	@Test
	public void shouldNotAddACollectionThatIsInAnotherCollection()
	{
		CDCollection cdCol2 = makeAnotherCollection();
		CDCollection cdCol3 = new CDCollection(4,"Yet another collection");
		
		cdCol2.addCollection(cdCol3);
		assertFalse(col.addCollection(cdCol3));
	}
	
	@Test
	public void shouldNotRemoveNullElements()
	{
		assertFalse(col.removeElement(null));
	}
	
	@Test
	public void shouldNotRemoveNullCollections()
	{
		assertFalse(col.removeCollection(null));
	}
	
	@Test
	public void shouldRemoveTheCollection()
	{
		CDCollection cdCol2 = makeAnotherCollection();
		
		col.addCollection(cdCol2);
		assertTrue(col.removeCollection(cdCol2));
	}
	
	@Test
	public void shouldNotRemoveACollectionThatIsNotThere()
	{
		CDCollection cdCol2 = new CDCollection(5,"Another collection");
		assertFalse(col.removeCollection(cdCol2));
	}
	
	@Test
	public void shouldReturnTheCorrectSize()
	{
		assertEquals(2, col.getSize());
	}
	
	@Test
	public void shouldReturnTheCorrectSizeOfAComposedCollection()
	{
		CDCollection cdCol2 = makeAnotherCollection();
		col.addCollection(cdCol2);
		
		assertEquals(4, col.getSize());
	}
	
	@Test
	public void shouldReturnTheCorrectSizeOfACollectionWithNoElements()
	{
		CDCollection cdCol2 = new CDCollection(1,"other");
		assertEquals(0, cdCol2.getSize());
	}
	
	@Test
	public void shouldContainTheElement()
	{
		assertEquals(cd1,col.isOrContains(cd1));
	}
	
	@Test
	public void shouldNotContainAnElementThatIsNotThere()
	{
		CD cd3 =  new CD(1,"CD3", 30);
		assertNull(col.isOrContains(cd3));
	}
	
	@Test
	public void shouldContainAnElementWithTheSameTitle()
	{
		CD cd3 = new CD(2,"CD1", 10);
		assertEquals(cd3,col.isOrContainsElementsWithTheSameTypeAndTitleAs(cd3));
	}
	
	@Test
	public void shouldNotContainAnElementWithTheSameTitle()
	{
		CD cd3 = new CD(3,"CD3", 30);
		assertNull(col.isOrContainsElementsWithTheSameTypeAndTitleAs(cd3));
	}
	
	@Test
	public void shouldNotBeAvaliable()
	{
		assertFalse(col.isAvailable());
	}
	
	@Test
	public void shouldBeAvaliable()
	{
		col.setAvailability(true);
		assertTrue(col.isAvailable());
	}
	
	@Test
	public void shouldNotBeRequested()
	{
		assertFalse(col.isRequested());
	}
	
	@Test
	public void shouldBeRequested()
	{
		col.isRequested(true);
		assertTrue(col.isRequested());
	}
	
	private CDCollection makeAnotherCollection()
	{
		CD cd3 =  new CD(1,"CD3", 30);
		CD cd4 =  new CD(2,"CD4", 40);
		
		CDCollection cdCol2 = new CDCollection(3,"Another Collection");
		cdCol2.addElement(cd3);
		cdCol2.addElement(cd4);
		
		return cdCol2;
	}
}
