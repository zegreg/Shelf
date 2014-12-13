package User;

import Database.DatabaseElements;
import Database.InMemoryRepo;
import Database.Repository;


/**
 * @author 
 *
 */
public class InMemoryUserRepository extends InMemoryRepo<UserInterface> implements
		UserRepository
{

	@Override
	public UserInterface getUserName(String username)
	{
		for (UserInterface user : super.getDatabaseElements())
			if (user.getLoginName().equals(username))
				return user;

		return null;
	}
	
	
	
	

	  
  }

	

