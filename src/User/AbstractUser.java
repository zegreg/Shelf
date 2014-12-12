package User;

public abstract class AbstractUser implements UserInterface, Comparable<AbstractUser>{
	
	protected final String loginName;
	protected final String loginPassword;
	
	public AbstractUser(String loginName, String loginPassword){
		this.loginName = loginName;
		this.loginPassword = loginPassword;
	}
	
	@Override
	public String getLoginName() {
		return loginName;
	}
	@Override
	public String getLoginPassword() {
		return loginPassword;
	}
	
	
	public int compareTo( AbstractUser user ) {
		
		if( user == null )
			throw new IllegalArgumentException( "This user cannot be null!" );
		
		int compareUserName = this.loginName.compareTo( user.loginName);
		
		if( compareUserName != 0 )
			return compareUserName;
		
		return getClass().toString().compareTo( user.getClass().toString());
	}
	
	
	@Override
	public abstract String toString();

    
}
