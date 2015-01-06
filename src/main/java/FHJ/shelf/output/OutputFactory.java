package main.java.FHJ.shelf.output;

import java.util.Map;

import main.java.FHJ.shelf.commands.exceptions.InvalidAcceptParameterException;

public class OutputFactory {

	Map<String, String> parameters;

	public OutputFactory(Map<String, String> parameters) {
		this.parameters = parameters;

	}

	public String textFormatter(String format)
			throws InvalidAcceptParameterException {

		if (format == null || format.equalsIgnoreCase("txt/plain")) {
			OptionalCommand accept = new Accept(parameters, "Plain");
			return accept.execute(parameters);
		}

		else if (format.equalsIgnoreCase("txt/html")) {
			OptionalCommand accept = new Accept(parameters, "Html");
			return accept.execute(parameters);
		}

		else if (format.equalsIgnoreCase("aplication/json")) {
			OptionalCommand accept = new Accept(parameters, "Json");
			return accept.execute(parameters);
		} else {
			throw new InvalidAcceptParameterException(format);
		}

	}
}
