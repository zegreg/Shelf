package fhj.shelf.commands;

import java.util.Map;
import java.util.concurrent.ExecutionException;

import fhj.shelf.commands.exceptions.CommandException;
import fhj.shelf.commands.exceptions.InvalidAcceptParameterException;
import fhj.shelf.output.OutputPrinter;
import fhj.shelf.output.AcceptParserManager;

/**
 * This class is the abstraction for the base get command, it defines how the base 
 * get commands are implemented.
 * 
 * @authors Filipa Estiveira, Hugo Leal, José Oliveira
 */
public abstract class BaseGetCommand extends BaseCommand {

	/**
	 * Optional parameter
	 */
	public static final String ACCEPT = "accept";

	/**
	 * Optional parameter
	 */
	public static final String OUTPUTFILE = "output-file";

	/**
     * An Array of Strings containing all the optional parameters of this command
     */
	public static final String[] OPTIONAL_PARAMETERS = new String[] { ACCEPT, OUTPUTFILE };

	 /**
     * Initiates an instance with the given a command parameters
     * @param parameters the command's unparsed parameters
     */
	public BaseGetCommand(Map<String, String> parameters) {
		super(parameters);
	}

	@Override
	protected void internalExecute() throws CommandException, InvalidAcceptParameterException, ExecutionException {
		
		String textFormat = "";
		if(!ACCEPT.equals(""))
			textFormat = getParameterAsString(ACCEPT);
		
		String outputFile = ""; 	
		if(!OUTPUTFILE.equals(""))
			outputFile = getParameterAsString(OUTPUTFILE);
	
		Map<String, String> commandResult;
		commandResult = actionExecute();
		
		AcceptParserManager outputFormat = new AcceptParserManager(commandResult);
		
		String resultFormatted = outputFormat.textFormatter(textFormat);
	
		OutputPrinter printer = new OutputPrinter(resultFormatted);
		printer.printResult(outputFile);
		
	}

	
	abstract protected Map<String, String> actionExecute()
			throws CommandException, ExecutionException;

	
	/**
	 * Gets the array bearing the names of the demanding command's parameters
	 * 
	 * @return The array containing the names of the mandatory parameters
	 */
	@Override
	protected String[] getMandatoryParameters() {
		return OPTIONAL_PARAMETERS;
	}

	
}
