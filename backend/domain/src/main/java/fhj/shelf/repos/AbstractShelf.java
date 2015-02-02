package fhj.shelf.repos;

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
	
	public abstract boolean contains (Element element);
	
	public abstract void removeAllElements ();
	
	public abstract boolean remove(Element element);

	public abstract boolean add(Element element) ;

	public abstract Iterator<Element> getAllElements();


}