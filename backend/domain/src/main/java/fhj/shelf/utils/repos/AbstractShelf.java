package fhj.shelf.utils.repos;

import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;

import fhj.shelf.utils.Element;

public abstract class AbstractShelf implements DatabaseElements {

	
	/**
	 * Holds the instance's identifier. 
	 */
	private final long id;
	public AbstractShelf(long id) {
		this.id = id;
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