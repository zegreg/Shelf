package main.java.FHJ.shelf.commands;

import java.util.Map;

import main.java.FHJ.shelf.commands.exceptions.CommandException;
import main.java.FHJ.shelf.commands.exceptions.OptionalParameterNotPresentException;
import main.java.FHJ.shelf.output.Output;
import main.java.FHJ.shelf.output.OutputFactory;

public abstract class BaseGetCommand extends BaseCommand {

	public static final String ACCEPT = "accept";

	public static final String OUTPUTFILE = "output-file";

	public static final String[] OPTIONAL_PARAMETERS = new String[] { ACCEPT, OUTPUTFILE };

	public BaseGetCommand(Map<String, String> parameters) {
		super(parameters);
	}

	@Override
	protected void internalExecute() throws CommandException, OptionalParameterNotPresentException {
		//validateDemandingParameters(ACCEPT);

		String textFormat = "";
		if(!ACCEPT.equals(""))
			textFormat = getParameterAsString(ACCEPT);
		
		String outputFile = ""; 	
		if(!OUTPUTFILE.equals(""))
			outputFile = getParameterAsString(OUTPUTFILE);
	

		Map<String, String> commandResult;
		commandResult = actionExecute();
		
		//verifyOptionalParameters(OPTIONAL_PARAMETERS);
		
		OutputFactory outputFormat = new OutputFactory(commandResult);
		
		String resultFormatted = outputFormat.textFormatter(textFormat);
	
		Output printer = new Output(resultFormatted);
		printer.printResult(outputFile);
		
	}

	abstract protected Map<String, String> actionExecute()
			throws CommandException;

	@Override
	protected String[] getMandatoryParameters() {
		return OPTIONAL_PARAMETERS;
	}

	protected void verifyOptionalParameters(String ...parameterNames) throws OptionalParameterNotPresentException {
		
		for (String name : parameterNames) {
			
	
			if(!parameters.containsKey(name)) {
				throw new OptionalParameterNotPresentException(name);
			}
		}
	}
	
}
