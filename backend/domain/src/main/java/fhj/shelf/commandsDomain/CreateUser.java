package fhj.shelf.commandsDomain;

import java.util.concurrent.Callable;

import fhj.shelf.repos.User;
import fhj.shelf.repos.UserRepository;

/**
 * Class whose instances represent the command that creates an user and adds to
 * an user repository
 * 
 * @author Filipa Estiveira, Hugo Leal, José Oliveira
 */
public class CreateUser implements Callable<String> {

	/**
	 * Holds the associated repository
	 */
	private final UserRepository userRepository;

	/**
	 * String with user's username
	 */
	private String username;

	/**
	 * String with user's password
	 */
	private String password;

	/**
	 * String with user's email
	 */
	private String email;

	/**
	 * String with user's full name
	 */
	private String fullname;

	/**
	 * Creates a command instance with the given user repository and the
	 * mandatory parameters to create an user
	 * 
	 * @param repository
	 *            The associated product repository
	 */
	public CreateUser(UserRepository userRepo, String username,
			String password, String email, String fullname) {
		this.userRepository = userRepo;
		this.username = username;
		this.password = password;
		this.email = email;
		this.fullname = fullname;
	}

	/**
	 * This method creates a user, adds the user to an user repository and
	 * returns a String with the information if the insertion in the repository
	 * was successful or not
	 * 
	 * @return a string with information about the success of the insertion of
	 *         an user in an user repository
	 * @throws Exception
	 */
	@Override
	public String call() throws Exception {

		User user = new User(username, password, email, fullname);

		if (userRepository.add(user)) {
			return username + " added successfully to users database";
		}

		return "unable to add " + username + " to database";

	}
}
