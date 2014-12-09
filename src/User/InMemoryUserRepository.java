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
	public UserInterface getUserByLoginName(String loginName)
	{
		for (UserInterface user : super.getDatabaseElements())
			if (user.getLoginName().equals(loginName))
				return user;

		return null;
	}

}
