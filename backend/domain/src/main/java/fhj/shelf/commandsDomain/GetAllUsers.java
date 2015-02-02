package fhj.shelf.commandsDomain;

import java.util.Map;
import java.util.concurrent.Callable;

import fhj.shelf.repos.AbstractUser;
import fhj.shelf.repos.UserRepository;

/**
 * Class whose instances represent the command that gets all users in the
 * repository.
 */
public class GetAllUsers implements Callable<Map<String, AbstractUser>> {

	/**
	 * Holds the associated repository
	 */
	private final UserRepository userRepository;

	/**
	 * Creates a command instance with the given user's repository
	 * 
	 * @param repository
	 *            The associated product repository
	 */
	public GetAllUsers(UserRepository userRepo) {
		this.userRepository = userRepo;
	}

	/**
	 * 
	 * @return a map with all the users in the user's repository
	 */
	@Override
	public Map<String, AbstractUser> call() throws Exception  {
		return userRepository.getUsers();
	}
}