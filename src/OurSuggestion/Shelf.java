package OurSuggestion;

import java.util.List;

public class Shelf implements Storage, RequestManager {
	
	List<Element> elem;

	@Override
	public boolean requireElement(Element element) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean returnElement(Element element) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void add(Element element) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(Element element) {
		// TODO Auto-generated method stub
		
	}
}
