package main.java.FHJ.shelf.commands;

import java.util.Map;

import main.java.FHJ.shelf.commands.exceptions.CommandException;
import main.java.FHJ.shelf.commands.exceptions.OptionalParameterNotPresentException;
import main.java.FHJ.shelf.model.repos.UserRepository;
import main.java.FHJ.shelf.output.OutputPrinter;

public abstract class BasePostCommand extends BaseCommand{
	
	
	/**
     * Holds the shelf repository to be used by the command
     */
	private final UserRepository userRepo;
	
	/**
	 * demaning parameter
	 */
	public static final String LOGINNAME = "loginName";
	
	
	/**
	 * demaning parameter
	 */
	public static final String LOGINPASSWORD = "loginPassword";
	
			
	
	/**
     * The array containing all the demanding parameters of this command
     */
	public static final String[] DEMANDING_PARAMETERS = {LOGINNAME, LOGINPASSWORD};
	
	
	
	 /**
     * Initiates an instance with the given user repository and a command parameters
     * @param parameters the command's unparsed parameters
     */
	public BasePostCommand(UserRepository userRepo, Map<String, String> parameters) {
		super(parameters);
		this.userRepo = userRepo;
	}

	
	/**
	 * Gets the array bearing the names of the demanding command's parameters
	 * 
	 * @return The array containing the names of the mandatory parameters
	 */
	@Override
	protected String[] getMandatoryParameters() {
		return DEMANDING_PARAMETERS;
	}

	/**
	 * Performs the actual execution of the command, producing the corresponding result
	 * 
	 * @throws CommandException if the command's execution preconditions are not met, e.g. a
     * parameter has an invalid value
	 */
	@Override
	protected void internalExecute() throws CommandException {
		validateDemandingParameters(LOGINNAME, LOGINPASSWORD);
		String username = parameters.get(LOGINNAME);
		String password = parameters.get(LOGINPASSWORD);
		if(verifyLogin(username, password ))
		{
			String commandResult = validLoginPostExecute();
			
			OutputPrinter printer = new OutputPrinter(commandResult);
			printer.printResult(null);
		}
		else 
			throw new CommandException();
	}
	
	
	/**
	 * Method used to perform validation of mandatory parameters. The 
	 *  
	 * @param parameterNames The parameter set to be validated
	 * @throws MandatoryParameterNotPresentException if a mandatory parameter is not present
	 */
	abstract protected String validLoginPostExecute() throws CommandException;

	public boolean verifyLogin(String loginName, String loginPassword)
	{
		return userRepo.validatePassword(loginName, loginPassword);
				
	}
	

}
