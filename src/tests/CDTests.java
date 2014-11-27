package tests;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import utils.Book;
import utils.CD;
import utils.Element;

/** Test cases for class {@link CD} */
public class CDTests {

	private Element cd1;
	private Element cd2;
	private CD cd3;

	@Before
	public void shouldCreateNewCDs() {
		// Arrange
		cd1 = new CD("Daniel", 3);
		cd2 = new CD("Filipe", 10);
		cd3 = new CD("Pedro", 8);
	}

	@Test
	public void shouldGetCDTitle() {
		// Assert
		Assert.assertEquals(cd1.getTitle(), "Daniel");
		Assert.assertEquals(cd2.getTitle(), "Filipe");
		Assert.assertEquals(cd3.getTitle(), "Pedro");
	}

	@Test
	public void shouldGetCDTracksNumber() {
		// Assert
		Assert.assertEquals(((CD) cd1).getTracksNumber(), 3);
		Assert.assertEquals(((CD) cd2).getTracksNumber(), 10);
		Assert.assertEquals(cd3.getTracksNumber(), 8);
	}

	@Test
	public void shouldGetRequestedStatusAndShouldBeFalse() {
		// Assert
		Assert.assertFalse(cd1.isRequested());
		Assert.assertFalse(cd2.isRequested());
		Assert.assertFalse(cd3.isRequested());
	}

	@Test
	public void shouldGetTypeAndShouldBeCD() {
		// Assert
		Assert.assertEquals(cd1.getType(), "CD");
		Assert.assertEquals(cd2.getType(), "CD");
		Assert.assertEquals(cd3.getType(), "CD");
	}

	@Test
	public void shouldRequestCDWhenNotRequested() {

		// Assert
		Assert.assertTrue(cd1.requestIt());
		Assert.assertFalse(cd1.requestIt());
		Assert.assertFalse(cd1.requestIt());

		Assert.assertTrue(cd3.requestIt());
		Assert.assertFalse(cd3.requestIt());
		Assert.assertFalse(cd3.requestIt());
	}

	@Test
	public void shouldReturnCDWhenRequested() {
		// Assert
		Assert.assertFalse(cd1.returnIt());
		Assert.assertTrue(cd1.requestIt());
		Assert.assertTrue(cd1.returnIt());
		Assert.assertFalse(cd1.returnIt());

		Assert.assertFalse(cd3.returnIt());
		Assert.assertTrue(cd3.requestIt());
		Assert.assertTrue(cd3.returnIt());
		Assert.assertFalse(cd3.returnIt());
	}

	@Test
	public void shouldCompareCDByTracksNumberAndPutThemInTheCorrectOrder() { // Descending order of tracksNumber.
		// Assert
		Assert.assertTrue(cd1.compareTo(cd1) == 0);
		Assert.assertTrue(cd1.compareTo(cd2) < 0);
		Assert.assertTrue(cd1.compareTo(cd3) < 0);
		Assert.assertTrue(cd2.compareTo(cd1) > 0);
		Assert.assertTrue(cd2.compareTo(cd3) > 0);
		Assert.assertTrue(cd3.compareTo(cd1) > 0);
		Assert.assertTrue(cd3.compareTo(cd2) < 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void ShouldThrowAnIllegalArgumentExceptionWhenNullTitle() {
		// Arrange
		Element cd = new CD(null, 2);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void ShouldThrowAnIllegalArgumentExceptionWhenTracksNumbersIsLessThanOne() {
		// Arrange
		Element cd = new CD("Daniel", 0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void ShouldThrowAnIllegalArgumentExceptionWhenTitleNullAndTracksNumbersIsLessThanOne() {
		// Arrange
		Element cd = new CD(null, -1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void ShouldThrowAnIllegalArgumentExceptionWhenNullParameterIsGivenToCompareToMethod() {
		// Arrange
		cd1.compareTo(null);
	}
	
	@Test(expected = ClassCastException.class)
	public void ShouldThrowClassCastExceptionWhenTheParameterGivenToTheCompareToMethodIsNotOfTypeCD() {
		// Arrange
		cd1.compareTo( new Book("a", "b"));
	}
}