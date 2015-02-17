package fhj.shelf.parser;

import static org.junit.Assert.*;

import java.util.concurrent.ExecutionException;

import org.junit.Before;
import org.junit.Test;

import fhj.shelf.CommandParser;
import fhj.shelf.commands.GetUser;
import fhj.shelf.commands.GetUsers;
import fhj.shelf.commands.PatchUsers;
import fhj.shelf.commands.PostUser;
import fhj.shelf.exceptions.CommandException;
import fhj.shelf.exceptions.CommandParserException;
import fhj.shelf.inMemoryRepositories.InMemoryUserRepository;
import fhj.shelf.repositories.User;
import fhj.shelf.repositories.UserRepository;

public class UsersCommandsExecutionParserTest {

	/**
	 * Create a CommandParser
	 */
	CommandParser parser = CommandParser.getInstance();

	/**
	 * Creates Repository
	 */
	UserRepository userrepository = new InMemoryUserRepository();

	@Before
	public void createAdmin() {
		User lima = new User("Lima", "SLB", "SLB", "SLB");
		userrepository.add(lima);
	}

	@Test
	public void shouldPostAUser() throws CommandParserException,
			IllegalArgumentException, CommandException, ExecutionException {
		parser.registerCommand("POST", new StringBuilder("/users/").toString(),
				new PostUser.Factory(userrepository));

		String result = parser
				.getCommand(
						"POST",
						"/users/",
						"loginName=Lima&loginPassword=SLB&username=Gaitan&password=SLB&email=OMAIOREMail&fullname=Gaitan")
				.execute();

		assertEquals("Gaitan added successfully to users database", result);

	}

	@Test
	public void shouldGetAllUsers() throws CommandParserException,
			IllegalArgumentException, CommandException, ExecutionException {
		parser.registerCommand("GET", new StringBuilder("/users").toString(),
				new GetUsers.Factory(userrepository));

		String result = parser.getCommand("GET", "/users").execute();

		StringBuilder expectedResult = new StringBuilder(" Userlist=null")
				.append("\n\n\n").append("Username=0=Lima").append("\n\n\n");

		assertEquals(expectedResult.toString(), result);

	}

	@Test
	public void shouldGetUserDetails() throws CommandParserException,
			IllegalArgumentException, CommandException, ExecutionException {
		parser.registerCommand(
				"GET",
				new StringBuilder("/users/{").append(GetUser.USERNAME)
						.append("}").toString(), new GetUser.Factory(
						userrepository));

		String result = parser.getCommand("GET", "/users/Lima").execute();

		StringBuilder expectedResult = new StringBuilder("User=null")
				.append("\n\n\n").append("email=SLB").append("\n\n\n")
				.append("fullname=SLB").append("\n\n\n").append("password=SLB")
				.append("\n\n\n").append("username=Lima").append("\n\n\n");

		assertEquals(expectedResult.toString(), result);

	}
	
	@Test
	public void shouldChangeUserPassword() throws CommandParserException,
			IllegalArgumentException, CommandException, ExecutionException {
		
		parser.registerCommand("PATCH",
				new StringBuilder("/users/{").append(PatchUsers.USERNAME)
				.append("}").toString(), new PatchUsers.Factory(
						userrepository));

		String result = parser.getCommand("PATCH", "/users/Lima", "loginName=Lima&loginPassword=SLB&oldPassword=SLB&newPassword=SLBOMAIOR").execute();

		System.out.println(result);
		
		String expectedResult = "The Password has been changed successfully";

		assertEquals(expectedResult, result);

	}
	
	

}
