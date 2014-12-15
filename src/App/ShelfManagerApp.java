package App;

import java.util.Scanner;

import commads.GetShelf;
import commads.GetShelfElement;
import commads.GetShelfElements;
import commads.GetShelfs;
import commads.GetUser;
import commads.GetUsers;
import commads.PostElement;
import commads.PostShelf;
import commads.PostShelfCollectionElement;
import commads.PostUser;
import exceptions.CommandException;
import User.InMemoryUserRepository;
import User.User;
import User.UserRepository;
import CommandParser.CommandParser;
import CommandParser.DuplicateArgumentsException;
import CommandParser.InvalidCommandArgumentsException;
import CommandParser.InvalidRegisterException;
import CommandParser.UnknownCommandException;
import Database.ElementsRepository;
import Database.InMemoryElementsRepository;
import Database.InMemoryShelfRepository;
import Database.ShelfRepository;

public class ShelfManagerApp {

	public static void RegisterCommand(CommandParser parser, UserRepository userRepo,
			ShelfRepository shelfRepo, ElementsRepository elementsRepo ) throws InvalidRegisterException {
		
		parser.registerCommand("POST",
				new StringBuilder("/users/").toString(),				
				new PostUser.Factory(userRepo));
		
		parser.registerCommand("Get",
				new StringBuilder("/users").toString(), 
				new GetUsers.Factory(userRepo));
		
		parser.registerCommand("Get",
				new StringBuilder("/users/{").append(GetUser.USERNAME).append("}").toString(), 
				new GetUser.Factory(userRepo));
		
		parser.registerCommand("POST",
				new StringBuilder("/shelfs/").toString(),
				new PostShelf.Factory(userRepo, shelfRepo));

		parser.registerCommand("POST",
				new StringBuilder("/shelfs/{").append(PostElement.SID).append("}")
				.append("/elements/{").append(PostElement.ELEMENT_TYPE).append("}")
				.toString(),				
				new PostElement.Factory(userRepo, shelfRepo, elementsRepo));
	
		parser.registerCommand("POST",
				new StringBuilder("/shelfs/{").append(PostShelfCollectionElement.SID).append("}")
				.append("/elements/{").append(PostShelfCollectionElement.ELEMENT_TYPE).append("}/{")
				.append(PostShelfCollectionElement.EID).append("}").toString(),				
				new PostShelfCollectionElement.Factory(userRepo, shelfRepo, elementsRepo));
		
		parser.registerCommand("GET", 
				new StringBuilder("/shelfs/{").append(GetShelfElements.SID).append("}").append("/elements")
				.toString(), 
				new GetShelfElements.Factory(shelfRepo));
	
		parser.registerCommand("GET", 
				new StringBuilder("/shelfs/{").append(GetShelfElement.SID).append("}").append("/elements/{")
				.append(GetShelfElement.EID).append("}").toString(), 
				new GetShelfElement.Factory(shelfRepo, elementsRepo));
		
		parser.registerCommand("GET", 
				new StringBuilder("/shelfs/{").append(GetShelf.SID).append("}").append("/details").toString(), 
				new GetShelf.Factory(shelfRepo));
		
		parser.registerCommand("GET", 
				new StringBuilder("/shelfs/").toString(), 
				new GetShelfs.Factory(shelfRepo));
	}
	
	public void run()
	{
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		
		CommandParser parser = new CommandParser();
		ShelfRepository shelfRepo = new InMemoryShelfRepository();
		ElementsRepository elementsRepo = new InMemoryElementsRepository();
		UserRepository userRepo = new InMemoryUserRepository();
		
		
		try{
			RegisterCommand(parser, userRepo, shelfRepo, elementsRepo);
		} catch(InvalidRegisterException e)
		{
			System.out.println("Invalid Register Command!");
		}
		
		System.out.println("*********************************" + 
						"\nWelcome to ShelfManagerApp of FHJ"+
						"\n*********************************");

		
		User admin = new User("Lima", "SLB", "OMAIOREMail", "Lima" );
		userRepo.add(admin);
		
		boolean run = true;
		do{
			String kbd = input.nextLine();
			
			switch(kbd)
			{
				case "Exit":
					input.nextLine();
					run = false;
					return;
					
				case "user guide":
					
				default:
					try
					{
						parser.getCommand(kbd.split(" ")).execute();
						input.nextLine();
					}
					catch (CommandException e) {
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
			
		}while(run == true);
		
	}
	
	public static void main(String[] args)
	{
		ShelfManagerApp app = new ShelfManagerApp();
		app.run();
	}
	
}
