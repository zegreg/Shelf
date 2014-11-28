package original.tests;
import java.util.TreeSet;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import original.utils.Book;
import original.utils.BooksCollection;
import original.utils.CD;
import original.utils.CDsCollection;
import original.utils.Collection;
import original.utils.DVD;
import original.utils.DVDsCollection;
import original.utils.Element;
import original.utils.Shelf;

/**
 * Test cases for {@link Shelf} class.
**/
public class ShelfTest {
	
	private Collection col;
	private Collection col1;
	private Collection col2;
	private Collection col3;

	private Element book1;
	private Element book2;
	private Book book3;

	private Element cd1;
	private Element cd2;
	private CD cd3;

	private Element dvd1;
	private Element dvd2;
	private DVD dvd3;

	private Shelf shelf1;
	private Shelf shelf2;
	

	@Test(expected = IllegalArgumentException.class)
	public void ShouldThrowAnIllegalArgumentExceptionWhenCreatingAShelfWithNoFreeSpace() {
		// Arrange
		shelf1 = new Shelf(0);
	}
	
	@Before
	public void ShouldCreateShelfCollectionsAndElementsAndAddElementsToCollections() {

		book1 = new Book("Daniel", "Gomes");
		book2 = new Book("Filipe", "Maia");
		book3 = new Book("Pedro", "Antunes");

		cd1 = new CD("Daniel", 3);
		cd2 = new CD("Filipe", 10);
		cd3 = new CD("Pedro", 8);

		dvd1 = new DVD("Daniel", 3);
		dvd2 = new DVD("Filipe", 10);
		dvd3 = new DVD("Pedro", 8);

		col = new BooksCollection("names");
		col1 = new BooksCollection("books");
		col2 = new CDsCollection("cds");
		col3 = new DVDsCollection("dvds");
		
		col1.addElement(book1);
		col1.addElement(book2);
		col1.addElement(book3);
		
		col2.addElement(cd1);
		col2.addElement(cd2);
		col2.addElement(cd3);
		
		col3.addElement(dvd1);
		col3.addElement(dvd2);
		col3.addElement(dvd3);
		
		shelf1 = new Shelf(8);
		shelf2 = new Shelf(5);
	}

	@Test
	public void ShouldGetCorrectShelfFreeSpace() {
		// Assert
		Assert.assertEquals(shelf1.getFreeSpace(), 8);
		Assert.assertEquals(shelf2.getFreeSpace(), 5);
	}
	
