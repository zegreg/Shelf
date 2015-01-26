package fhj.shelf.commandsDomain;


import java.util.concurrent.Callable;

import fhj.shelf.utils.repos.AbstractUser;
import fhj.shelf.utils.repos.UserRepository;

public class EditUser implements Callable<String> {

	/**
	 * Holds the associated repository
	 */
	private final UserRepository userRepository;
	
	private String username;

	private String oldPassword;

	private String newPassword;

	/**
	 * Creates a command instance with the given repository
	 * 
	 * @param repository
	 *            The associated product repository
	 */
	public EditUser(UserRepository userRepo, String username,
			String oldPassword, String newPassword) {
		this.userRepository = userRepo;
		this.username = username;
		this.oldPassword = oldPassword;
		this.newPassword = newPassword;
	}

	/**
	 * 
	 * @return the repository with all the users
	 * @throws Exception
	 */
	@Override
	public String call() throws Exception {
		AbstractUser user = userRepository.getUserName(username);

		if (user.getLoginPassword().equals(oldPassword)) {
			user.setLoginPassword(newPassword);
			return "The Password has been changed successfully";
		}

		return "Unable to change password, try again";
	}
}
