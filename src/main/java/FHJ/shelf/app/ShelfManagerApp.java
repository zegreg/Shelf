package main.java.FHJ.shelf.app;

import java.util.Scanner;

import main.java.FHJ.shelf.commandParser.CommandParser;
import main.java.FHJ.shelf.commandParser.DuplicateArgumentsException;
import main.java.FHJ.shelf.commandParser.InvalidCommandArgumentsException;
import main.java.FHJ.shelf.commandParser.InvalidRegisterException;
import main.java.FHJ.shelf.commandParser.UnknownCommandException;
import main.java.FHJ.shelf.commands.GetShelf;
import main.java.FHJ.shelf.commands.GetShelfElement;
import main.java.FHJ.shelf.commands.GetShelfElements;
import main.java.FHJ.shelf.commands.GetShelfs;
import main.java.FHJ.shelf.commands.GetUser;
import main.java.FHJ.shelf.commands.GetUsers;
import main.java.FHJ.shelf.commands.PostElement;
import main.java.FHJ.shelf.commands.PostShelf;
import main.java.FHJ.shelf.commands.PostShelfCollectionElement;
import main.java.FHJ.shelf.commands.PostUser;
import main.java.FHJ.shelf.commands.exceptions.CommandException;
import main.java.FHJ.shelf.model.User;
import main.java.FHJ.shelf.model.repos.ElementsRepository;
import main.java.FHJ.shelf.model.repos.InMemoryElementsRepository;
import main.java.FHJ.shelf.model.repos.InMemoryShelfRepository;
import main.java.FHJ.shelf.model.repos.InMemoryUserRepository;
import main.java.FHJ.shelf.model.repos.ShelfRepository;
import main.java.FHJ.shelf.model.repos.UserRepository;

public class ShelfManagerApp {
	
	/**
	 * Registers available commands by using a parser to 
	 * @param parser
	 * @param userRepo
	 * @param shelfRepo
	 * @param elementsRepo
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
	}

	public void run() {
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);

		CommandParser parser = new CommandParser();
		ShelfRepository shelfRepo = new InMemoryShelfRepository();
		ElementsRepository elementsRepo = new InMemoryElementsRepository();
		UserRepository userRepo = new InMemoryUserRepository();

		try {
			RegisterCommand(parser, userRepo, shelfRepo, elementsRepo);
		} catch (InvalidRegisterException e) {
			System.out.println("Invalid Register Command!");
		}

		System.out.println("***************************************"
				+ "\n***Welcome to ShelfManagerApp of FHJ***"
				+ "\n (if you're a new user type userguide)"
				+ "\n (if you want to exit type Exit)"
				+ "\n***************************************");

		User admin = new User("Lima", "SLB", "OMAIOREMail", "Lima");
		if(userRepo.add(admin))
		{
			userRepo.insert(admin);
		}
		

		boolean run = true;
		do {
			String kbd = input.nextLine();

			switch (kbd) {
			case "Exit":
				System.out.println("*********************************"
						+ "\nThanks for using FHJ's App! Bye :)");
				return;
				
			case "userguide":
				System.out
						.println("*********************************"
								+ "\n         USERS GUIDE          "
								+ "\n*********************************"
								+ "\n********Available Commands********"
								+ "\nUsers Commands"
								+

								"\n-Creates a New User: POST /users"
								+ "\n Example: POST /users loginName=Lima&loginPassword=SLB"
								+ "&username=Gaitan"
								+ "&password=SLB"
								+ "&email=OMAIOREMail"
								+ "&fullname=Gaitan\n"
								+

								"\n-Returns the List of Users: GET /users"
								+ "\n Example: GET /users\n"
								+

								"\n-Returns Information About a User(indicate username)"
								+ "\n Example: GET /users/ username=Gaitan\n"
								+

								"\nShelfs Commands"
								+ "\n-Creates a New Shelf With a Certain Dimension(nbElements)"
								+ "\n this command needs a valid Login"
								+ "\n Example: POST /shelfs loginName=Lima&loginPassword=SLB&nbElements=10\n"
								+

								"\n-Creates a New Element in the Indicated Shelf: POST /shelfs/{sid}/elements/{type}\n"
								+

								"\n-Creates a New Element in an Existent Collection: Post /shelfs/{sid}/elements/{type}/{eid}\n"
								+

								"\n-Returns Information about all Elements of certain Shelf: GET /shelfs/{sid}/elements\n"
								+

								"\n-Returns a certain Element: GET /shelfs/{sid}/elements/{eid}\n"
								+

								"\n-Returns Information of a Certain Shelf: GET /shelfs/{sid}/details\n"
								+

								"\n-Returns the Details of all Shelfs - GET /shelfs/\n"

						);
				continue;
			default:
				try {
					parser.getCommand(kbd.split(" ")).execute();
					
				} catch (CommandException e) {
					// TODO: handle exception
				} catch (UnknownCommandException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (DuplicateArgumentsException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvalidCommandArgumentsException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		} while (run == true);

	}

	public static void main(String[] args) {
		ShelfManagerApp app = new ShelfManagerApp();
		app.run();
	}

}
