package afterSOLIDrevisionEHL.model;

import java.util.ArrayList;
import java.util.List;

import Database.DatabaseElements;

public abstract class AbstractShelf implements DatabaseElements {
	
	private static long id = 0;
	private static long lastId;
	static List<Long> listId = new ArrayList<Long>();
	
	public AbstractShelf() {
		id = ++lastId;
		listId.add(id) ;
		
	}

	public static  long getId() {
		
		long elementId = 0;
		for (Long id : listId) {
			
			elementId = id.longValue();
			}
		return elementId;
	}
	
	
	
	
}
