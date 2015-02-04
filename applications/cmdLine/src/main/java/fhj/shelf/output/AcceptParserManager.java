package fhj.shelf.output;

import java.util.Map;

import fhj.shelf.commands.exceptions.InvalidAcceptParameterException;

public class AcceptParserManager {

	Map<String, String> parameters;
	StackMensage stackMensage;
	
	public AcceptParserManager(StackMensage stackMensage,Map<String, String> parameters) {
		this.parameters = parameters;
        this.stackMensage = stackMensage;
	}

	public String textFormatter(StackMensage stackMensage, String format)
			throws InvalidAcceptParameterException {

		if (format == null || format.equalsIgnoreCase("txt/plain")) {
			ParameterDecisionMarker accept = new Accept(parameters, "Plain");
			return accept.execute(stackMensage,parameters);
		}

		else if (format.equalsIgnoreCase("txt/html")) {
			ParameterDecisionMarker accept = new Accept(parameters, "Html");
			return accept.execute(stackMensage,parameters);
		}

		else if (format.equalsIgnoreCase("application/json")) {
			ParameterDecisionMarker accept = new Accept(parameters, "Json");
			return accept.execute(stackMensage,parameters);
		} else {
			throw new InvalidAcceptParameterException(format);
		}

	}
}
