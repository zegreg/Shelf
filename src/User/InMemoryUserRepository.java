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
	public UserInterface getUserById(long id)
	{
		for (UserInterface user : super.getDatabaseElements())
			if (user.getId() == id)
				return user;

		return null;
	}

	

}
