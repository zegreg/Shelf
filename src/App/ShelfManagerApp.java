package App;

import commads.GetShelf;
import commads.GetShelfElement;
import commads.GetShelfElements;
import commads.GetShelfs;
import commads.PostElement;
import commads.PostShelf;
import commads.PostShelfCollectionElement;
import User.UserRepository;
import CommandParser.CommandParser;
import CommandParser.InvalidRegisterException;
import Database.ElementsRepository;
import Database.ShelfRepository;

public class ShelfManagerApp {

	public static void RegisterCommand(CommandParser parser, UserRepository userRepo,
			ShelfRepository shelfRepo, ElementsRepository elementsRepo ) throws InvalidRegisterException {
		
		parser.registerCommand("POST",
				new StringBuilder("/shelfs").toString(),
				new PostShelf.Factory(userRepo, shelfRepo));

		parser.registerCommand("POST",
				new StringBuilder("/shelfs/{").append(PostElement.SID).append("}")
				.append("/elements/{").append(PostElement.ELEMENT_TYPE).append("}")
				.toString(),				
				new PostElement.Factory(userRepo, shelfRepo, elementsRepo));
		
		parser.registerCommand("POST",
				new StringBuilder("/shelfs/{").append(PostShelfCollectionElement.SID).append("}")
				.append("/elements/{").append(PostShelfCollectionElement.EID).append("}/{")
				.append(PostShelfCollectionElement.ELEMENT_TYPE).append("}").toString(),				
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
}
