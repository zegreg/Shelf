package afterSOLIDrevisionEHL.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import afterSOLIDrevisionEHL.model.CD;
import afterSOLIDrevisionEHL.model.CDCollection;

public class CDCollectionTest {

	CDCollection col;
	CD cd1;
	CD cd2;
	
	@Before
	public void constructCollection()
	{
		cd1 = new CD("CD1", 10);
		cd2 = new CD("CD2", 20);
		
		col = new CDCollection("My CD collection");
		
		col.addElement(cd1);
		col.addElement(cd2);
	}
	
	@Test
	public void shouldBeAvailable() {
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
		assertTrue(col.addElement(new CD("CD3", 15)));
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
		assertFalse(col.removeElement(new CD("CD3", 15)));
	}
	
	@Test
	public void shouldSayTheCollectionsAreNotEqual()
	{
		assertFalse(constructOtherCollection().equals(col));
	}
	
	@Test
	public void shouldSayTheCollectionsAreEqual()
	{
		CDCollection col2 = new CDCollection("My CD collection");
		
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
		CDCollection col2 = new CDCollection("My CD collection");
		
		col2.addElement(cd1);
		col2.addElement(cd2);
		
		assertTrue(col.compareTo(col) == 0);
		assertTrue(col2.compareTo(col) == 0);
	}
	
	private CDCollection constructOtherCollection()
	{
		CD cd3 = new CD("CD3", 15);
		CD cd4 = new CD("CD4", 16);
		
		CDCollection col2 = new CDCollection("other collection");
		col2.addElement(cd4);
		col2.addElement(cd3);
		
		return col2;
	}
	
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
	}
}
