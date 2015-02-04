package fhj.shelf;

import org.slf4j.*;

import java.util.Scanner;
import java.util.concurrent.ExecutionException;

import fhj.shelf.commandParser.CommandParser;
import fhj.shelf.commandParser.DuplicateArgumentsException;
import fhj.shelf.commandParser.InvalidCommandArgumentsException;
import fhj.shelf.commandParser.InvalidRegisterException;
import fhj.shelf.commandParser.UnknownCommandException;
import fhj.shelf.commands.DeleteShelfElement;
import fhj.shelf.commands.DeleteShelfs;
import fhj.shelf.commands.Exit;
import fhj.shelf.commands.GetShelf;
import fhj.shelf.commands.GetShelfElement;
import fhj.shelf.commands.GetShelfElements;
import fhj.shelf.commands.GetShelfs;
import fhj.shelf.commands.GetUser;
import fhj.shelf.commands.GetUsers;
import fhj.shelf.commands.Option;
import fhj.shelf.commands.PatchElement;
import fhj.shelf.commands.PatchUsers;
import fhj.shelf.commands.PostElement;
import fhj.shelf.commands.PostShelf;
import fhj.shelf.commands.PostShelfCollectionElement;
import fhj.shelf.commands.PostUser;
import fhj.shelf.commands.exceptions.CommandException;
import fhj.shelf.output.StackMensage;
import fhj.shelf.repos.ElementsRepository;
import fhj.shelf.repos.InMemoryElementsRepository;
import fhj.shelf.repos.InMemoryShelfRepository;
import fhj.shelf.repos.InMemoryUserRepository;
import fhj.shelf.repos.ShelfRepository;
import fhj.shelf.repos.User;
import fhj.shelf.repos.UserRepository;

/**
 * ShelfManagerApp The goal of this app is to manage a database of shelfs that
 * could have books, cd's, dvd's, book collections, cd's collections and dvd's
 * collections. 
 * -A shelf is defined by the number of elements that can store
 * -A book is defined by a title and an author. 
 * -A cd is defined by a title and the track's number 
 * -A dvd is defined by a title and the duration 
 * -A collection only could have elements of the same type (books or cd's or dvd's)
 * 
 * 
 *
 * @author Filipa Estiveira, Hugo Leal, José Oliveira
 */
public class ShelfManagerApp {
	
	static Logger LOGGER = LoggerFactory.getLogger(ShelfManagerApp.class);

