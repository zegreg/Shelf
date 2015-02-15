package fhj.shelf.commands;

import java.util.Map;
import java.util.concurrent.ExecutionException;

import fhj.shelf.exceptions.CommandException;
import fhj.shelf.exceptions.InvalidAcceptParameterException;
import fhj.shelf.output.AcceptParserManager;
import fhj.shelf.repositories.UserRepository;


/**
 * This class is the abstraction for the base get command, it defines how the base 
 * get commands are implemented.
 * 
 * @authors Filipa Estiveira, Hugo Leal, José Oliveira
 */
public abstract class BaseAuthenticationCommand extends BaseCommand {


	/**
	 * Holds the shelf repository to be used by the command
	 */
	private final UserRepository userRepo;


	/**
	 * {@code String} with the Login Name argument's name. This argument is used
	 * for {@code User}'s authentication.
	 */
	public static final String LOGINNAME = "loginName";

	/**
	 * {@code String} with the Login Password argument's name. This argument is
	 * used for {@code User}'s authentication.
	 */
	public static final String LOGINPASSWORD = "loginPassword";

	/**
	 * An array of {@code String}s with the names of all mandatory arguments.
	 */
	public static final String[] DEMANDING_PARAMETERS = new String[] {
		LOGINNAME, LOGINPASSWORD };

/**
	 * Initiates an instance with the given a command parameters
	 * @param parameters the command's unparsed parameters
	 * @return 
	 */
	public BaseAuthenticationCommand(UserRepository userRepo,Map<String, String> parameters) {
		super(parameters);
		this.userRepo = userRepo;
	}



	@Override
	protected String internalExecute() throws CommandException,
	IllegalArgumentException, ExecutionException {
		validateMandatoryParameters(LOGINNAME, LOGINPASSWORD);
		String username = parameters.get(LOGINNAME);
		String password = parameters.get(LOGINPASSWORD);
		if (verifyLogin(username, password)) {
			String commandResult = validLoginPostExecute();

			return commandResult;
			/*   
				OutputPrinter printer = new OutputPrinter(commandResult);
				printer.printResult(null);
			 */
		} else
			throw new CommandException();
	}

	/**
	 * Method used to perform validation of mandatory parameters. The
	 * 
	 * @param parameterNames
	 *            The parameter set to be validated
	 * @throws ExecutionException 
	 * @throws IllegalArgumentException 
	 * @throws ElementNotAddedToShelfException
	 * @throws MandatoryParameterNotPresentException
	 *             if a mandatory parameter is not present
	 */
	abstract protected String validLoginPostExecute() throws CommandException, IllegalArgumentException, ExecutionException;

	public boolean verifyLogin(String loginName, String loginPassword) {
		return userRepo.validatePassword(loginName, loginPassword);
	}
}
