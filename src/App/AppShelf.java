package App;


import afterSOLIDrevisionEHL.model.CD;
import afterSOLIDrevisionEHL.model.Shelf;
import commads.GetShelf;
import commads.GetShelfElement;
import commads.GetShelfElements;
import commads.PostElement;
import commads.GetShelfs;
import commads.PostShelf;
import exceptions.CommandException;
import CommandParser.CommandParser;
import CommandParser.DuplicateArgumentsException;
import CommandParser.InvalidCommandArgumentsException;
import CommandParser.InvalidRegisterException;
import CommandParser.UnknownCommandException;
import Database.ElementsRepository;
import Database.InMemoryElementsRepository;
import Database.InMemoryShelfRepository;
import Database.ShelfRepository;


public class AppShelf {

	public static void main(String[] args) throws InvalidRegisterException,
	UnknownCommandException, DuplicateArgumentsException, InvalidCommandArgumentsException, CommandException {
		
		// Cria um CommandParser
		CommandParser parser = new CommandParser();
		
		// Cria um Repositório
		ShelfRepository shelfRepo = new InMemoryShelfRepository();
		ElementsRepository elementsRepo = new InMemoryElementsRepository();
		
		
		// Regista o comando Post/Shelf
		
		parser.registerCommand("POST",
				new StringBuilder("/shelfs").toString(),
				new PostShelf.Factory(shelfRepo));
		
		//  Executa o comando Post/Shelf com o parametro nbElements =10
		parser.getCommand("POST", "/shelfs/", "nbElements=10").execute();
		
		// Regista o comando Get/shelfs/ID
		
		parser.registerCommand("GET", 
				new StringBuilder("/shelfs/{").append(GetShelf.SID).append("}").append("/details").toString(), 
				new GetShelf.Factory(shelfRepo));
		
		//  Executa o comando Get/shelfs/{sid}/details
		
		parser.getCommand("GET", "/shelfs/0/details").execute();
		
		
//		Regista o comando Get/shelfs/
		parser.registerCommand("Get", "/shelfs/", new GetShelfs.Factory(shelfRepo));
		
		//Executa o comando Get/shelfs/
		parser.getCommand("Get", "/shelfs/").execute();
		
		
		parser.registerCommand("POST",
				new StringBuilder("/shelfs/{").append(PostElement.SID).append("}")
				.append("/elements/{").append(PostElement.ELEMENT_TYPE).append("}")
				.toString(),				
				new PostElement.Factory(shelfRepo, elementsRepo));
		
		parser.getCommand("POST", "/shelfs/0/elements/Book/","name=A mãe&author=Máximo Gorki").execute();
	
		
		//Embuste para testar get element de uma shelf
		
		Shelf shelf1 = new Shelf(10);
		shelfRepo.insert(shelf1);
		
		CD cd = new CD("A cd", 10);
		elementsRepo.insert(cd);
		
		shelf1.add(cd);
		
		
		// Regista o comando Get/shelfs/{sid}/elements/{eid}
		
		parser.registerCommand("GET", 
				new StringBuilder("/shelfs/{").append(GetShelfElement.SID).append("}").append("/elements/{")
				.append(GetShelfElement.EID).append("}").toString(), 
				new GetShelfElement.Factory(shelfRepo, elementsRepo));
		
		//  Executa o comando Get/shelfs/{sid}/elements/{eid}
		
		parser.getCommand("GET", "/shelfs/1/elements/1").execute();
		
	//Embuste para testar get element de uma shelf
		
		Shelf shelf2 = new Shelf(10);
		shelfRepo.insert(shelf2);
		
		CD cd1 = new CD("A new cd", 20);
		elementsRepo.insert(cd1);
		
		shelf2.add(cd1);
		
		
		
		//Regista o comando Get/shelfs/{sid}/elements
		parser.registerCommand("GET", 
				new StringBuilder("/shelfs/{").append(GetShelfElements.SID).append("}").append("/elements")
				.toString(), 
				new GetShelfElements.Factory(shelfRepo));
		
		//  Executa o comando Get/shelfs/{sid}/elements/
		
		parser.getCommand("GET", "/shelfs/2/elements").execute();
		
		
/*
//		Registar Get/Shelf/ID/elements
		
	
		Shelf shelf1 = (Shelf) productRepo.GetElementById(0);
		
		shelf1.add(new CD("CD", 1));
		
		productRepo.insert(shelf1);
		
		parser.registerCommand("Get",
				new StringBuilder("/Shelf/{").append(GetShelfElements.ID).append("}").append("/elements").toString(), 
				new GetShelfElements.Factory(productRepo));
		
		parser.getCommand("Get", "/Shelf/0/elements").execute();
		
		
		
//		productRepo.insert(new Shelf (20));
//		parser.registerCommand("POST", "/Shelf", new PostShelf.Factory(productRepo));
//		parser.getCommand("POST", "/Shelf").execute();
		

//		productRepo.insert(new Shelf (30));
//		parser.registerCommand("POST", "/Shelf", new PostShelf.Factory(productRepo));
//		parser.getCommand("POST", "/Shelf").execute();
		
		
		
	
		
		
		
//		parser.registerCommand("Get", "/Shelf", new GetShelf.Factory(productRepo));
//		parser.getCommand("Get", "/Shelf").execute();
		
		
		
		
//		StringBuilder builder = new StringBuilder();
//		for (String elements : PostElement.DEMANDING_PARAMETERS) {
//			builder.append(elements);
//		}
	
		
		
		
		parser.registerCommand("POST",
				new StringBuilder().append("/Shelfs/{").append(PostElement.SID).append("}")
				.append("/elements/{").append(PostElement.TYPE).append("}/")
				.toString(),				
				new PostElement.Factory(productRepo));
		
		parser.getCommand("POST", "/Shelfs/0/elements/Book/","title=A mae&details=Maximo Gorki").execute();
		
		
		
		
		
//		UserRepository userRepository = new InMemoryUserRepository();
//		userRepository.insert(new User("Jos�", "6676", "j@gmail.com", "Jose Oliveira"));
//		parser.registerCommand("Get", "/Shelf", new GetUser.Factory(userRepository));
//		parser.getCommand("Get", "/Shelf").execute();
		
		
		
		*/
		
	}

}
