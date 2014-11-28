package OurSuggestion;


public abstract class RequestableElement extends Element
{
		
	public RequestableElement( String title, String type ) {
		super( title, type );
	}
	
	
	public int getSize() {
		return 1;
	}
	
	
	public Element isOrContainsElementsWithTheSameTypeAndTitleAs( Element element ) {
		
		if( isInstanceOfTheSameTypeAndHasTheSameTitleAs( element ) )
			return this;
		
		return null;
	}
	
	public Element isOrContains( Element element ) {
		
		if( equals( element ) )
			return this;
		
		return null;
	}

	
}
