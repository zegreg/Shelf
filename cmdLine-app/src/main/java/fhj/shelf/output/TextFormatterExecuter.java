package fhj.shelf.output;


import java.util.Map;

public class TextFormatterExecuter<T extends StrategyFormatter> {

	StrategyFormatter strategy;

	public TextFormatterExecuter(StrategyFormatter strategy) {
		this.strategy = strategy;
	}
	
	public String executeStrategy(Map<String, String> mapObject){
		return strategy.encode(mapObject );
	}


}
