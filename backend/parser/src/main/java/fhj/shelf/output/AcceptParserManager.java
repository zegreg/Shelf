package fhj.shelf.output;

import java.util.Map;

import fhj.shelf.exceptions.InvalidAcceptParameterException;

/**
 * This class is responsible to choose the output parser
 *
 * @author Filipa Estiveira, Hugo Leal, José Oliveira
 */
public class AcceptParserManager {

	Map<String, String> parameters;

	public AcceptParserManager(Map<String, String> parameters) {
		this.parameters = parameters;

	}

	public String textFormatter(String format)
			throws InvalidAcceptParameterException {

		if (format == null || format.equalsIgnoreCase("text/plain")) {
			ParameterDecisionMarker accept = new Accept(parameters, "Plain");
			return accept.execute(parameters);
		}

		else if (format.equalsIgnoreCase("text/html")) {
			ParameterDecisionMarker accept = new Accept(parameters, "Html");
			return accept.execute(parameters);
		}

		else if (format.equalsIgnoreCase("application/json")) {
			ParameterDecisionMarker accept = new Accept(parameters, "Json");
			return accept.execute(parameters);
		} else {
			throw new InvalidAcceptParameterException(format);
		}

	}
}
