package fhj.shelf.output;

import java.util.Map;

public interface StrategyFormatter {

	public String encode(Map<String, String> commandResult);
}
