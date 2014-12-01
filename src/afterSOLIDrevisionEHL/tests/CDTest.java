package afterSOLIDrevisionEHL.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import afterSOLIDrevisionEHL.model.Book;
import afterSOLIDrevisionEHL.model.CD;
import afterSOLIDrevisionEHL.model.DVD;

public class CDTest {

CD cd1;
CD cd2;
CD cd3;
DVD cd4;
CD cd5;

	@Before
	public void constructCD()
	{
		cd1 = new CD("A cd", 12);
		cd2 = new CD("A cd", 10);
		cd3 = new CD("Big cd", 100);
		cd4 = new DVD("A cd", 120);
		cd5 = new CD("A cd", 12);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void shouldReturnExceptionIfNumberOfTracksAreLessThanOne() {
		new CD("A cd", 0);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void shouldReturnExceptionIfNameIsNull() {
		new CD(null, 12);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void shouldReturnExceptionIfCDIsNull() {
		cd1.compareTo(null);
	}
	
	@Test
	public void shouldBeTheSameCD()
	{
		assertEquals(0, cd1.compareTo(new CD("A cd", 12)));
		assertEquals(0, cd1.compareTo(cd1));
	}
	
	@Test
	public void shouldNotBeTheSameCD()
	{
		assertTrue(0 != cd1.compareTo(new CD("Other cd", 1)));
		assertTrue(0 >= cd1.compareTo(cd2));
		assertTrue(0 != cd1.compareTo(cd3));
	}
	
	@Test
	public void shouldNotBeOfTheSameType()
	{
		assertTrue(0 <= cd4.compareTo(cd1));
	}
	
	@Test
	public void shouldHaveTheSameHashCode()
	{
		CD cdRef = cd1;

		assertTrue(cdRef.hashCode() == cd1.hashCode());
		assertTrue(cd1.hashCode() == cd5.hashCode());
	}

	@Test
	public void shouldReturnObjectHashCode()
	{	
		int hashCodeExpected = 31*1 + cd1.getTitle().hashCode();;
		hashCodeExpected = 31*hashCodeExpected + cd1.getTracksNumber();

		assertTrue(hashCodeExpected == cd1.hashCode());
	}
	
	@Test
	public void shouldBeEqual()
	{
		assertTrue(cd1.equals(new CD("A cd", 12)));
		assertTrue(cd1.equals(cd5));
	}
	
	@Test
	public void shouldNotBeEqual()
	{
		assertFalse(cd1.equals(new CD("Other cd", 10)));
		assertFalse(cd1.equals(cd2));
		assertFalse(cd1.equals(cd4));
	}
	
	@Test
	public void shouldReturnTheTracksNumber()
	{
		assertTrue(12 == cd1.getTracksNumber());
	}

	@Test
	public void  shouldReturnCDInformation()
	{
		StringBuilder expected = new StringBuilder("\nCD Title: ").append(cd1.getTitle())
												.append("\nNumber of Tracks: ").append(cd1.getTracksNumber())
													.append("\nIs Available: ").append(cd1.isAvailable());
									
		assertEquals(expected.toString(),cd1.toString());
	}


}
