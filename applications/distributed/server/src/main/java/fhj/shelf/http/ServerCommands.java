package fhj.shelf.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fhj.shelf.CommandParser;
import fhj.shelf.commands.DeleteShelfElement;
import fhj.shelf.commands.DeleteShelfs;
import fhj.shelf.commands.GetShelf;
import fhj.shelf.commands.GetShelfElement;
import fhj.shelf.commands.GetShelfElements;
import fhj.shelf.commands.GetShelfs;
import fhj.shelf.commands.GetUser;
import fhj.shelf.commands.GetUsers;
import fhj.shelf.commands.PatchElement;
import fhj.shelf.commands.PatchUsers;
import fhj.shelf.commands.PostElement;
import fhj.shelf.commands.PostShelf;
import fhj.shelf.commands.PostShelfCollectionElement;
import fhj.shelf.commands.PostUser;
import fhj.shelf.exceptions.InvalidRegisterException;
import fhj.shelf.inMemoryRepositories.ElementsRepository;
import fhj.shelf.inMemoryRepositories.ShelfRepository;
import fhj.shelf.repositories.User;
import fhj.shelf.repositories.UserRepository;

public class ServerCommands {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(ServerCommands.class);
	
	public void registerServerCommands(CommandParser parser,
			ShelfRepository shelfRepo, ElementsRepository elementsRepo,
			UserRepository userRepo) {

		try {
			parser.registerCommand("POST",
					new StringBuilder("/users/").toString(),
					new PostUser.Factory(userRepo));

			parser.registerCommand("GET",
					new StringBuilder("/users").toString(),
					new GetUsers.Factory(userRepo));

			parser.registerCommand("PATCH", new StringBuilder("/shelfs/{")
					.append(PatchElement.SID).append("}").append("/elements/{")
					.append(PatchElement.EID).append("}").toString(),
					new PatchElement.Factory(userRepo, shelfRepo, elementsRepo));

			parser.registerCommand("GET",
					new StringBuilder("/users/{").append(GetUser.USERNAME)
							.append("}").toString(), new GetUser.Factory(
							userRepo));

			parser.registerCommand("POST",
					new StringBuilder("/shelfs/").toString(),
					new PostShelf.Factory(userRepo, shelfRepo));

			parser.registerCommand("POST", new StringBuilder("/shelfs/{")
					.append(PostElement.SID).append("}").append("/elements/{")
					.append(PostElement.ELEMENT_TYPE).append("}").toString(),
					new PostElement.Factory(userRepo, shelfRepo, elementsRepo));

			parser.registerCommand(
					"POST",
					new StringBuilder("/shelfs/{")
							.append(PostShelfCollectionElement.SID).append("}")
							.append("/elements/{")
							.append(PostShelfCollectionElement.ELEMENT_TYPE)
							.append("}/{")
							.append(PostShelfCollectionElement.EID).append("}")
							.toString(),
					new PostShelfCollectionElement.Factory(userRepo, shelfRepo,
							elementsRepo));

			parser.registerCommand("GET",
					new StringBuilder("/shelfs/{").append(GetShelfElements.SID)
							.append("}").append("/elements").toString(),
					new GetShelfElements.Factory(shelfRepo));

			parser.registerCommand(
					"GET",
					new StringBuilder("/shelfs/{").append(GetShelfElement.SID)
							.append("}").append("/elements/{")
							.append(GetShelfElement.EID).append("}").toString(),
					new GetShelfElement.Factory(shelfRepo, elementsRepo));

			parser.registerCommand("GET", new StringBuilder("/shelfs/{")
					.append(GetShelf.SID).append("}").append("/details")
					.toString(), new GetShelf.Factory(shelfRepo));

			parser.registerCommand("GET",
					new StringBuilder("/shelfs/").toString(),
					new GetShelfs.Factory(shelfRepo));

			parser.registerCommand("DELETE", new StringBuilder("/shelfs/{")
					.append(DeleteShelfs.SID).append("}").toString(),
					new DeleteShelfs.Factory(userRepo, shelfRepo));

			parser.registerCommand("DELETE", new StringBuilder("/shelfs/{")
					.append(DeleteShelfElement.SID).append("}/elements/{")
					.append(DeleteShelfElement.EID).append("}").toString(),
					new DeleteShelfElement.Factory(userRepo, shelfRepo,
							elementsRepo));

			parser.registerCommand("PATCH", new StringBuilder("/users/{")
					.append(PatchUsers.USERNAME).append("}").toString(),
					new PatchUsers.Factory(userRepo));

			parser.registerCommand("PATCH",
					new StringBuilder("/shelfs/{").append(PatchElement.SID)
							.append("}/elements/{").append(PatchElement.EID)
							.append("}").toString(), new PatchElement.Factory(
							userRepo, shelfRepo, elementsRepo));
			
			User admin = new User("Lima", "SLB", "OMAIOREMail", "Lima");
			userRepo.add(admin);

		} catch (InvalidRegisterException e) {
			LOGGER.error("Invalid Register Command!: ", e);

		}
	}

}
