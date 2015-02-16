package fhj.shelf.repositories;

/**
 * Class whose instance represent an system Administrator
 * 
 * @author Filipa Estiveira, Hugo Leal, José Oliveira
 *
 */
public class AdminLogin extends User {
	/**
	 * Admin's username
	 */
	private String username;
	
	/**
	 * Admin's password
	 */
	private String password;
	
	/**
	 * Admin's email
	 */
	@SuppressWarnings("unused")
	private String email;
	
	/**
	 * Admin's fullname
	 */
	@SuppressWarnings("unused")
	private String fullname;

	/**
	 * Constructor
	 * 
	 * @param username
	 * @param password
	 * @param email
	 * @param fullName
	 * @throws IllegalArgumentException
	 */
	public AdminLogin(String username, String password, String email,
			String fullName) throws IllegalArgumentException {
		super(username, password, email, fullName);

		this.username = username;
		this.password = password;
		this.email = email;
		this.fullname = fullName;
	}

	/**
	 * Method to be call by Login class
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public boolean loginAuthentication(String username, String password) {

		if (username.equals(this.username) && password.equals(this.password)) {
			return true;
		}
		return false;
	}
}
