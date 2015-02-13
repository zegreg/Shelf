package fhj.shelf.model;

import static org.junit.Assert.*;
import model.fhj.shelf.model.CD;
import model.fhj.shelf.model.DVD;

import org.junit.Before;
import org.junit.Test;

public class CDTest {

CD cd1;
CD cd2;
CD cd3;
DVD cd4;
CD cd5;

	@Before
	public void constructCD()
	{
		cd1 = new CD(1,"A cd", 12);
		cd2 = new CD(2,"A cd", 10);
		cd3 = new CD(3,"Big cd", 100);
		cd4 = new DVD(4,"A cd", 120);
		cd5 = new CD(5,"A cd", 12);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void shouldReturnExceptionIfNumberOfTracksAreLessThanOne() {
		new CD(1,"A cd", 0);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void shouldReturnExceptionIfNameIsNull() {
		new CD(1,null, 12);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void shouldReturnExceptionIfCDIsNull() {
		cd1.compareTo(null);
	}
	
	@Test
	public void shouldBeTheSameCD()
	{
		assertEquals(0, cd1.compareTo(new CD(1,"A cd", 12)));
		assertEquals(0, cd1.compareTo(cd1));
	}
	
	@Test
	public void shouldNotBeTheSameCD()
	{
		assertTrue(0 != cd1.compareTo(new CD(1,"Other cd", 1)));
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
		assertTrue(cd1.equals(new CD(1,"A cd", 12)));
		assertTrue(cd1.equals(cd5));
	}
	
	@Test
	public void shouldNotBeEqual()
	{
		assertFalse(cd1.equals(new CD(2,"Other cd", 10)));
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
