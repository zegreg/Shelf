package afterSOLIDrevisionEHL.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import afterSOLIDrevisionEHL.model.CD;

public class CDTest2 {

CD cd1;
	
	@Before
	public void constructCD()
	{
		cd1 = new CD("A cd", 12);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void shouldReturnExceptionIfNumberOfTracksAreLessThanOne() {
		new CD("A cd", 0);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void shouldReturnExceptionIfNameIsNull() {
		new CD(null, 12);
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
	}
	
	@Test
	public void shouldBeEqual()
	{
		assertTrue(cd1.equals(new CD("A cd", 12)));
		assertTrue(cd1.equals(cd1));
	}
	
	@Test
	public void shouldNotBeEqual()
	{
		assertFalse(cd1.equals(new CD("Other cd", 10)));
	}
	
	@Test
	public void toStringVisualTest()
	{
		System.out.println(cd1.toString());
	}

}
