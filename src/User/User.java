package User;

import java.util.Collection;

import afterSOLIDrevisionEHL.model.Element;



/**
 * @author 
 *
 */
public class User implements UserInterface
{


	
	private final String email;
	private final String fullName;
	private final String username;
	private final String password;




	public User(String username, String password, String email, String fullName)
	{
		this.username = username;
		this.password = password;
		this.email = email;
		this.fullName = fullName;
	}


 
	@Override
	public String getLoginName() {
		// TODO Auto-generated method stub
		return username;
	}
	
	
	@Override
	public String getLoginPassword() {
		// TODO Auto-generated method stub
		return password;
	}
	/**
	 * @return the email
	 */
	@Override
	public String getEmail()
	{
		return email;
	}

	/**
	 * @return the fullName
	 */
	@Override
	public String getFullName()
	{
		return fullName;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result
				+ ((fullName == null) ? 0 : fullName.hashCode());
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		result = prime * result
				+ ((username == null) ? 0 : username.hashCode());
		return result;
	}

	
	@Override
	public boolean equals(UserInterface user) {
		if (this == user) {
			return true;
		}
		if (user == null) {
			return false;
		}
		
		if( !getClass().equals( user.getClass() ) )
			return false;
		
		if( !this.username.equals( ((User)user).username ) )
			return false;
		if( !this.password.equals( ((User)user).password ) )
			return false;
		if( !this.fullName.equals( ((User)user).fullName ) )
			return false;
		if( !this.email.equals( ((User)user).email ) )
			return false;
		
		return true;
	}

	
	
	
	
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder("USER Content\n\n ");

		return builder.append("\nLoginName : ").append(this.username).
				append("\nLoginPassword : ").append(this.password).
				append("\nemail : ").append(this.email).
				append("\nFullName : ").append(this.fullName).
				append("\n").toString();

	}




}