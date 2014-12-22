package main.java.FHJ.shelf.output;


import java.util.Map;

public class Context<T extends Strategy> {

	Strategy strategy;


	public Context(Strategy strategy) {
		this.strategy = strategy;
	}


	
	public String executeStrategy(Map<String, String> mapObject){
		return strategy.encode(mapObject );
	}


}
