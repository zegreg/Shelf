package fhj.shelf.commandsDomain;

import java.util.concurrent.Callable;

import fhj.shelf.utils.repos.AbstractUser;
import fhj.shelf.utils.repos.UserRepository;

public class GetOneUser implements Callable<AbstractUser> {

	/**
	 * Holds the associated repository
	 */
	private final UserRepository userRepository;

	private String username;

	/**
	 * Creates a command instance with the given repository
	 * 
	 * @param repository
	 *            The associated product repository
	 */
	public GetOneUser(UserRepository userRepo, String username) {
		this.userRepository = userRepo;
		this.username = username;
	}

	/**
	 * 
	 * @return the repository with all the users
	 * @throws Exception
	 */
	@Override
	public AbstractUser call() throws Exception {

		return userRepository.getUserName(username);
	}

}
