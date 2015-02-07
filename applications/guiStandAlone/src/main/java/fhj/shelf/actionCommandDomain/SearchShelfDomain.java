package fhj.shelf.actionCommandDomain;

import java.util.Map;
import java.util.TreeMap;

import fhj.shelf.commandsDomain.GetOneShelf;
import fhj.shelf.commandsDomain.GetOneUser;
import fhj.shelf.repos.AbstractUser;
import fhj.shelf.repos.ShelfRepository;
import fhj.shelf.repos.UserRepository;
import fhj.shelf.utils.Shelf;

public class SearchShelfDomain {

	
	public SearchShelfDomain() {
		// TODO Auto-generated constructor stub
	}
	
	
	public static Map<String, String>  GetShelfInformation(ShelfRepository repository,Map<String, String> params) throws Exception{
		
		
		Shelf shelf = new GetOneShelf(repository, Long.valueOf(params.get("nbElements"))).call();
		Map<String, String> map = new TreeMap<String, String> ();

		map.put("Capacity", String.valueOf(shelf.getCapacity()));
		map.put("FreeSpace", String.valueOf(shelf.getFreeSpace()));
		
		return  map;

	}
	
	
}
