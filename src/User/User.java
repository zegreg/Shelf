package User;

/**
 * @author 
 *
 */
public class User extends AbstractUser
{


	private final String email;
	private final String fullName;



	public User(String username, String password, String email, String fullName)
	{
		super(username, password);
		this.email = email;
		this.fullName = fullName;
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((email == null) ? 0 : email.hashCode());
		result = prime * result
				+ ((fullName == null) ? 0 : fullName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (fullName == null) {
			if (other.fullName != null)
				return false;
		} else if (!fullName.equals(other.fullName))
			return false;
		return true;
	}




	@Override
	public int compareTo(AbstractUser user) {
		if( user == null )
			throw new IllegalArgumentException( "This user cannot be null!" );

		int comparePassword = this.loginPassword.compareTo( user.getLoginPassword());

		if( comparePassword != 0 )
			return comparePassword;

		return getClass().toString().compareTo( user.getClass().toString());
	}




	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder("USER Content\n\n ");

		return builder.append("\nLoginName : ").append(this.loginName).
				append("\nLoginPassword : ").append(this.loginPassword).
				append("\nemail : ").append(this.email).
				append("\nFullName : ").append(this.fullName).
				append("\n").toString();

	}

}