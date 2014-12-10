package User;

/**
 * @author 
 *
 */
public class User extends AbstractUser
{

	private final String loginName;
	private String loginPassword;
	private String email;
	private final String fullName;
	
	

	public User(String username, String password, String email, String fullName)
	{
		this.loginName = username;
		this.loginPassword = password;
		this.email = email;
		this.fullName = fullName;
	}

	/**
	 * @return the username
	 */
	public String getLoginName()
	{
		return loginName;
	}

	/**
	 * @return the password
	 */
	public String getLoginPassword()
	{
		return loginPassword;
	}

	/**
	 * @return the email
	 */
	public String getEmail()
	{
		return email;
	}

	/**
	 * @return the fullName
	 */
	public String getFullName()
	{
		return fullName;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder("USER Content ");
		
		return builder.append("\nLoginName : ").append(this.loginName).
				append("\nLoginPassword : ").append(this.loginPassword).
				append("\nemail : ").append(this.email).
				append("\nFullName : ").append(this.fullName).toString();
				
	}

}
