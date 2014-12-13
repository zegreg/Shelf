package User;


import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import Database.InMemoryRepo;



/**
 * @author 
 *
 */
public class InMemoryUserRepository extends InMemoryRepo<UserInterface> implements UserRepository
{
	
	
	private Map<String, UserInterface> users;

	
	public InMemoryUserRepository()
	{
		users = new TreeMap<String, UserInterface>();

	}


	@Override
	public UserInterface getUserName(String username)
	{
		return users.get(username);
	}
	
	
	@Override
	public boolean add(User user) {
		
		if (!(user == null || users.containsKey(user.getLoginName()))) {
			users.put(user.getLoginName(), user);
			return true;
		}
		return false;
	}
		
	
	@Override
	public boolean validatePassword(String username, String password) 
	{


		if(username == null || password == null ){
			return false;
		}
		UserInterface user = users.get(username);

		if(user== null){
			return false;
		}
		if(user.getLoginPassword().equals(password)){
			return true;
		}
		return false;
	}

	
	
	public String toString() {

		StringBuilder builder = new StringBuilder( "USER CONTENTS\n\n\n" );
		Iterator<Entry<String, UserInterface>> iterator =  users.entrySet().iterator();
				
		while( iterator.hasNext() )
			builder.append( iterator.next().toString() ).append( "\n\n\n" );

		return builder.toString();
	}



	  
  }

	