	@Test
	public void ShouldSetCorrectShelfFreeSpaceAndBeBiggerThanZeroAndNotSurpassTheCapacity() {
		// Act
		shelf1.setFreeSpace(5);
		shelf2.setFreeSpace(2);
		
		// Assert
		Assert.assertEquals(shelf1.getFreeSpace(), 5);
		Assert.assertEquals(shelf2.getFreeSpace(), 2);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void ShouldThrowAnIllegalArgumentExceptionWhenSettingTheFreeSpaceToBeBiggerThanTheShelfCapacity() {
		// Arrange
		shelf1.setFreeSpace(10);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void ShouldThrowAnIllegalArgumentExceptionWhenSettingTheFreeSpaceToBeLessThanZero() {
		// Arrange
		shelf1.setFreeSpace(-1);
	}
	
	@Test
	public void ShouldGetCorrectShelfCollections() {
		
		TreeSet<Collection> collections = new TreeSet<Collection>();
		
		collections.add(col1);
		collections.add(col2);
		
		shelf1.addCollection(col1);
		shelf1.addCollection(col2);
		
		Assert.assertEquals(shelf1.getShelfCollections(), collections);
	}
	
	@Test
	public void ShouldAddCollectionToShelfAndUpdateTheFreeSpaceCorrectlyIf_NotNull_TheShelfHasEnoughSpace_TheCollectionIsNotEmpty() {
		// Assert
		Assert.assertEquals(col1.getCollection().size(), 3);
		Assert.assertEquals(shelf1.getFreeSpace(), 8);
		Assert.assertTrue(shelf1.addCollection(col1));
		Assert.assertEquals(shelf1.getFreeSpace(), 5);
		
		Assert.assertEquals(col2.getCollection().size(), 3);
		Assert.assertTrue(shelf1.addCollection(col2));
		Assert.assertEquals(shelf1.getFreeSpace(), 2);
		
		Assert.assertEquals(col3.getCollection().size(), 3);
		Assert.assertFalse(shelf1.addCollection(col3));
		Assert.assertEquals(shelf1.getFreeSpace(), 2);
		
		Assert.assertEquals(col.getCollection().size(), 0);
		Assert.assertFalse(shelf1.addCollection(col));
		
		Assert.assertFalse(shelf1.addCollection(null));
	}
	
	@Test
	public void ShouldRemoveCollectionFromShelfAndUpdateTheFreeSpaceCorrectlyIf_NotNull_IsContainedInTheShelf_IsAvailable() {
		// Arrange
		col.addElement(book1);
		
		// Act
		shelf1.addCollection(col1);
		shelf1.addCollection(col2);
		shelf1.addCollection(col);
		
		
		// Assert
		Assert.assertEquals(col1.getCollection().size(), 3);
		Assert.assertEquals(shelf1.getFreeSpace(), 1);
		Assert.assertTrue(shelf1.removeCollection(col1));
		Assert.assertEquals(shelf1.getFreeSpace(), 4);
		
		Assert.assertFalse(shelf1.removeCollection(col1));
		Assert.assertEquals(shelf1.getFreeSpace(), 4);
		
		Assert.assertFalse(shelf1.removeCollection(col3));
		Assert.assertFalse(shelf1.removeCollection(null));
		Assert.assertEquals(shelf1.getFreeSpace(), 4);
		
		Assert.assertTrue(shelf1.getShelfCollections().contains(col));
		Assert.assertEquals(col.getCollection().size(), 1);
		col.removeElement(book1);
		Assert.assertEquals(col.getCollection().size(), 0);
		Assert.assertFalse(shelf1.getShelfCollections().contains(col));
		
		Assert.assertTrue(shelf1.getShelfCollections().contains(col2));
		col2.updateAvailability();
		Assert.assertFalse(shelf1.removeCollection(col2));
	}
	
	@Test
	public void ShouldRequestElementsBelongingToTheShelfThatAreNotAlreadyRequestedAndTheirCollectionIsAvailable() {
		// Act
		shelf1.addCollection(col1);
		shelf1.addCollection(col2);
		
		// Assert
		Assert.assertEquals(shelf1.requestElement(col1.getElementsType(), "Daniel"), book1);
		Assert.assertNull(shelf1.requestElement(col1.getElementsType(), "Daniel"));
		
		Assert.assertNull(shelf1.requestElement(col1.getElementsType(), "Filipe"));
		Assert.assertNull(shelf1.returnElement(col1.getElementsType(), "Pedro"));
		
		Assert.assertEquals(shelf1.requestElement(col2.getElementsType(), "Daniel"), cd1);
		Assert.assertNull(shelf1.requestElement(col2.getElementsType(), "Daniel"));
	}
	
	@Test
	public void ShouldReturnElementsBelongingToTheShelfThatWereNotAlreadyReturned() {
		// Act
		shelf1.addCollection(col1);
		shelf1.addCollection(col2);
		shelf1.requestElement(col1.getElementsType(), "Daniel");
		shelf1.requestElement(col2.getElementsType(), "Daniel");
				
		// Assert
		Assert.assertNull(shelf1.returnElement(col1.getElementsType(), "Filipe"));
		Assert.assertNull(shelf1.returnElement(col1.getElementsType(), "Pedro"));
		Assert.assertNull(shelf1.returnElement(col2.getElementsType(), "Filipe"));
				
		Assert.assertEquals(shelf1.returnElement(col1.getElementsType(), "Daniel"), book1);
		Assert.assertNull(shelf1.returnElement(col1.getElementsType(), "Daniel"));
		
		Assert.assertEquals(shelf1.returnElement(col2.getElementsType(), "Daniel"), cd1);
		Assert.assertNull(shelf1.returnElement(col2.getElementsType(), "Daniel"));
	}
	
	@Test
	public void ShouldFindRequestedElementsBelongingToTheShelf() {
		// Act
		shelf1.addCollection(col1);
		shelf1.addCollection(col2);
		
		shelf1.requestElement(col1.getElementsType(), "Daniel");
		shelf1.requestElement(col2.getElementsType(), "Daniel");
		
		// Assert
		Assert.assertNull(shelf1.findRequestedElement(col1.getElementsType(), "Filipe"));
		Assert.assertNull(shelf1.findRequestedElement(col2.getElementsType(), "Pedro"));
		
		Assert.assertEquals(shelf1.findRequestedElement(col1.getElementsType(), "Daniel"), book1);
		Assert.assertEquals(shelf1.findRequestedElement(col2.getElementsType(), "Daniel"), cd1);
	}
	
	@Test
	public void ShouldFindNotRequestedElementsBelongingToTheShelf() {
		// Act
		shelf1.addCollection(col1);
		shelf1.addCollection(col2);
				
		shelf1.requestElement(col1.getElementsType(), "Daniel");
		shelf1.requestElement(col2.getElementsType(), "Daniel");
				
		// Assert
		Assert.assertNull(shelf1.findNotRequestedElement(col1.getElementsType(), "Daniel"));
		Assert.assertNull(shelf1.findNotRequestedElement(col2.getElementsType(), "Daniel"));
				
		Assert.assertEquals(shelf1.findNotRequestedElement(col1.getElementsType(), "Filipe"), book2);
		Assert.assertEquals(shelf1.findNotRequestedElement(col2.getElementsType(), "Pedro"), cd3);		
	}
}