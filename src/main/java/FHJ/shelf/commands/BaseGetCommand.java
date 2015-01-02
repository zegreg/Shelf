package main.java.FHJ.shelf.commands;

import java.util.Map;

import main.java.FHJ.shelf.commands.exceptions.CommandException;
import main.java.FHJ.shelf.commands.exceptions.OptionalParameterNotPresentException;
import main.java.FHJ.shelf.output.OutputFactory;

public abstract class BaseGetCommand extends BaseCommand {

	public static final String ACCEPT = "accept";

	public static final String OUTPUTFILE = "output-file";

	public static final String[] OPTIONAL_PARAMETERS = new String[] { ACCEPT };

	public BaseGetCommand(Map<String, String> parameters) {
		super(parameters);
	}

	@Override
	protected void internalExecute() throws CommandException {
		validateDemandingParameters(ACCEPT);

		String textFormat = getParameterAsString(ACCEPT);

		// String destination = getParameterAsString(OUTPUT);

		Map<String, String> commandResult;
		commandResult = actionExecute();
		
		OutputFactory outputFormat = new OutputFactory(commandResult);
		outputFormat.getCommand(textFormat, "output");

	}

	abstract protected Map<String, String> actionExecute()
			throws CommandException;

	@Override
	protected String[] getMandatoryParameters() {
		return OPTIONAL_PARAMETERS;
	}

	protected void validateOptionalParameters(String ...parameterNames) throws OptionalParameterNotPresentException {
		for (String name : parameterNames) {
							
			if(!parameters.containsKey(name)) {
				throw new OptionalParameterNotPresentException(name);
			}
		}
	}
	
}
