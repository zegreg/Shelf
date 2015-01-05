package main.java.FHJ.shelf.output;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import main.java.FHJ.shelf.model.repos.UserInterface;


public class PlainCompositor implements Strategy {

	
	protected static StringBuilder builder;

	public PlainCompositor(StringBuilder builder) {
		this.builder =builder;
	}

	void builder( String str )  {
		
		builder.append(str);
	}

	

	@Override
	public String encode(Map<String, String>_map)  {
		Iterator<Entry<String, String>> iterator =  _map.entrySet().iterator();
		
		while( iterator.hasNext() )
		{
			builder.append( iterator.next().toString() ).append( "\n\n\n" );
		}
		//System.out.println(builder.toString());
		return builder.toString();
		
	}

}
