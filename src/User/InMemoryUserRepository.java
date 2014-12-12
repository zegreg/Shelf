package User;

import Database.InMemoryRepo;


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
