package test.java.FHJ.shelf.commands;

import static org.junit.Assert.*;
import main.java.FHJ.shelf.model.User;
import main.java.FHJ.shelf.model.repos.InMemoryUserRepository;
import main.java.FHJ.shelf.model.repos.UserRepository;

import org.junit.Before;
import org.junit.Test;
/**
 * 
 *  Class that test UserPackage
 *
 *@authors Hugo Leal, José Oliveira, Filipa Estiveira
 
 */
public class Usertest {

	@SuppressWarnings("unused")
	private UserRepository userRepository = new InMemoryUserRepository();
	private User user1;
	private User user2;
	private User user3;
	private User user4;
	
	
	@Before
	public void createUser()
	{
		user1 = new User("Jos�", "6676", "j@mail.pt", "JGGO");
		user2 = new User("Hugo", "6677", "h@mail.pt", "HL");
		user3 = new User("Filipa", "8778", "f@mail.pt", "FE");
		user4 = new User ("Filipa", "8778", "f@mail.pt", "FE");
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
		
		assertFalse(user1.equals(user2));
		assertFalse(user1.equals(user3));
		assertFalse(user1.equals(user4));
	}
	
	
		
	

	
	
	
}

