package main.java.FHJ.shelf.commands;

import java.util.Map;

import main.java.FHJ.shelf.commands.exceptions.CommandException;
import main.java.FHJ.shelf.commands.exceptions.DemandingParameterNotPresentException;


public abstract class BaseCommand implements Command {

	/**
	 * The command arguments;
	 */
	protected final Map<String, String> parameters;
	
	
	public BaseCommand(Map<String, String> parameters) {
		super();
		this.parameters = parameters;
	}


	public final void execute() throws CommandException
	{	
		validateDemandingParameters(getDemandingParametres());
		internalExecute();
	}



	protected abstract String[] getDemandingParametres();


	abstract protected void internalExecute() throws CommandException;


	protected void validateDemandingParameters(String ...parameterNames) throws DemandingParameterNotPresentException {
				for (String name : parameterNames) {
					if(!parameters.containsKey(name)) {
						throw new DemandingParameterNotPresentException(name);
					}
				}
			}


	protected double getParameterAsDouble(String name) {
		return Double.parseDouble(parameters.get(name));
	}

	protected String getParameterAsString(String name) {
		return parameters.get(name);
	}
	
	protected int getParameterAsInt(String name) {
		return Integer.parseInt(parameters.get(name));
	}


}

