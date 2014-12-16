package test.java.FHJ.shelf.model;

import java.util.Collection;
import java.util.TreeSet;



public class UserContent {

	private Collection< User > userCollection;
	
	public UserContent() {
		userCollection = new TreeSet< User >();
	}
	

	public boolean add( User user ) {
		
		if( user == null || userCollection.contains( user ) )
			return false;
		
		else{
			userCollection.add( user );
			return true;
		}
	}



	
}
