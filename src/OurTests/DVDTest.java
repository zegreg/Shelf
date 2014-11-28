package OurTests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import OurSuggestion.DVD;

public class DVDTest {

DVD dvd1;
			
	@Before
	public void constructDVD()		
	{
		dvd1 = new DVD("A dvd", 120);
	}
			
	@Test (expected = IllegalArgumentException.class)
	public void shouldReturnExceptionIfTheDurationOfDVDIsLessThanOne() 
	{
		new DVD("A dvd", 0);
	}
			
	@Test (expected = IllegalArgumentException.class)
	public void shouldReturnExceptionIfTitleIsNull()
	{
		new DVD(null, 120);
	}
			
	@Test
	public void shouldBeTheSameDVD()
	{
		assertEquals(0, dvd1.compareTo(new DVD("A dvd", 120)));
		assertEquals(0, dvd1.compareTo(dvd1));
	}
			
	@Test
	public void shouldNotBeTheSameDVD()
	{
		assertTrue(0 != dvd1.compareTo(new DVD("Other dvd", 10)));
	}
			
	@Test
	public void shouldBeEqual()
	{
		assertTrue(dvd1.equals(new DVD("A dvd", 120)));
		assertTrue(dvd1.equals(dvd1));
	}
			
	@Test
	public void shouldNotBeEqual()
	{
		assertFalse(dvd1.equals(new DVD("Other dvd", 100)));
	}
			
	@Test
	public void toStringVisualTest()
	{
		System.out.println(dvd1.toString());
	}

}
