package afterSOLIDrevisionEHL.model;


import java.util.concurrent.atomic.AtomicInteger;

import Database.DatabaseElements;

public abstract class AbstractShelf implements DatabaseElements {
	
	
	private static AtomicInteger uniqueId=new AtomicInteger();
    private long id;

    public AbstractShelf() {
    	
	 this.id=uniqueId.getAndIncrement();
	}
   
	
//	private long id = 0;
//	private static long lastId;
	
//	public AbstractShelf() {
//		id = ++lastId;
//		
//		
//	}

	
	public long getId() {
		return id;
	}
	
	
	
	
}
