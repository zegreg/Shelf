package test;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import User.InMemoryUserRepository;
import User.User;
import User.UserRepository;
/**
 * 
 *  Class that test UserPackage
 *
 *@authors Hugo Leal, José Oliveira, Filipa Estiveira
 
 */
public class Usertest {

	private UserRepository user = new InMemoryUserRepository();
	private User user1;
	private User user2;
	private User user3;
	private User user4;
	
	
	@Before
	public void createUser()
	{
		user1 = new User("José", "6676", "j@mail.pt", "JGGO");
		user2 = new User("Hugo", "6677", "h@mail.pt", "HL");
		user3 = new User("Filipa", "8778", "f@mail.pt", "FE");
		user4 = new User ("Filipa", "8778", "f@mail.pt", "FE");
	}
	
	@Test	
	public void getLoginName()
	{
		user1 .getLoginName();
		assertEquals("José", user1.getLoginName());
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
	public void shouldNotBeEqual() {
		assertFalse(user1.equals(user2));
	}
	
	
	@Test
	public void AvoidDoubleRegistration () 
		{
		
		
		user.add(user3);
		user.add(user4);
		
		assertEquals(1, user.getSize());
		}
		
	@Test
	public void AvoidInvalidPassword () throws NullPointerException
	{
	
		user.add(user1);
		
		assertFalse(user.validatePassword(user1.getLoginName(), "3333"));
		
		
	}
		
	

}