	/**
	 * Registers available commands using a CommandParser, and Repository of
	 * users, shelfs and elements
	 * 
	 * @param parser
	 *            interprets the String composed by the path({method} {path}
	 *            {parameter list}) to the command
	 * @param userRepo
	 *            repository of users
	 * @param shelfRepo
	 *            repository of shelfs
	 * @param elementsRepo
	 *            repository of elements
	 * @throws InvalidRegisterException
	 */
	public static void RegisterCommand(CommandParser parser,
			UserRepository userRepo, ShelfRepository shelfRepo,
			ElementsRepository elementsRepo) throws InvalidRegisterException {

		parser.registerCommand("POST", new StringBuilder("/users/").toString(),
				new PostUser.Factory(userRepo));

		parser.registerCommand("GET", new StringBuilder("/users").toString(),
				new GetUsers.Factory(userRepo));

		parser.registerCommand(
				"PATCH",
				new StringBuilder("/shelfs/{").append(PatchElement.SID)
						.append("}").append("/elements/{")
						.append(PatchElement.EID).append("}").toString(),
				new PatchElement.Factory(userRepo, shelfRepo, elementsRepo));

		parser.registerCommand(
				"GET",
				new StringBuilder("/users/{").append(GetUser.USERNAME)
						.append("}").toString(), new GetUser.Factory(userRepo));

		parser.registerCommand("POST",
				new StringBuilder("/shelfs/").toString(),
				new PostShelf.Factory(userRepo, shelfRepo));

		parser.registerCommand(
				"POST",
				new StringBuilder("/shelfs/{").append(PostElement.SID)
						.append("}").append("/elements/{")
						.append(PostElement.ELEMENT_TYPE).append("}")
						.toString(), new PostElement.Factory(userRepo,
						shelfRepo, elementsRepo));

		parser.registerCommand(
				"POST",
				new StringBuilder("/shelfs/{")
						.append(PostShelfCollectionElement.SID).append("}")
						.append("/elements/{")
						.append(PostShelfCollectionElement.ELEMENT_TYPE)
						.append("}/{").append(PostShelfCollectionElement.EID)
						.append("}").toString(),
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

		parser.registerCommand("GET",
				new StringBuilder("/shelfs/{").append(GetShelf.SID).append("}")
						.append("/details").toString(), new GetShelf.Factory(
						shelfRepo));

		parser.registerCommand("GET", new StringBuilder("/shelfs/").toString(),
				new GetShelfs.Factory(shelfRepo));

		parser.registerCommand(
				"DELETE",
				new StringBuilder("/shelfs/{").append(DeleteShelfs.SID)
						.append("}").toString(), new DeleteShelfs.Factory(
						userRepo, shelfRepo));

		parser.registerCommand("DELETE",
				new StringBuilder("/shelfs/{").append(DeleteShelfElement.SID)
						.append("}/elements/{").append(DeleteShelfElement.EID)
						.append("}").toString(),
				new DeleteShelfElement.Factory(userRepo, shelfRepo,
						elementsRepo));

		parser.registerCommand("PATCH",
				new StringBuilder("/users/{").append(PatchUsers.USERNAME)
						.append("}").toString(), new PatchUsers.Factory(
						userRepo));

		parser.registerCommand(
				"PATCH",
				new StringBuilder("/shelfs/{").append(PatchElement.SID)
						.append("}/elements/{").append(PatchElement.EID)
						.append("}").toString(), new PatchElement.Factory(
						userRepo, shelfRepo, elementsRepo));

		parser.registerCommand("OPTION", new StringBuilder("/").toString(),
				new Option.Factory(userRepo));

		parser.registerCommand("EXIT", new StringBuilder("/").toString(),
				new Exit.Factory(userRepo));
	}

	/**
	 * This method uses registerCommand to register all Commands, instantiates a
	 * command parser, and repository of shelfs, elements and users. The
	 * commands are call in this method by an user input
	 */
	public void run() {
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);

		StackMensage  stackMensage = new StackMensage(10);
		CommandParser parser = new CommandParser();
		ShelfRepository shelfRepo = new InMemoryShelfRepository();
		ElementsRepository elementsRepo = new InMemoryElementsRepository();
		UserRepository userRepo = new InMemoryUserRepository();

		try {
			RegisterCommand(parser, userRepo, shelfRepo, elementsRepo);
		} catch (InvalidRegisterException e) {
			System.out.println("Invalid Register Command!");
		}

		System.out.println(welcomeMessage());

		User admin = new User("Lima", "SLB", "OMAIOREMail", "Lima");
		userRepo.add(admin);
					
   
	
		do {
			String kbd = input.nextLine();

			try {
				parser.getCommand(kbd.split(" ")).execute(stackMensage);
				
			} catch (CommandException e) {
				LOGGER.error("Command Exception", e);
			} catch (UnknownCommandException e) {
				LOGGER.error("Unknow Command Exception", e);
			} catch (DuplicateArgumentsException e) {
				LOGGER.error("Duplicate Argument Exception", e);
			} catch (InvalidCommandArgumentsException e) {
				LOGGER.error("Invalid Command Argument Exception", e);
			} catch (IllegalArgumentException e) {
				LOGGER.error("Illegal Argument Exception", e);
			} catch (ExecutionException e) {
				LOGGER.error("Execution Exception", e);
			}

		} while (true);

	}

	/**
	 * This is the main method which makes use of run method
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		ShelfManagerApp app = new ShelfManagerApp();
		app.run();
	}

	/**
	 * This method builds a String with a welcome message to the app
	 * 
	 * @return String with welcome message
	 */
	public static String welcomeMessage() {

		StringBuilder msg = new StringBuilder(
				"***************************************")
				.append("\n***Welcome to ShelfManagerApp of FHJ***")
				.append("\n 	(New user type OPTION /)")
				.append("\n        (To end app type EXIT /)")
				.append("\n***************************************");

		String finalMsg = msg.toString();

		return finalMsg;
	}
}
