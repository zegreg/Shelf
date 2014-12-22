package main.java.FHJ.shelf.output;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AppStrategy {

	public static void main(String[] args) throws IOException {
		
		StringBuilder builder = new StringBuilder();
		
		Strategy strategy = new PlainCompositor(builder);
		
		
		Context<Strategy> context = new Context<Strategy>(strategy);
		
			
			
		 Map<String, String> mapObject = new HashMap<String, String>();
		
			    mapObject.put("username","josé");
				
		        mapObject.put("password", "jbvc");
		
	
		//String s = "\nLoginName : " +"Jose" +"\nLoginPassword : " +"6676";
	
		
	
		
		System.out.print(context.executeStrategy(mapObject));
	}

}
