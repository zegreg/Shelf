package main.java.FHJ.shelf.output;

public class CreateFactory {

	 
	   public OptionalCommand getCommand(String key)
	   {
	      if(key == null)
	      {
	         return null;
	      }		
	      if(key.equalsIgnoreCase("ACCEPT"))
	      {
	         return new Accept(null);
	         
	      } else 
	    	  if(key.equalsIgnoreCase("OUTPUT"))
	      {
	         return new Output();
	      } 
	      return null;
	   }
}
