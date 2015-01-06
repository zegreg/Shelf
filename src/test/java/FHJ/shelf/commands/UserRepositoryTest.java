package test.java.FHJ.shelf.commands;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import main.java.FHJ.shelf.model.User;
import main.java.FHJ.shelf.model.repos.InMemoryUserRepository;
import main.java.FHJ.shelf.model.repos.UserInterface;
import main.java.FHJ.shelf.model.repos.UserRepository;



public class UserRepositoryTest {
	private UserRepository userRepository = new InMemoryUserRepository();
	private User user1;
	private User user2;
	private User user3;
	private User user4;
	private User user5;
	private User user6;
	private User user7;

	@Before
	public void createUser()
	{
		user1 = new User("José", "6676", "j@mail.pt", "JGGO");
		user2 = new User("Hugo", "6677", "h@mail.pt", "HL");
		user3 = new User("Filipa", "8778", "f@mail.pt", "FE");
		user4 = new User ("Filipa", "8778", "f@mail.pt", "FE");
		user5 = new User ("Filipa", null, "f@mail.pt", "FE");
		user6 = new User (null, "333", "f@mail.pt", "FE");
		user7 = null;
	}
	
	@Test
	public void AvoidDoubleRegistrationInUserRepository () 
		{
		
		
		userRepository.add(user3);
		userRepository.add(user4);
		
		assertEquals(1, userRepository.getSize());
		}
		
	
	
	@Test
	public void AllowValidPassword () 
	{
	
		userRepository.add(user1);
		
		assertTrue(userRepository.validatePassword(user1.getLoginName(), "6676"));
		
		
	}
	
	
	
	
	@Test
	public void AvoidInvalidPassword () 
	{
	
		userRepository.add(user1);
		
		assertFalse(userRepository.validatePassword(user1.getLoginName(), "3333"));
		
		
	}
	
	
	@Test
	public void AvoidNullPassword () 
	{
	
		userRepository.add(user5);
		
		assertFalse (userRepository.validatePassword(user5.getLoginName(), user5.getLoginPassword()));
				
	}
	
	@Test
	public void AvoidNullUsername () throws NullPointerException
	{
		try {
			userRepository.add(user6);
			userRepository.validatePassword(user6.getLoginName(), user6.getLoginPassword());
		} catch (Exception e) {
			assertEquals ("User can't be add because username is Null", e.getMessage());
		}
		
				
	}
	
	
	@Test
	public void AvoidNullUser () throws NullPointerException
	{
	
		try {
			
			assertFalse(userRepository.add(user7));
		} catch (Exception e) {
			
		}
				
	}
	
	
	@Test
	public void shouldReturnNullPointerExceptionInEmptyRepository () throws NullPointerException
	{
		try {
			userRepository.getUserName(user1.getLoginName());
		} catch (Exception e) {
			assertEquals("UserRepository is empty", e.getMessage());
		}

	}

	
	
	@Test
	public void shouldReturnAUserbyGivenUsername () 
	{
	
		userRepository.add(user1);
		
		assertEquals(user1,userRepository.getUserName(user1.getLoginName()));
		
		
	}
	
	
	
	@Test
	public void shouldReturnInfoOfUser()
	{
		
		userRepository.add(user1);
		
		StringBuilder builder = new StringBuilder("USER CONTENTS\n\n\n")
		.append(user1.getLoginName()).append("=USER Content\n\n\n")
		        .append("\nLoginName : ")
				.append(user1.getLoginName())
				.append("\nLoginPassword : ").append(user1.getLoginPassword())
				.append("\nemail : ").append(this.user1.getEmail())
				.append("\nFullName : ").append(user1.getFullName()).append("\n\n\n\n");
		
	
		assertEquals(builder.toString(), userRepository.toString());
	}
	
	@Test
	public void shouldReturnAllUser()
	{
	
		userRepository.add(user1);
	
		assertEquals(1, userRepository.getSize());
	}
	
	
	@Test
	public void shouldReturnAMapUserInUserRepository()
	{
		Map<String, UserInterface> map;
		userRepository.add(user1);
	
		map = userRepository.getUsers();
				
		assertEquals(user1, map.get(user1.getLoginName()));
	}
	
	
	@Test
	public void shouldRemoveAUserbyUsername()
	{
	
		userRepository.add(user1);
	
		userRepository.remove(user1);
		
		assertEquals(0, userRepository.getSize());
	}
	
	
}