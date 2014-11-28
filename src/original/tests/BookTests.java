package original.tests;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import original.utils.Book;
import original.utils.CD;
import original.utils.Element;

/**
 * Test cases for class {@link Book}
 */


public class BookTests {

	private Element book1;
	private Element book2;
	private Book book3;

	@Before
	public void shouldCreateNewBooks() {
		// Arrange
		book1 = new Book("Daniel", "Gomes");
		book2 = new Book("Filipe", "Maia");
		book3 = new Book("Pedro", "Antunes");
	}

	@Test
	public void shouldGetBookTitles() {
		// Assert
		Assert.assertEquals(book1.getTitle(), "Daniel");
		Assert.assertEquals(book2.getTitle(), "Filipe");
		Assert.assertEquals(book3.getTitle(), "Pedro");
	}

	@Test
	public void shouldGetBookAuthor() {
		// Assert
		Assert.assertEquals(((Book) book1).getAuthor(), "Gomes");
		Assert.assertEquals(((Book) book2).getAuthor(), "Maia");
		Assert.assertEquals(book3.getAuthor(), "Antunes");
	}

	@Test
	public void shouldGetRequestedStatusAndShouldBeFalse() {
		// Assert
		Assert.assertFalse(book1.isRequested());
		Assert.assertFalse(book2.isRequested());
		Assert.assertFalse(book3.isRequested());
	}

	@Test
	public void shouldGetTypeAndShouldBeBook() {
		// Assert
		Assert.assertEquals(book1.getType(), "Book");
		Assert.assertEquals(book2.getType(), "Book");
		Assert.assertEquals(book3.getType(), "Book");
	}

	@Test
	public void shouldRequestBookWhenNotRequested() {

		// Assert
		Assert.assertTrue(book1.requestIt());
		Assert.assertFalse(book1.requestIt());
		Assert.assertFalse(book1.requestIt());

		Assert.assertTrue(book3.requestIt());
		Assert.assertFalse(book3.requestIt());
		Assert.assertFalse(book3.requestIt());
	}

	@Test
	public void shouldReturnBookWhenRequested() {
		// Assert
		Assert.assertFalse(book1.returnIt());
		Assert.assertTrue(book1.requestIt());
		Assert.assertTrue(book1.returnIt());
		Assert.assertFalse(book1.returnIt());

		Assert.assertFalse(book3.returnIt());
		Assert.assertTrue(book3.requestIt());
		Assert.assertTrue(book3.returnIt());
		Assert.assertFalse(book3.returnIt());
	}

	@Test
	public void shouldCompareBooksByAuthorAndPutThemInTheCorrectOrder() { // Alphabetically
		// Assert
		Assert.assertTrue(book1.compareTo(book1) == 0);
		Assert.assertTrue(book1.compareTo(book2) < 0);
		Assert.assertTrue(book1.compareTo(book3) > 0);
		Assert.assertTrue(book2.compareTo(book1) > 0);
		Assert.assertTrue(book2.compareTo(book3) > 0);
		Assert.assertTrue(book3.compareTo(book1) < 0);
		Assert.assertTrue(book3.compareTo(book2) < 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void ShouldThrowAnIllegalArgumentExceptionWhenNullTitle() {
		// Arrange
		Element book = new Book(null, "Gomes");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void ShouldThrowAnIllegalArgumentExceptionWhenNullAuthor() {
		// Arrange
		Element book = new Book("Daniel", null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void ShouldThrowAnIllegalArgumentExceptionWhenBothParametersAreNull() {
		// Arrange
		Element book = new Book("Daniel", null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void ShouldThrowAnIllegalArgumentExceptionWhenNullParameterIsGivenToCompareToMethod() {
		// Arrange
		book1.compareTo(null);
	}
	
	@Test(expected = ClassCastException.class)
	public void ShouldThrowClassCastExceptionWhenTheParameterGivenToTheCompareToMethodIsNotOfTypeBook() {
		// Arrange
		book1.compareTo( new CD("a", 3));
	}
}