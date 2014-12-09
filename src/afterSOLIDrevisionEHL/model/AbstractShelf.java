package afterSOLIDrevisionEHL.model;

import Database.DatabaseElements;

public abstract class AbstractShelf implements DatabaseElements {
	
	private static long id;
	private static long lastId;
	
	public AbstractShelf() {
		id = ++lastId;
	}

	public static long getId() {
		return id;
	}
	
	
}
