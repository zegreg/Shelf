//package test.java.FHJ.shelf.commands;
//
//import java.io.IOException;
//
//
//
//import java.util.Scanner;
//
//import main.java.FHJ.shelf.commandParser.CommandParser;
//import main.java.FHJ.shelf.commandParser.CommandParserException;
//import main.java.FHJ.shelf.commandParser.InvalidRegisterException;
//import main.java.FHJ.shelf.commands.GetShelf;
//import main.java.FHJ.shelf.commands.GetShelfElement;
//import main.java.FHJ.shelf.commands.GetShelfElements;
//import main.java.FHJ.shelf.commands.GetShelfs;
//import main.java.FHJ.shelf.commands.GetUsers;
//import main.java.FHJ.shelf.commands.PostElement;
//import main.java.FHJ.shelf.commands.PostShelf;
//import main.java.FHJ.shelf.commands.PostUser;
//import main.java.FHJ.shelf.commands.exceptions.CommandException;
//import main.java.FHJ.shelf.model.repos.ElementsRepository;
//import main.java.FHJ.shelf.model.repos.InMemoryElementsRepository;
//import main.java.FHJ.shelf.model.repos.InMemoryShelfRepository;
//import main.java.FHJ.shelf.model.repos.InMemoryUserRepository;
//import main.java.FHJ.shelf.model.repos.ShelfRepository;
//import main.java.FHJ.shelf.model.repos.UserRepository;
//
//public class AppShelfCommad {
//	
//	
//	public static void RegisterCommand(CommandParser parser, UserRepository userRepo, ShelfRepository shelfRepo,
//			ElementsRepository elemRepo )
//			throws InvalidRegisterException {
//
//		parser.registerCommand("POST", new StringBuilder("/User").toString(),new PostUser.Factory(userRepo));
////		parser.registerCommand("POST", "/user", new PostUser.Factory(userRepo));
////		parser.registerCommand("GET", "/users",	new GetUsers.Factory(userRepo));
//////		parser.registerCommand("GET", "/users/{username}", new GetUser.Factory(userRepo));
////		
////		
////		
////		parser.registerCommand("POST", "/shelfs", new PostShelf.Factory(shelfRepo));
////		parser.registerCommand("POST", "/shelfs/{sid}/elements/{type}", new PostElement.Factory(shelfRepo,elemRepo));
//////		parser.registerCommand("POST", "/shelfs/{sid}/elements/{eid}/{type}", new Post.Factory(shelfRepo));
////		
////
////		parser.registerCommand("GET", "/shelfs", new GetShelfs.Factory(shelfRepo));
////		parser.registerCommand("GET", "/shelf/{sid}/details",	new GetShelf.Factory(shelfRepo));
////		parser.registerCommand("GET", "/shelfs/{sid}/elements/{eid}",	new GetShelfElement.Factory(shelfRepo, elemRepo));
////		parser.registerCommand("GET", "/shelfs/{sid}/elements",	new GetShelfElements.Factory(shelfRepo));
//	}
//
//
//	
//	public void execute() throws CommandParserException, CommandException,
//			IOException {
//	
//		Scanner scanner = new Scanner(System.in);
//
//		CommandParser parser = new CommandParser();
//		ShelfRepository shelfRepository = new InMemoryShelfRepository();
//		UserRepository userRepo = new InMemoryUserRepository();
//		ElementsRepository elementsRepository = new InMemoryElementsRepository();
//		
//		
//		RegisterCommand(parser, userRepo, shelfRepository, elementsRepository);
//		
//
//	// iSTO AINDA NÃO FUNCIONA TENHO QUE TENTAR AMANHÃ
//
//		System.out.println("\nInsert the command :");
//		do {
//			String a = scanner.nextLine();
//			
//			a +=" "+ "username=jose&password=6786&email=j@hotmail.com&fullname=JGGO";
//			a.split(" ");
//			a = a.replaceAll(" ",",");
//			 
//			 
//			 System.out.println(a);
//					parser.getCommand( a).execute();
//					scanner.nextLine();
//				
//		} while (true);
//
//	}
//
//	public static void main(String[] args) throws CommandParserException,
//			CommandException, IOException {
//
//		AppShelfCommad app = new AppShelfCommad();
//		app.execute();
//
//	}
//}
