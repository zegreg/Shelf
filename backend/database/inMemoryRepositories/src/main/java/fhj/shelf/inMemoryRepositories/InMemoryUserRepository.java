package fhj.shelf.inMemoryRepositories;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import fhj.shelf.repositories.AbstractUser;
import fhj.shelf.repositories.UserRepository;

/**
 * This class is the class of the elements in the repository of users. The
 * repository knows it's elements.
 *
 * @authors Hugo Leal, José Oliveira, Filipa Estiveira
 *
 */
public class InMemoryUserRepository extends InMemoryRepo<AbstractUser>
		implements UserRepository {

	private Map<String, AbstractUser> usersContainer;

	/**
	 * This is the constructor of the class, it defines the user as a treemap
	 * that receives String type of keys and the type of mapped values is the
	 * UserInterface.
	 *
	 */
	public InMemoryUserRepository() {
		usersContainer = new TreeMap<String, AbstractUser>();

	}

	/**
	 * This method is an override method of the base interface UserRepository,
	 * it allows to see how many users are there, or if the repository is empty.
	 *
	 */
	@Override
	public AbstractUser getUserName(String username) {
		if (usersContainer.isEmpty()) {
			throw new NullPointerException("UserRepository is empty");
		}

		return usersContainer.get(username);
	}

	/**
	 * This method is an override method of the base interface UserRepository,
	 * it checks the possibility of adding a new user, returning false if such
	 * isn't possible.
	 *
	 */
	@Override
	public boolean add(AbstractUser user) {

		if (user.getLoginName() == null) {
			throw new NullPointerException(
					"User can't be add because username is Null");
		}

		if (!(user == null || usersContainer.containsKey(user.getLoginName()))) {
			usersContainer.put(user.getLoginName(), user);
			return true;
		}
		return false;
	}

	/**
	 * This method is an override method of the base interface UserRepository,
	 * it checks the possibility of removing a existing user, returning false if
	 * such isn't possible.
	 *
	 */
	@Override
	public boolean remove(AbstractUser user) {

		if (user.getLoginName() == null) {
			throw new NullPointerException(
					"User can't be remove because username is Null");
		}

		if (user != null && usersContainer.containsKey(user.getLoginName())) {
			usersContainer.remove(user.getLoginName());
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
	public boolean validatePassword(String username, String password) {

		if (username == null || password == null) {
			return false;
		}

		AbstractUser user = usersContainer.get(username);

		if (user == null) {
			return false;
		}
		if (user.getLoginPassword().equals(password)) {
			return true;
		}
		return false;
	}

	/**
	 * These methods are override's methods of the base interface
	 * UserRepository, the first returns the size of a particular user, the
	 * second shows the contents of User Interface in String type.
	 */
	@Override
	public int getSize() {

		return usersContainer.size();
	}

	@Override
	public Map<String, AbstractUser> getUsers() {
		return usersContainer;
	}

	public String toString() {

		StringBuilder builder = new StringBuilder("USER CONTENTS\n\n\n");
		Iterator<Entry<String, AbstractUser>> iterator = usersContainer
				.entrySet().iterator();

		while (iterator.hasNext())
			builder.append(iterator.next().toString()).append("\n\n\n");

		return builder.toString();
	}

}

	

