package commads;


import java.util.Map;


import User.User;
import User.UserRepository;
import exceptions.CommandException;


public class PostUser extends BasePostCommand implements Command {


	/**
	 * Class that implements the {@link GetProducts} factory, according to the 
	 * AbstratFactory design pattern. 
	 */
	public static class Factory implements CommandFactory {

		private final UserRepository repository;

		public Factory(UserRepository productRepo)
		{
			this.repository = productRepo;
		}

		@Override
		public Command newInstance(Map<String, String> parameters) 
		{
			return new PostUser(repository, parameters);
		}

	}

	public static final String USERNAME = "username";

	public static final String PASSWORD = "password";

	public static final String EMAIL = "email";

	public static final String FULLNAME = "fullname";


	public final UserRepository userRepository;


	private static final String[] DEMANDING_PARAMETERS = {USERNAME,PASSWORD,EMAIL,FULLNAME};

	/**
	 * 
	 * @param repository
	 * @param id
	 */
	private PostUser(UserRepository repository, Map<String, String> parameters)
	{
		super(repository, parameters);
		this.userRepository = repository;

	}


	private User createUser(String username, String password, String email, String fullname) {

		return new User (username,password,email,fullname);
	}

	@Override
	protected String[] getDemandingParametres() {

		return DEMANDING_PARAMETERS;
	}

	@Override
	protected void validLoginPostExecute() throws CommandException {
	
		String username = parameters.get(USERNAME);
		String password = parameters.get(PASSWORD);
		String email = parameters.get(EMAIL);
		String fullname = parameters.get(FULLNAME);
			
		User p = createUser(username, password, email, fullname);
		
		userRepository.insert(p);
	
			System.out.println("User Added To Database" + "\n" + p.toString());
	
		
	}
}
