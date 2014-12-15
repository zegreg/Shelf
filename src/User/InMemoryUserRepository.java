package User;


import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import Database.InMemoryRepo;




/** 
 *This class is the class of the elements in the repository of users.
 * The repository knows it's elements.
 *
 * @authors Hugo Leal, José Oliveira, Filipa Estiveira
 *
 */
public class InMemoryUserRepository extends InMemoryRepo<UserInterface> implements UserRepository
{
	
	
	private Map<String, UserInterface> users;

	


	/**
	* This is the constructor of the class, it defines the user
	* as a treemap that receives String type of keys and the type
	* of mapped values is the UserInterface.
	*
	*/
	public InMemoryUserRepository()
	{
		users = new TreeMap<String, UserInterface>();

	}


	/**
	* This method is an override method of the base interface UserRepository,
	* it allows to see how many users are there, or if the repository is empty.
	*
	*/
	@Override
	public UserInterface getUserName(String username)
	{
		if (users.isEmpty()) {
			throw new NullPointerException("UserRepository is empty");
		}
		
		return users.get(username);
	}
	
	/**
	* This method is an override method of the base interface UserRepository,	
	* it checks the possibility of adding a new user, returning false if such
	* isn't possible.
	*
	*/	
	@Override
	public boolean add(User user) {
		
		if (!(user == null || users.containsKey(user.getLoginName()))) {
			users.put(user.getLoginName(), user);
			return true;
		}
		return false;
	}
		
	
	/**
	* This method is an override method of the base interface UserRepository,
	* it validates, or not, the user's password. 
	*
	*/
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
	

	/**
	* These methods are override's methods of the base interface UserRepository,
	* the first returns the size of a particular user, the second shows the contents  
	* of User Interface in String type.
	*/
	@Override
	public int getSize() {
		
		return users.size();
	}
	
	
	public String toString() {

		StringBuilder builder = new StringBuilder( "USER CONTENTS\n\n\n" );
		Iterator<Entry<String, UserInterface>> iterator =  users.entrySet().iterator();
				
		while( iterator.hasNext() )
			builder.append( iterator.next().toString() ).append( "\n\n\n" );

		return builder.toString();
	}





	  
  }

	

