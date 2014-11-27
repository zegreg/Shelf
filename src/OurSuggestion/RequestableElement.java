package OurSuggestion;

public abstract class RequestableElement extends Element implements Requestable{

	public RequestableElement( String title, String type ) {
		super( title, type );
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean getAvailability() {
		// TODO Auto-generated method stub
		return false;
	}

}
