package main.java.FHJ.shelf.output;

import java.io.IOException;
import java.util.Map;

public class Context<T extends Strategy> {

	Strategy strategy;


	public Context(Strategy strategy) {
		this.strategy = strategy;
	}


	
	public String executeStrategy(Map<String, String> mapObject) throws IOException{
		return strategy.encode(mapObject );
	}


}
