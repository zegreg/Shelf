package fhj.shelf.commandsDomain;

import java.util.concurrent.Callable;

import fhj.shelf.repos.AbstractUser;
import fhj.shelf.repos.UserRepository;

/**
 * Class whose instances represent the command that edits an user's password,
 * that is in a user repository
 * 
 * @author Filipa Estiveira, Hugo Leal, José Oliveira
 */
public class EditUser implements Callable<String> {

	/**
	 * Holds the associated repository
	 */
	private final UserRepository userRepository;

	/**
	 * The user name
	 */
	private String username;

	/**
	 * User old password
	 */
	private String oldPassword;

	/**
	 * User new password
	 */
	private String newPassword;

	/**
	 * Creates a command instance with the given user repository and all the
	 * parameters necessary to change user's password
	 * 
	 * @param userRepo
	 * @param username
	 * @param oldPassword
	 * @param newPassword
	 */
	public EditUser(UserRepository userRepo, String username,
			String oldPassword, String newPassword) {
		this.userRepository = userRepo;
		this.username = username;
		this.oldPassword = oldPassword;
		this.newPassword = newPassword;
	}

	/**
	 * This method verifies the user password(oldpassword) and if it's valid,
	 * the password is changed to the newpassword.
	 * 
	 * @return a message with the information if the password was changed or not
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
