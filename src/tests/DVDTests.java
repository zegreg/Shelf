package tests;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import utils.Book;
import utils.DVD;
import utils.Element;

/** Test cases for class {@link CD} */
public class DVDTests {

	private Element dvd1;
	private Element dvd2;
	private DVD dvd3;

	@Before
	public void shouldCreateNewCDs() {
		// Arrange
		dvd1 = new DVD("Daniel", 3);
		dvd2 = new DVD("Filipe", 10);
		dvd3 = new DVD("Pedro", 8);
	}

	@Test
	public void shouldGetDVDTitle() {
		// Assert
		Assert.assertEquals(dvd1.getTitle(), "Daniel");
		Assert.assertEquals(dvd2.getTitle(), "Filipe");
		Assert.assertEquals(dvd3.getTitle(), "Pedro");
	}

	@Test
	public void shouldGetDVDDuration() {
		// Assert
		Assert.assertEquals(((DVD) dvd1).getDuration(), 3);
		Assert.assertEquals(((DVD) dvd2).getDuration(), 10);
		Assert.assertEquals(dvd3.getDuration(), 8);
	}

	@Test
	public void shouldGetRequestedStatusAndShouldBeFalse() {
		// Assert
		Assert.assertFalse(dvd1.isRequested());
		Assert.assertFalse(dvd2.isRequested());
		Assert.assertFalse(dvd3.isRequested());
	}

	@Test
	public void shouldGetTypeAndShouldBeDVD() {
		// Assert
		Assert.assertEquals(dvd1.getType(), "DVD");
		Assert.assertEquals(dvd2.getType(), "DVD");
		Assert.assertEquals(dvd3.getType(), "DVD");
	}

	@Test
	public void shouldRequestDVDWhenNotRequested() {

		// Assert
		Assert.assertTrue(dvd1.requestIt());
		Assert.assertFalse(dvd1.requestIt());
		Assert.assertFalse(dvd1.requestIt());

		Assert.assertTrue(dvd3.requestIt());
		Assert.assertFalse(dvd3.requestIt());
		Assert.assertFalse(dvd3.requestIt());
	}

	@Test
	public void shouldReturnDVDWhenRequested() {
		// Assert
		Assert.assertFalse(dvd1.returnIt());
		Assert.assertTrue(dvd1.requestIt());
		Assert.assertTrue(dvd1.returnIt());
		Assert.assertFalse(dvd1.returnIt());

		Assert.assertFalse(dvd3.returnIt());
		Assert.assertTrue(dvd3.requestIt());
		Assert.assertTrue(dvd3.returnIt());
		Assert.assertFalse(dvd3.returnIt());
	}

	@Test
	public void shouldCompareDVDBydurationAndPutThemInTheCorrectOrder() { // Ascending order of duration.
		// Assert
		Assert.assertTrue(dvd1.compareTo(dvd1) == 0);
		Assert.assertTrue(dvd1.compareTo(dvd2) < 0);
		Assert.assertTrue(dvd1.compareTo(dvd3) < 0);
		Assert.assertTrue(dvd2.compareTo(dvd1) > 0);
		Assert.assertTrue(dvd2.compareTo(dvd3) > 0);
		Assert.assertTrue(dvd3.compareTo(dvd1) > 0);
		Assert.assertTrue(dvd3.compareTo(dvd2) < 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void ShouldThrowAnIllegalArgumentExceptionWhenNullTitle() {
		// Arrange
		Element dvd = new DVD(null, 2);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void ShouldThrowAnIllegalArgumentExceptionWhenDurationIsLessThanOne() {
		// Arrange
		Element dvd = new DVD("Daniel", 0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void ShouldThrowAnIllegalArgumentExceptionWhenTitleNullAndDurationIsLessThanOne() {
		// Arrange
		Element DVD = new DVD(null, -1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void ShouldThrowAnIllegalArgumentExceptionWhenNullParameterIsGivenToCompareToMethod() {
		// Arrange
		dvd1.compareTo(null);
	}
	
	@Test(expected = ClassCastException.class)
	public void ShouldThrowClassCastExceptionWhenTheParameterGivenToTheCompareToMethodIsNotOfTypeDVD() {
		// Arrange
		dvd1.compareTo( new Book("a", "b"));
	}
}