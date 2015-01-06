package main.java.FHJ.shelf.commands;

import java.util.Map;

import main.java.FHJ.shelf.commands.exceptions.CommandException;
import main.java.FHJ.shelf.model.repos.UserRepository;
import main.java.FHJ.shelf.output.OutputPrinter;

public abstract class BasePostCommand extends BaseCommand{

	public static final String LOGINNAME = "loginName";
	
	public static final String LOGINPASSWORD = "loginPassword";
	
	private final UserRepository userRepo;	
	
	public static final String[] DEMANDING_PARAMETERS = {LOGINNAME, LOGINPASSWORD};
	
	public BasePostCommand(UserRepository userRepo, Map<String, String> parameters) {
		super(parameters);
		this.userRepo = userRepo;
	}

	@Override
	protected String[] getMandatoryParameters() {
		return DEMANDING_PARAMETERS;
	}

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
	
	abstract protected String validLoginPostExecute() throws CommandException;

	public boolean verifyLogin(String loginName, String loginPassword)
	{
		return userRepo.validatePassword(loginName, loginPassword);
				
	}
	

}
