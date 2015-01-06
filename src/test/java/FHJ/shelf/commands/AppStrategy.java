package test.java.FHJ.shelf.commands;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import main.java.FHJ.shelf.output.PlainBuilder;
import main.java.FHJ.shelf.output.StrategyFormatter;
import main.java.FHJ.shelf.output.TextFormatterExecuter;

public class AppStrategy {

	public static void main(String[] args) throws IOException {
		
		StringBuilder builder = new StringBuilder();
		
		StrategyFormatter strategy = new PlainBuilder(builder);
		
		
		TextFormatterExecuter<StrategyFormatter> context = new TextFormatterExecuter<StrategyFormatter>(strategy);
		
			
			
		 Map<String, String> mapObject = new HashMap<String, String>();
		
			    mapObject.put("username","jos�");
				
		        mapObject.put("password", "jbvc");
		
	
		//String s = "\nLoginName : " +"Jose" +"\nLoginPassword : " +"6676";
	
		
	
		
		System.out.print(context.executeStrategy(mapObject));
	}

}
