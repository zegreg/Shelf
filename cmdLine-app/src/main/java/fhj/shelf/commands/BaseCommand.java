package fhj.shelf.commands;

import java.util.Map;
import java.util.concurrent.ExecutionException;

import fhj.shelf.commands.exceptions.CommandException;
import fhj.shelf.commands.exceptions.DemandingParameterNotPresentException;
import fhj.shelf.commands.exceptions.InvalidAcceptParameterException;

/**
 * This class is the abstraction for the base commands, it defines how the base
 * commands are implemented.
 *
 * @authors Hugo Leal, José Oliveira, Filipa Estiveira
 */
public abstract class BaseCommand implements Command {

	/**
	 * The command arguments;
	 */
	protected final Map<String, String> parameters;

	/**
	 * Initiates an instance with the given command parameters
	 * 
	 * @param parameters
	 *            the command's unparsed parameters
	 */
	public BaseCommand(Map<String, String> parameters) {
		super();
		this.parameters = parameters;
	}

	/**
	 * Performs the execution order command :firstly validate demanding
	 * parameter and then call internalExecute() method
	 * @throws ExecutionException 
	 * 
	 * @throws ElementNotAddedToShelfException
	 */
	public final void execute() throws CommandException,

	InvalidAcceptParameterException, IllegalArgumentException, ExecutionException {
		validateMandatoryParameters(getMandatoryParameters());
		internalExecute();
	}

	/**
	 * Gets the array bearing the names of the demanding command's parameters
	 * 
	 * @return The array containing the names of the mandatory parameters
	 */
	protected abstract String[] getMandatoryParameters();

	/**
	 * Performs the actual execution of the command, producing the corresponding
	 * result
	 * 
	 * @throws OptionalParameterNotPresentException
	 * @throws CommandException
	 *             if the command's execution preconditions are not met, e.g. a
	 *             parameter has an invalid value
	 * @throws ExecutionException 
	 * @throws ElementNotAddedToShelfException
	 */
	abstract protected void internalExecute() throws CommandException, ExecutionException;

	/**
	 * Method used to perform validation of mandatory parameters. The
	 * 
	 * @param parameterNames
	 *            The parameter set to be validated
	 * @throws MandatoryParameterNotPresentException
	 *             if a mandatory parameter is not present
	 */
	protected void validateMandatoryParameters(String... parameterNames)
			throws DemandingParameterNotPresentException {
		for (String name : parameterNames) {
			if (!parameters.containsKey(name)) {
				throw new DemandingParameterNotPresentException(name);
			}
		}
	}

	/**
	 * Get a parameter from parameters map as a double
	 * 
	 * @param name
	 * @return
	 */
	protected double getParameterAsDouble(String name) {
		return Double.parseDouble(parameters.get(name));
	}

	/**
	 * Get a parameter from parameters map as a string
	 * 
	 * @param name
	 * @return
	 */
	protected String getParameterAsString(String name) {
		return parameters.get(name);
	}

	/**
	 * Get a parameter from parameters map as a int
	 * 
	 * @param name
	 * @return
	 */
	protected int getParameterAsInt(String name) {
		return Integer.parseInt(parameters.get(name));
	}
	
	/**
	 * Get a parameter from parameters map as a long
	 * @param name
	 * @return
	 */
	protected long getParameterAsLong(String name) {
		return Long.parseLong(parameters.get(name));
	}

}
