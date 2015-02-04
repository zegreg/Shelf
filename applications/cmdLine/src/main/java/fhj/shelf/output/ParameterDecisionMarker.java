package fhj.shelf.output;

import java.util.Map;

public interface ParameterDecisionMarker {

	String execute(StackMensage stackMensage, Map<String, String> parameters);

	
	
}
