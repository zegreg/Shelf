package fhj.shelf.commands;

import java.util.Map;

import fhj.shelf.commandsDomain.EditUser;
import fhj.shelf.database.StandAloneDatabase;
import fhj.shelf.factorys.CommandPatchFactoryWithParameters;
import fhj.shelf.repositories.UserRepository;


public class ModifyUser implements UIPatchCommand {

	public static class Factory implements CommandPatchFactoryWithParameters {

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
		public UIPatchCommand newInstance(Map<String, String> parameters) {
			return new ModifyUser(parameters);
		}
	}

	private Map<String, String> parameters;

	private UserRepository userRepo = StandAloneDatabase.getUserRepoInstance();

	public ModifyUser(Map<String, String> parameters) {
		this.parameters = parameters;
	}

	@Override
	public String execute() throws Exception {

		return new EditUser(userRepo, parameters.get("username"),
				parameters.get("oldPassword"), parameters.get("newPassword"))
				.call();

	}

}
