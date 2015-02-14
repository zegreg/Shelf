package fhj.shelf.inMemoryRepositories;

import static org.junit.Assert.*;
import fhj.shelf.inMemoryRepositories.InMemoryUserRepository;
import fhj.shelf.repositories.User;
import fhj.shelf.repositories.UserRepository;

import org.junit.Before;
import org.junit.Test;

public class UserTest {
	@SuppressWarnings("unused")
	private UserRepository userRepository = new InMemoryUserRepository();
	private User user1;
	private User user2;
	private User user3;
	private User user4;
	private User user5;
	private User user6;
	
	
	@Before
	public void createUser()
	{
		user1 = new User("Jos�", "6676", "j@mail.pt", "JGGO");
		user2 = new User("Hugo", "6677", "h@mail.pt", "HL");
		user3 = new User("Filipa", "8778", "f@mail.pt", "FE");
		user4 = new User ("Filipa", "8778", "f@mail.pt", "FE");
		user5 = new User("Jos�", "6676", "j@mail.pt", "JGGO");
		user6= null;
	}
	
	@Test	
	public void getLoginName()
	{
		user1 .getLoginName();
		assertEquals("Jos�", user1.getLoginName());
	}
	
	
	@Test
	public void getLoginPassword()
	{
		user1.getLoginPassword();
		assertEquals("6676", user1.getLoginPassword());
		
	}
	
	@Test
	public void getEmail()
	{
		user2.getEmail();
		assertEquals("h@mail.pt", user2.getEmail());
		
	}
	
	@Test
	public void getFullName()
	{	
		user3.getFullName();
		assertEquals("FE", user3.getFullName());
	}
	
	
	
	@Test
	public void shouldSetLoginName () 
	{
		
		user1.setLoginName("Leonor");
		
		assertEquals("Leonor", user1.getLoginName());
			
	}
	
	
	
	@Test
	public void shouldSetLoginPassword() 
	{
		
		user1.setLoginPassword("sempreAlerta");
		
		assertEquals("sempreAlerta", user1.getLoginPassword());
			
	}
	
	@Test
	public void shouldNotBeEqual() {
		
		user2 = new User("Jos�", "333", "j@mail.pt", "JGGO");
		user3 = new User("Jos�", "6676", "j@pt", "JGGO");
		user4 = new User("Jos�", "6676", "j@mail.pt", "JG");
		user5 = new User("Paulo", "6676", "j@mail.pt", "JG");
		
		assertFalse(user1.equals(user2));
		assertNotEquals(user1.hashCode(), user2.hashCode());
		assertFalse(user1.equals(user3));
		assertNotEquals(user1.hashCode(), user3.hashCode());
		assertFalse(user1.equals(user4));
		assertNotEquals(user1.hashCode(), user4.hashCode());
		assertFalse(user1.equals(user5));
		assertNotEquals(user1.hashCode(), user5.hashCode());
	}
	
	@Test
	public void shoulBeEqual() {
		
		assertTrue(user1.equals(user5));
		assertEquals(user1.hashCode(), user5.hashCode());
	}
	
	
	@Test
	public void shoulNotBeNull() {
		
		assertFalse(user1.equals(user6));
		
	}
	
	
	@Test
	public void shouldReturnInfoOfUser()
	{
				
		StringBuilder builder = new StringBuilder("USER Content\n\n\n")
		        .append("\nLoginName : ")
				.append(user1.getLoginName())
				.append("\nLoginPassword : ").append(user1.getLoginPassword())
				.append("\nemail : ").append(this.user1.getEmail())
				.append("\nFullName : ").append(user1.getFullName()).append("\n");
		
	
		assertEquals(builder.toString(), user1.toString());
	}
	
}
