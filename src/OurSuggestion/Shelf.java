package OurSuggestion;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

import OurSuggestion.Element;

public class Shelf implements Storage, RequestManager {
	private Collection<Element> shelf;
	private final int capacity;
	private int freeSpace;

	public Shelf(int capacity) {
		if (capacity < 1)
			throw new IllegalArgumentException(
					"The Shelf must have a capacity bigger than 0");

		this.capacity = capacity;
		this.freeSpace = capacity;
		shelf = new TreeSet<Element>();
	}

	@Override
	public boolean add(Element element) {
		if (element == null || shelf.contains(element))
			return false;

		int elemSize = element.getSize();

		if (elemSize < 1 || elemSize > getFreeSpace())
			return false;

		shelf.add(element);
		freeSpace -= elemSize;
		element.isInAShelf(true);
		return true;
	}

	@Override
	public boolean remove(Element element) {
		if (element == null || !shelf.contains(element))
			return false;

		if (element.isRequested())
			return false;

		shelf.remove(element);
		freeSpace += element.getSize();
		return true;
	}

	@Override
	public Element requestElement(String title, String type) {

		if(title == null || type == null)
			return null;
		
		if (type.equals("collection"))
			return null;

		Element theElement = null;
		for (Element e : shelf) {
			if (e.isRequested()) {
				Element elem = e.isOrContains(title, type);
				if (elem != null) {
					theElement = elem;
					e.changeAvailability();
					break;
				}
			}
		}
	}

	public Element findElement(String title, String type) {
		for (Element e : shelf) {
			Element elem = e.isOrContains(title, type);

			if (elem != null) {
				return elem;
			}
		}
		return null;
	}

	@Override
	public Element returnElement(Element element) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getStoredElementsInformation() {

	}

}
