package OurSuggestion;


import java.util.Collection;
import java.util.Iterator;
import java.util.TreeSet;

import OurSuggestion.Element;


public class Shelf implements Storage, RequestManager
{
	private Collection<Element> shelf;
	private final int capacity;
	private int freeSpace;
	
	
	public Shelf (int capacity)
	{
		if (capacity < 1)
			throw new IllegalArgumentException(
					"The Shelf must have a capacity bigger than 0");
		
		this.capacity = capacity;
		this.freeSpace = capacity;
		shelf = new TreeSet<Element>();
	}
	
	public boolean add(Element element)
	{
		if(element == null || shelf.contains(element))
			return false;
		
		int elemSize = element.getSize();
		
		if(elemSize < 1 || elemSize > getFreeSpace() )
			return false;
		
		element.setShelf(this);
		shelf.add(element);
		freeSpace -= elemSize;
	}
	
	
	
	@Override
	public void add( Element element ) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void remove( Element element ) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public Element requireElement( Element element ) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Element returnElement( Element element ) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
