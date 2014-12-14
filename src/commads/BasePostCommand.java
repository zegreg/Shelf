package commads;

import java.util.Map;

import User.UserRepository;
import exceptions.CommandException;

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
	protected String[] getDemandingParametres() {
		return DEMANDING_PARAMETERS;
	}

	@Override
	protected void internalExecute() throws CommandException {
		validateDemandingParameters(LOGINNAME, LOGINPASSWORD);
		String username = parameters.get(LOGINNAME);
		String password = parameters.get(LOGINPASSWORD);
		if(verifyLogin(username, password ))
		{
			validLoginPostExecute();
		}
		
	}
	
	abstract protected void validLoginPostExecute();

	public boolean verifyLogin(String loginName, String loginPassword)
	{
		return userRepo.validatePassword(loginName, loginPassword);
				
	}
	

}
