package fhj.shelf.utils.repos;

import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;

import fhj.shelf.utils.Element;

public abstract class AbstractShelf implements DatabaseElements {

	private static AtomicInteger uniqueId = new AtomicInteger();
	private long id;

	public AbstractShelf() {

		this.id = uniqueId.getAndIncrement();
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}
	
	
	public abstract String details();

	public abstract int getCapacity();

	public abstract int getFreeSpace();
	
	public abstract boolean contains (AbstractElement element);
	
	public abstract void removeAllElements ();
	
	public abstract boolean remove(AbstractElement element);

	public abstract boolean add(AbstractElement element) ;

	public abstract Iterator<AbstractElement> getAllElements();


}