package OurSuggestion;

public abstract class RequestableElement extends Element implements Requestable{


	
	public RequestableElement(String title, String type) {
		super(title, type);
	}

	
	public int getSize()
	{
		return 1;
	}
	
	public Element isOrContains(String title, String type)
	{
		 if(getTitle().equals(title) && getType().equals(type))
		 {
			 return this;
		 }
		return null;
	}
	
}
