package afterSOLIDrevisionEHL.model;

import java.util.concurrent.atomic.AtomicInteger;

import Database.DatabaseElements;

public abstract class AbstractElement implements DatabaseElements {

		private static AtomicInteger uniqueId=new AtomicInteger();
	    private long eid;

	    public AbstractElement() {
	    	
		 this.eid=uniqueId.getAndIncrement();
		}
		
		public long getId() {
			return eid;
		}
		
}
