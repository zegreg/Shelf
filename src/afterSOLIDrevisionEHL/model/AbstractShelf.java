package afterSOLIDrevisionEHL.model;

import java.util.ArrayList;
import java.util.List;

import Database.DatabaseElements;

public abstract class AbstractShelf implements DatabaseElements {
	
	private long id = 0;
	private static long lastId;
	//List<Long> listId = new ArrayList<Long>();
	
	public AbstractShelf() {
		id = ++lastId;
		
	}

	public long getId() {
		return id;
	}
	
	
	
	
}
