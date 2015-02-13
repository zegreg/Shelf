package fhj.shelf.commandsDomain;

import java.util.concurrent.Callable;

import fhj.shelf.repositories.AbstractUser;
import fhj.shelf.repositories.UserRepository;



/**
 * Class whose instances represent the command that gets a shelf from the shelf
 * repository
 * 
 *@author Filipa Estiveira, Hugo Leal, José Oliveira
 */
public class GetOneUser implements Callable<AbstractUser> {

	/**
	 * Holds the user's repository
	 */
	private final UserRepository userRepository;

	/**
	 * User name 
	 */
	private String username;

	/**
	 * Creates a command instance with the given user's repository and user's name wanted
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
	 * @return the user with the given user name
	 */
	@Override
	public AbstractUser call() throws Exception {

		return userRepository.getUserName(username);
	}

}
