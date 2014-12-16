package main.java.FHJ.shelf.model;
//package afterSOLIDrevisionEHL.model;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Scanner;
//
//import afterSOLIDrevisionEHL.model.Book;
//
//
//public class AppTeste {
//
//	private static final String MANAGE_SHELF = "manage shelf";
//	private static final String HELP = "help";
//	private static final String END_APP = "quit";
//
////	private static final String CREATE_COLLECTION = "create collection";
//	private static final String ADD_ELEMENT = "add element";
//	private static final String BACK = "back";
//	private static final String VERIFY_SIZE = "verify size";
//
//	private static final String ADD_COLLECTION = "add collection";
//	private static final String REMOVE_COLLECTION = "remove collection";
//	private static final String FIND_NOT_REQUESTED_ELEMENT = "find not requested element";
//	private static final String FIND_REQUESTED_ELEMENT = "find requested element";
//	private static final String REQUEST_ELEMENT = "request element";
//	private static final String RETURN_ELEMENT = "return element";
//	private static final String SHOW_SHELF = "show shelf";
//	private static final String VERIFY_FREE_SPACE = "verify free space";
//	private static final String CREATE_COLLECTION_DVD = "dvd collection";
//	private static final String CREATE_COLLECTION_CD = "cd collection";
//	private static final String CREATE_COLLECTION_BOOK = "book collection";
//	private static final String CREATE_BOOK = "create book";
//	private static final String CREATE_CD = "create cd";
//	private static final String CREATE_DVD = "create dvd";
//	private static final String REMOVE_BOOK = "remove book";
//	private static final String REMOVE_DVD = "remove dvd";
//	private static final String REMOVE_CD = "remove cd";
//	private static final String CREATE_SHELF = "shelf";
//	private static final String CREATE_USER = "user";
//	private static final String SHOW_USERS = "show users";
//	private static final String FIND_REQUEST_USER = "userid";
//	private static final String ADD_ELEMENT_TO_SHELF = "add element shelf";
//	private static final String ADD_ELEMENT_TO_CDCOLLECTION = "add cdcollection";
//	private static final String ADD_ELEMENT_TO_BOOKCOLLECTION = "add bookcollection";
//	private static final String ADD_ELEMENT_TO_DVDCOLLECTION = "add dvdcollection";
//	private static final String ADD_ELEMENT_TO_COLLECTION = "add element to collection";
//	private static final String SHOW_ALL_ELEMENTS = "show elements";
//	private static final String SHOW_ELEMENTSID = "show idelements";
//	private static final String SHOW_DETAILS = "show details";
//	private static final String SHOW_ALL_DETAILS = "show all details";
//
//
//	
//	
//	private String[] users = { CREATE_USER, SHOW_USERS, FIND_REQUEST_USER};
//
//	private String[] shelfCommands = { CREATE_SHELF, ADD_ELEMENT_TO_SHELF, ADD_ELEMENT_TO_CDCOLLECTION,
//			ADD_ELEMENT_TO_BOOKCOLLECTION, ADD_ELEMENT_TO_DVDCOLLECTION, ADD_ELEMENT_TO_COLLECTION, SHOW_ALL_ELEMENTS,
//			SHOW_ELEMENTSID, SHOW_DETAILS, SHOW_ALL_DETAILS};
//		
//
//	private Scanner scanner;
//	private Shelf shelf;
//
//	private SimpleElement simpleElement;
//	private CD cd;
//	private DVD dvd;
//	private Book book;
//	private CDCollection cdCollection;
//	private BookCollection bookCollection;
//	private DVDCollection dvdCollection;
//	private SimpleElement composedElement;
//	private Element element;
//
//	private String type;
//	private String title;
//
//	
//	
//	public AppTeste() 
//	{
//
//		scanner = new Scanner(System.in);
////		CreateShelf();
//
//		
//	}
//	
//
//	private void CreateShelf() {
//		System.out.println("Please insert the maximum capacity of the shelf:");
//		shelf = new Shelf(scanner.nextInt());
//		System.out.print(scanner.nextLine());
//		
//		Book book1 = new Book("Book1", "Author1");
//		Book book2 = new Book("Book2", "Author2");
//		
//		BookCollection col = new BookCollection("Collection of Books");
//		col.addElement(book1);
//		col.addElement(book2);
//		
//		cd = new CD("CD1", 20);
//
//		shelf = new Shelf(20);
//		
//		shelf.add(col);
//		shelf.add(cd);
//	}
//
//
//	public void execute() {
//
//		boolean exit = false;
//
//		while (!exit) {
//
//			showCommands(0);
//
//			String command = readCommand();
//			switch (command) {
//
//			case CREATE_USER:
//				// Create and Remove Element from a collection
//				executeUser();
//				
//				break;
//
//
//			case MANAGE_SHELF:
//
//				executeManageShelf();
//
//				break;
//			
//			case HELP:
//
//				showCommands(0);
//				break;
//
//			case END_APP:
//
//				exit = true;
//				break;
//
//			default:
//
//				System.out.println("Invalid command");
//			}
//		}
//	}
//
//	private void executeUser() {
//		// TODO Auto-generated method stub
//		
//	}
//
//
//	private void executeManageElement() {
//		boolean back = false;
//
//		while (!back) {
//
//			showCommands(1);
//
//			String command = readCommand();
//
//			switch (command) {
//
//			case CREATE_DVD:
//
//				System.out.println("Choose an element DVD title : ");
//				String title = readCommand();
//				
//				System.out.println("Choose a  DVD duration : ");
//				int duration = Integer.parseInt(readCommand());
//				
//				dvd = new DVD(title, duration);
//				
//				while( true )
//				{
//					try
//					{
//							if (dvdCollection.addElement(dvd) == false) 
//							{
//								System.out.println("That DVD has already been added to this project!");
//							}
//							else
//							{
//						
//							dvdCollection.addElement(dvd);
//							shelf.add(dvdCollection);
//							}
//					}
//
//					catch( NullPointerException npe)
//					{
//						System.out.println( "The DVD do not exist!" );
//						break;
//					}
//					System.out.print( "Do you want to add a new DVD? (y) " );
//
//					if( ! scanner.nextLine().equals("y") )
//						break;
//
//				}
//
//				break;
//				
//			case CREATE_CD:
//				
//				System.out.println("Choose a CD title : ");
//				String cdTitle = readCommand();
//				
//
//				System.out.println("Choose a CD track : ");
//				int track = Integer.parseInt(readCommand());
//				
//				cd = new CD(cdTitle, track);
//				
//				while( true )
//				{
//					try
//					{
//							if (cdCollection.addElement(cd) == false) 
//							{
//								System.out.println("That CD has already been added to this project!");
//							}
//							else
//							{
//						
//							cdCollection.addElement(cd);
//							shelf.add(cdCollection);
//							}
//										
//					}
//
//					catch( NullPointerException npe)
//					{
//						System.out.println( "The CD do not exist!" );
//						break;
//					}
//					System.out.print( "Do you want to add a new CD? (y) " );
//
//					if( ! scanner.nextLine().equals("y") )
//						break;
//
//				}
//
//				
//				
//				
//
//				break;
//				
//			case CREATE_BOOK:
//				
//				System.out.println("Choose a Book title : ");
//				String bookTitle = readCommand();
//				
//				System.out.println("Choose a Book author : ");
//				String bookAuthor = readCommand();
//				
//				book = new Book(bookTitle, bookAuthor);
//				while( true )
//				{
//					try
//					{
//							if (bookCollection.addElement(book) == false) 
//							{
//								System.out.println("That Book has already been added to this project!");
//							}
//							else
//							{
//						
//							bookCollection.addElement(book);
//							shelf.add(bookCollection);
//							}
//										
//					}
//
//					catch( NullPointerException npe)
//					{
//						System.out.println( "The Book do not exist!" );
//						break;
//					}
//					System.out.print( "Do you want to add a new Book? (y) " );
//
//					if( ! scanner.nextLine().equals("y") )
//						break;
//
//				}
//
//				
//				break;
//
//			
//			case REMOVE_CD:
//
//				System.out.print("which is the name cd to remove : ");
//
//				String nameCD = readCommand();
//
//				System.out.println("Which is the track of CD : ");
//				
//				int track1 = Integer.parseInt(readCommand());
//				
//				cd = new CD(nameCD, track1);
//
//			
//
//				try
//				{
//					if (cdCollection.removeElement(cd) == false ) {
//						System.out.println( "Enable to remove the CD" );
//					}
//					else
//					{
//				
//					cdCollection.removeElement(cd);
//					shelf.remove(cdCollection);
//					}
//
//
//				}
//				catch( NullPointerException npe)
//				{
//					System.out.println( "The CD do not exist!" );
//				}
//
//
//
//
//				break;
//
//			case REMOVE_BOOK:
//
//				System.out.print("which is the name book to remove : ");
//
//				String nameBook = readCommand();
//
//				System.out.print("which is the author book to remove : ");
//
//				String author = readCommand();
//
//				book = new Book(nameBook, author);
//
//				try
//				{
//					if (bookCollection.removeElement(book) == false ) {
//						System.out.println( "Enable to remove the Book" );
//					}
//					else
//					{
//				
//					bookCollection.removeElement(book);
//					shelf.remove(bookCollection);
//					}
//
//
//				}
//				catch( NullPointerException npe)
//				{
//					System.out.println( "The Book do not exist!" );
//				}
//
//
//
//
//				break;
//
//			case REMOVE_DVD:
//
//				System.out.print("which is the name DVD to remove : ");
//
//				String nameDVD = readCommand();
//
//				System.out.print("which is the duration DVD to remove : ");
//
//				int duration2 = Integer.parseInt(readCommand());
//
//				dvd = new DVD(nameDVD, duration2);
//
//				try
//				{
//					if (dvdCollection.removeElement(dvd) == false ) {
//						System.out.println( "Enable to remove the DVD" );
//					}
//					else
//					{
//				
//					dvdCollection.removeElement(dvd);
//					shelf.remove(dvdCollection);
//					}
//
//
//				}
//				catch( NullPointerException npe)
//				{
//					System.out.println( "The DVD do not exist!" );
//				}
//
//
//				break;
//
//			case VERIFY_SIZE:
//
//				System.out.println("Verify size : ");
//				
//				System.out.println(composedElement.getSize());
//			
//				break;
//
//			case HELP:
//
//				showCommands(1);
//				break;
//
//			case BACK:
//
//				back = true;
//				break;
//
//			default:
//
//				System.out.println("Invalid command");
//			}
//		}
//		
//	}
//
//	private void executeManageCollections() {
//
//		boolean back = false;
//
//		while (!back) {
//
////			showCommands(1);
//
//			String command = readCommand();
//
//			switch (command) {
//
//			case CREATE_COLLECTION_DVD:
//
//				System.out.println("Choose a collection DVD title : ");
//				String title = readCommand();
//				dvdCollection = new DVDCollection(title);
//
//				break;
//				
//			case CREATE_COLLECTION_CD:
//				
//				System.out.println("Choose a collection CD title : ");
//				String cdTitle = readCommand();
//
//				bookCollection = new BookCollection(cdTitle);
//
//				break;
//				
//			case CREATE_COLLECTION_BOOK:
//				
//				System.out.println("Choose a collection CD title : ");
//				String bookTitle = readCommand();
//
//				cdCollection = new CDCollection(bookTitle);
//
//				break;
//
//
//			case REMOVE_COLLECTION:
//
//				System.out.println("Choose Collection to be delete");
//				String collectionTitle = readCommand();
//
//				try 
//				{
//					if (composedElement.isInstanceWithTheSameTypeAndTitleAs(collectionTitle) == false) 
//					{
//						System.out.println("can not be removed ");
//					}
//				}
//				catch( NullPointerException npe)
//				{
//					System.out.println( "The project/subproject do not exist!" );
//
//				}
//
//
//				break;
//
//			case VERIFY_SIZE:
//
//				System.out.println("Verify size Collection : ");
//				
//				String collectionTitle1 = readCommand();
//				
//				if (composedElement.isInstanceWithTheSameTypeAndTitleAs(collectionTitle1) == true) {
//					System.out.println(composedElement.getSize());
//				} 
//						
//			
//				break;
//
//			case HELP:
//
//				showCommands(1);
//				break;
//
//			case BACK:
//
//				back = true;
//				break;
//
//			default:
//
//				System.out.println("Invalid command");
//			}
//		}
//	}
//
//	private void executeManageShelf() {
//
//		boolean back = false;
//
//		while (!back) {
//
//			showCommands(2);
//
//			String command = readCommand();
//
//			switch (command) {
//			
//			case CREATE_SHELF:
//				
//				System.out.println("Please insert the maximum capacity of the shelf:");
//				shelf = new Shelf(scanner.nextInt());
//				System.out.print(scanner.nextLine());
//			
//				
//			case ADD_ELEMENT_TO_SHELF:
//				executeManageElement();
//				
//				
//			break;
////			case ADD_ELEMENT_TO_CDCOLLECTION:
////			case ADD_ELEMENT_TO_BOOKCOLLECTION:
////			case ADD_ELEMENT_TO_DVDCOLLECTION:
////			case ADD_ELEMENT_TO_COLLECTION:
////			case SHOW_ALL_ELEMENTS:
////			case SHOW_ELEMENTSID:
////			case SHOW_DETAILS: 
////			case SHOW_ALL_DETAILS:
//				
//				
//			case FIND_NOT_REQUESTED_ELEMENT:
//
//				
//
//				break;
//
//			case FIND_REQUESTED_ELEMENT:
//
//				
//
//				break;
//
//			case REQUEST_ELEMENT:
//
//				
//				break;
//
//			case RETURN_ELEMENT:
//
//				shelf.returnElement(composedElement);
//
//				break;
//
//			case SHOW_SHELF:
//
//				
//				System.out.println(shelf.getInfoAboutAllElementsContained());
//				
//
//				break;
//
//			case VERIFY_FREE_SPACE:
//
//				
//				break;
//
//			case HELP:
//
//				showCommands(2);
//				break;
//
//			case BACK:
//
//				back = true;
//				break;
//
//			default:
//
//				System.out.println("Invalid command");
//			}
//		}
//	}
//
//	private void showCommands(int menu) {
//
//		if (menu == 0)
//			for (int i = 0; i < commands.length; i++)
//				System.out.println(((i == 0) ? "\n" : "") + commands[i]
//						+ ((i == commands.length - 1) ? "\n" : ""));
//
//		if (menu == 1)
//			for (int i = 0; i < collectionsCommands.length; i++)
//				System.out.println(((i == 0) ? "\n" : "")
//						+ collectionsCommands[i]
//						+ ((i == collectionsCommands.length - 1) ? "\n" : ""));
//
//		if (menu == 2)
//			for (int i = 0; i < shelfCommands.length; i++)
//				System.out.println(((i == 0) ? "\n" : "") + shelfCommands[i]
//						+ ((i == shelfCommands.length - 1) ? "\n" : ""));
//	}
//
//	private String readCommand() {
//
//		return scanner.nextLine();
//	}
//	
//
//	public static void main(String[] args) {
//		
//		AppTeste app = new AppTeste();
//		app.execute();
//	}
//}