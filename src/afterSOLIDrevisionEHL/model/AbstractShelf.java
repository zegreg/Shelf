package afterSOLIDrevisionEHL.model;

import Database.DatabaseElements;

public abstract class AbstractShelf implements DatabaseElements {
	
	/**
	 * Holds the value of the last assigned identifier.
	 */
	private static long lastId = 0;
	
	/**
	 * Holds the instance's identifier. 
	 */
	protected long id;
	
	
	public AbstractShelf(long id) {
		this.id = id;
		id = ++lastId;
	}

	/**
	 * Gets the product's id
	 * 
	 * @return the {@code Product} name.
	 */
	
	public long getID() 
	{
		return id;
	}
	
	
}
