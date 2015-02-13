package fhj.shelf.commands;

import java.util.Map;

import fhj.shelf.commands.UIPostCommand;
import fhj.shelf.commandsDomain.CreateUser;
import fhj.shelf.database.StandAloneDatabase;
import fhj.shelf.factorys.CommandPostFactoryWithParameters;
import fhj.shelf.repos.UserRepository;

public class NewUser implements UIPostCommand {

	public static class Factory implements CommandPostFactoryWithParameters {

		/**
		 * This is the constructor for the class above, it defines the factory
		 * 
		 * @param userRepo
		 *            is an instance of UserRepository
		 * @param shelfRepo
		 *            is an instance of ShelfRepository
		 */
		public Factory() {

		}

		/**
		 * This is an override method of the base class, it returns a new
		 * instance of PostShelf
		 */
		@Override
		public UIPostCommand newInstance(Map<String, String> parameters) {
			return new NewUser(parameters);
		}
	}

	private Map<String, String> parameters;

	private UserRepository userRepo = StandAloneDatabase.getUserRepoInstance();

	public NewUser(Map<String, String> parameters) {
		this.parameters = parameters;
	}

	@Override
	public String execute() throws Exception {

		return new CreateUser(userRepo, parameters.get("username"),
				parameters.get("password"), parameters.get("email"),
				parameters.get("fullname")).call();

	}

}
