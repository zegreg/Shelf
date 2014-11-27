package tests;
import java.util.TreeSet;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import utils.Book;
import utils.BooksCollection;
import utils.CD;
import utils.CDsCollection;
import utils.Collection;
import utils.DVD;
import utils.DVDsCollection;
import utils.Element;
import utils.Shelf;

/** Test cases for {@link Collection} class */
public class CollectionTest {

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

	private Shelf shelf;

	@Test(expected = IllegalArgumentException.class)
	public void ShouldThrowAnIllegalArgumentExceptionWhenCreatingBookCollectionsWithNullTitle() {
		// Arrange
		Collection col = new BooksCollection(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void ShouldThrowAnIllegalArgumentExceptionWhenCreatingCDCollectionsWithNullTitle() {
		// Arrange
		Collection col = new CDsCollection(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void ShouldThrowAnIllegalArgumentExceptionWhenCreatingDVDCollectionsWithNullTitle() {
		// Arrange
		Collection col = new DVDsCollection(null);
	}

	// Collections not in a {@code Shelf}.
	@Before
	public void ShouldCreateCollectionsAndElements() {

		book1 = new Book("Daniel", "Gomes");
		book2 = new Book("Filipe", "Maia");
		book3 = new Book("Pedro", "Antunes");

		cd1 = new CD("Daniel", 3);
		cd2 = new CD("Filipe", 10);
		cd3 = new CD("Pedro", 8);

		dvd1 = new DVD("Daniel", 3);
		dvd2 = new DVD("Filipe", 10);
		dvd3 = new DVD("Pedro", 8);

		col1 = new BooksCollection("books");
		col2 = new CDsCollection("cds");
		col3 = new DVDsCollection("dvds");
	}

	@Test
	public void ShouldGetCorrectTitleOfEachCollection() {
		// Assert
		Assert.assertEquals(col1.getCollectionTitle(), "books");
		Assert.assertEquals(col2.getCollectionTitle(), "cds");
		Assert.assertEquals(col3.getCollectionTitle(), "dvds");
	}

	@Test
	public void ShouldGetCorrectElementsTypeOfEachCollection() {
		// Assert
		Assert.assertEquals(col1.getElementsType(), "Book");
		Assert.assertEquals(col2.getElementsType(), "CD");
		Assert.assertEquals(col3.getElementsType(), "DVD");
	}

	@Test
	public void shouldGetAvailableStatusAndShouldBeTrue() {
		// Assert
		Assert.assertTrue(col1.isAvailable());
		Assert.assertTrue(col2.isAvailable());
		Assert.assertTrue(col3.isAvailable());
	}

	@Test
	public void ShouldOnlyAddTheCorrectTypeOfElementsAndTheyCantBeNullOrBeAlreadyInTheCollection() {
		// Assert
		Assert.assertTrue(col1.addElement(book1));
		Assert.assertTrue(col1.addElement(book2));
		Assert.assertTrue(col1.addElement(book3));

		Assert.assertFalse(col1.addElement(book1));
		Assert.assertFalse(col1.addElement(cd1));
		Assert.assertFalse(col1.addElement(dvd1));
		Assert.assertFalse(col1.addElement(null));

		Assert.assertTrue(col2.addElement(cd1));
		Assert.assertTrue(col2.addElement(cd2));
		Assert.assertTrue(col2.addElement(cd3));

		Assert.assertFalse(col2.addElement(cd1));
		Assert.assertFalse(col2.addElement(book1));
		Assert.assertFalse(col2.addElement(dvd1));
		Assert.assertFalse(col2.addElement(null));

		Assert.assertTrue(col3.addElement(dvd1));
		Assert.assertTrue(col3.addElement(dvd2));
		Assert.assertTrue(col3.addElement(dvd3));

		Assert.assertFalse(col3.addElement(dvd1));
		Assert.assertFalse(col3.addElement(book1));
		Assert.assertFalse(col3.addElement(cd1));
		Assert.assertFalse(col3.addElement(null));
	}

	@Test
	public void shouldGetCorrectCollection() {
		// Act
		col1.addElement(book3);
		col1.addElement(book2);
		col1.addElement(book1);

		TreeSet<Element> elements = new TreeSet<Element>();

		elements.add(book1);
		elements.add(book3);
		elements.add(book2);

		// Assert
		Assert.assertEquals(col1.getCollection(), elements);
	}

	@Test
	public void shouldGetCorrectCollectionSize() {
		// Act
		col1.addElement(book3);
		col1.addElement(book2);
		col1.addElement(book1);

		// Assert
		Assert.assertEquals(col1.getCollection().size(), 3);
		Assert.assertEquals(col2.getCollection().size(), 0);
	}

	@Test
	public void ShouldOnlyRemoveElementsThatAreInTheCollectionAndAraNotNull() {
		// Act
		Assert.assertFalse(col1.removeElement(cd1));

		col1.addElement(book3);
		col1.addElement(book2);
		col1.addElement(book1);

		Assert.assertTrue(col1.removeElement(book1));
		Assert.assertEquals(col1.getCollection().size(), 2);
		Assert.assertFalse(col1.removeElement(book1));

		Assert.assertTrue(col1.removeElement(book2));
		Assert.assertEquals(col1.getCollection().size(), 1);

		Assert.assertFalse(col1.removeElement(null));

		Assert.assertTrue(col1.removeElement(book3));
		Assert.assertEquals(col1.getCollection().size(), 0);
	}

	@Test(expected = ClassCastException.class)
	public void ShouldThrowClassCastExceptionWhenWeTryToRemoveAnElementThatIsNotOfTheSameTypeAsTheCollectionWhenTheCollectionIsNotEmpty() {
		// Act
		col1.addElement(book3);
		col1.addElement(book2);
		col1.addElement(book1);

		col1.removeElement(cd1);
	}

	@Test
	public void shouldUpdateCollectionAvailability() {

		col1.updateAvailability();
		Assert.assertFalse(col1.isAvailable());

		col1.updateAvailability();
		Assert.assertTrue(col1.isAvailable());

		col1.updateAvailability();
		Assert.assertFalse(col1.isAvailable());
	}

	@Test
	public void shouldCompareCollectionsByTitleAndOrganizeThemInTheCorrectOrder() { 
		// Alphabetically, then by type, then by size
		
		// Arrange
		col = new BooksCollection("books");

		// Act
		col.addElement(book2);
		col.addElement(book1);
		col.addElement(book3);

		col1.addElement(book3);
		col1.addElement(book2);
		col1.addElement(book1);

		// Assert
		Assert.assertTrue(col1.equals(col));
		Assert.assertTrue(col1.compareTo(col2) < 0);
		Assert.assertTrue(col1.compareTo(col3) < 0);
		Assert.assertTrue(col2.compareTo(col1) > 0);
		Assert.assertTrue(col2.compareTo(col3) < 0);
		Assert.assertTrue(col3.compareTo(col1) > 0);
		Assert.assertTrue(col3.compareTo(col2) > 0);
	}

	// Collections in a {@code Shelf}.
	@Test
	public void ShouldOnlyAddElementsIfTheShelfIsNotFullAndUpdateTheShelfFreeSpace() {
		// Arrange
		shelf = new Shelf(2);

		// Act
		col1.addElement(book2);
		
		shelf.addCollection(col1);

		// Assert
		Assert.assertEquals(shelf.getFreeSpace(), 1);
		Assert.assertTrue(col1.addElement(book3));
		Assert.assertEquals(shelf.getFreeSpace(), 0);
		Assert.assertFalse(col1.addElement(book1));
		Assert.assertEquals(shelf.getFreeSpace(), 0);
	}

	@Test
	public void ShouldOnlyRemoveElementsIfTheCollectionIsAvailableAndUpdateTheShelfFreeSpace() {
		// Arrange
		shelf = new Shelf(5);

		// Act
		col1.addElement(book2);
		col1.addElement(book1);
		col1.addElement(book3);
		
		col2.addElement(cd1);
		col2.addElement(cd2);
		
		shelf.addCollection(col1);
		shelf.addCollection(col2);

		// Assert
		Assert.assertEquals(shelf.getFreeSpace(), 0);
		Assert.assertTrue(col1.removeElement(book1));
		Assert.assertEquals(shelf.getFreeSpace(), 1);
		col1.updateAvailability();
		Assert.assertFalse(col1.removeElement(book2));

		Assert.assertEquals(shelf.getFreeSpace(), 1);
		Assert.assertTrue(col2.removeElement(cd2));
		Assert.assertEquals(shelf.getFreeSpace(), 2);
		col2.updateAvailability();
		Assert.assertFalse(col2.removeElement(cd1));

		Assert.assertEquals(shelf.getFreeSpace(), 2);
		col1.updateAvailability();
		Assert.assertTrue(col1.removeElement(book2));
		Assert.assertEquals(shelf.getFreeSpace(), 3);
	}
	
	@Test
	public void ShouldRemoveTheCollectionFromTheShelfIfTheCollectionGetsEmpty() {
		// Arrange
		shelf = new Shelf(3);

		// Act		
		col2.addElement(cd1);
		col2.addElement(cd2);
		
		shelf.addCollection(col2);
		
		Assert.assertTrue(col2.removeElement(cd1));
		Assert.assertTrue(col2.removeElement(cd2));
		Assert.assertNull(col2.getShelf());
		Assert.assertFalse(shelf.getShelfCollections().contains(col2));
	}
}