package main.java.FHJ.shelf.commands;

import java.util.Map;

import main.java.FHJ.shelf.commands.exceptions.CommandException;
import main.java.FHJ.shelf.commands.exceptions.DemandingParameterNotPresentException;
import main.java.FHJ.shelf.commands.exceptions.InvalidAcceptParameterException;
import main.java.FHJ.shelf.commands.exceptions.OptionalParameterNotPresentException;

public abstract class BaseCommand implements Command {

	/**
	 * The command arguments;
	 */
	protected final Map<String, String> parameters;

	
	 /**
     * Initiates an instance with the given command parameters
     * @param parameters the command's unparsed parameters
     */
	public BaseCommand(Map<String, String> parameters) {
		super();
		this.parameters = parameters;
	}

	
	/**
	 * Performs the execution order command :firstly validate demanding parameter and then call internalExecute() method
	 */
	public final void execute() throws CommandException,
			OptionalParameterNotPresentException,
			InvalidAcceptParameterException {
		validateDemandingParameters(getMandatoryParameters());
		internalExecute();
	}

	/**
	 * Gets the array bearing the names of the demanding command's parameters
	 * 
	 * @return The array containing the names of the mandatory parameters
	 */
	protected abstract String[] getMandatoryParameters();
	
	
	/**
	 * Performs the actual execution of the command, producing the corresponding result
	 * 
	 * @throws OptionalParameterNotPresentException 
     * @throws CommandException if the command's execution preconditions are not met, e.g. a
     * parameter has an invalid value
	 */
	abstract protected void internalExecute() throws CommandException,
			OptionalParameterNotPresentException;

	
	/**
	 * Method used to perform validation of mandatory parameters. The 
	 *  
	 * @param parameterNames The parameter set to be validated
	 * @throws MandatoryParameterNotPresentException if a mandatory parameter is not present
	 */
	protected void validateDemandingParameters(String... parameterNames)
			throws DemandingParameterNotPresentException {
		for (String name : parameterNames) {
			if (!parameters.containsKey(name)) {
				throw new DemandingParameterNotPresentException(name);
			}
		}
	}
	
	/**
	 * Get a parameter from parameters map as a double
	 * @param name
	 * @return
	 */
	protected double getParameterAsDouble(String name) {
		return Double.parseDouble(parameters.get(name));
	}

	
	/**
	 * Get a parameter from parameters map as a string
	 * @param name
	 * @return
	 */
	protected String getParameterAsString(String name) {
		return parameters.get(name);
	}

	
	/**
	 * Get a parameter from parameters map as a int
	 * @param name
	 * @return
	 */
	protected int getParameterAsInt(String name) {
		return Integer.parseInt(parameters.get(name));
	}

}
