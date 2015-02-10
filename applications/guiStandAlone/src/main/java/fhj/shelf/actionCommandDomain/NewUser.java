package fhj.shelf.actionCommandDomain;

import java.util.Map;

import fhj.shelf.commandsDomain.CreateUser;
import fhj.shelf.repos.UserRepository;

public class NewUser {

	private Map<String, String> parameters;

	private UserRepository userRepo = StandAloneDatabase.getUserRepoInstance();

	public NewUser(Map<String, String> parameters) {
		this.parameters = parameters;
	}

	public String execute() throws Exception {

		return new CreateUser(userRepo, parameters.get("username"),
				parameters.get("password"), parameters.get("email"),
				parameters.get("fullname")).call();

	}

}
