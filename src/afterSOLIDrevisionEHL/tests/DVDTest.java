package afterSOLIDrevisionEHL.tests;


import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import afterSOLIDrevisionEHL.model.Book;
import afterSOLIDrevisionEHL.model.DVD;


public class DVDTest
{
	
	DVD dvd1;
	DVD dvd2;
	DVD dvd3;
	Book dvd4;
	DVD dvd5;
	
	
	@Before
	public void constructDVD() {
		dvd1 = new DVD( "A dvd", 120 );
		dvd2 = new DVD( "A dvd", 100 );
		dvd3 = new DVD( "Big DVD", 1000 );
		dvd4 = new Book( "A dvd", "Sony" );
		dvd5 = new DVD( "A dvd", 120 );
	}
	
	@Test( expected = IllegalArgumentException.class )
	public void shouldReturnExceptionIfTheDurationOfDVDIsLessThanOne() {
		new DVD( "A dvd", 0 );
	}
	
	@Test( expected = IllegalArgumentException.class )
	public void shouldReturnExceptionIfTitleIsNull() {
		new DVD( null, 120 );
	}
	
	@Test( expected = IllegalArgumentException.class )
	public void shouldReturnExceptionIfBookIsNull() {
		dvd1.compareTo( null );
	}
	
	@Test
	public void shouldBeTheSameDVD() {
		assertEquals( 0, dvd1.compareTo( new DVD( "A dvd", 120 ) ) );
		assertEquals( 0, dvd1.compareTo( dvd1 ) );
	}
	
	@Test
	public void shouldNotBeTheSameDVD() {
		assertTrue( 0 != dvd1.compareTo( new DVD( "Other dvd", 10 ) ) );
		assertTrue( 0 <= dvd1.compareTo( dvd2 ) );
		assertTrue( 0 != dvd1.compareTo( dvd3 ) );
	}
	
	@Test
	public void shouldNotBeOfTheSameType() {
		assertTrue( 0 >= dvd4.compareTo( dvd1 ) );
	}
	
	@Test
	public void shouldHaveTheSameHashCode() {
		DVD dvdRef = dvd1;
		
		assertTrue( dvdRef.hashCode() == dvd1.hashCode() );
		assertTrue( dvd1.hashCode() == dvd5.hashCode() );
	}
	
	@Test
	public void shouldReturnObjectHashCode() {
		int hashCodeExpected = 31 * 1 + dvd1.getTitle().hashCode();;
		hashCodeExpected = 31 * hashCodeExpected + dvd1.getDuration();
		
		assertTrue( hashCodeExpected == dvd1.hashCode() );
	}
	
	@Test
	public void shouldBeEqual() {
		assertTrue( dvd1.equals( new DVD( "A dvd", 120 ) ) );
		assertTrue( dvd1.equals( dvd5 ) );
	}
	
	@Test
	public void shouldNotBeEqual() {
		assertFalse( dvd1.equals( new DVD( "Other dvd", 100 ) ) );
		assertFalse( dvd1.equals( dvd2 ) );
		assertFalse( dvd1.equals( dvd4 ) );
	}
	
	@Test
	public void shouldReturnTheDVDDuration() {
		assertTrue( 120 == dvd1.getDuration() );
	}
	
	@Test
	public void shouldReturnDVDInformation() {
		StringBuilder expected = new StringBuilder( "\nDVD Title: " )
				.append( dvd1.getTitle() ).append( "\nDVD Duration: " )
				.append( dvd1.getDuration() ).append( "\nIs Available: " )
				.append( dvd1.isAvailable() );
		
		assertEquals( expected.toString(), dvd1.toString() );
	}
	
}
