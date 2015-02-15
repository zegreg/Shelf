//package fhj.shelf.commands;
//
//import java.util.Map;
//import java.util.concurrent.ExecutionException;
//
//import fhj.shelf.exceptions.CommandException;
//import fhj.shelf.repositories.UserRepository;
//
//public class UserAuthentication extends BaseAuthenticationCommand{
//	/**
//	 * Class that implements the {@link PostUser} factory, according to the
//	 * AbstratFactory design pattern.
//	 */
//	public static class Factory implements CommandFactory {
//
//		/**
//		 * Holds the user repository to be used by this command factory
//		 */
//		private final UserRepository repository;
//
//		public Factory(UserRepository productRepo) {
//			this.repository = productRepo;
//		}
//
//		/**
//		 * This is an override method of the base class, it returns a new
//		 * instance of PostUser
//		 */
//		@Override
//		public Command newInstance(Map<String, String> parameters) {
//			return new UserAuthentication(repository, parameters);
//		}
//	}
//	
//	
//	
//	
//	/**
//	 * {@code String} with the {@code User} Login Name argument's name.
//	 */
//	private static final String USERNAME = "loginName";
//
//	/**
//	 * {@code String} with the {@code User} Login Password argument's name.
//	 */
//	private static final String PASSWORD = "loginPassword";
//
//	/**
//	 * An array of {@code String}s with the names of all mandatory arguments.
//	 */
//	private static final String[] DEMANDING_PARAMETERS = {USERNAME, PASSWORD};
//
//	/**
//	 * The {@link UserRepository} with the {@code User}s.
//	 */
//	private final UserRepository repository;
//	
//	
//	
//	
//	
//	public UserAuthentication(UserRepository repository,Map<String, String> parameters) {
//		super(repository, parameters);
//		this.repository = repository;
//		
//	}
//
//	
//	
//	
//	
//	@Override
//	protected String[] getMandatoryParameters() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//
//
//
//
//	@Override
//	protected String validLoginPostExecute() throws CommandException,
//			IllegalArgumentException, ExecutionException {
//		
//		String username = parameters.get(USERNAME);
//		String password = parameters.get(PASSWORD);
//		
//
//		try {
//			return new fhj.shelf.commandsDomain.CreateUser(repository,
//					username, password,admin, admin).call();
//
//		} catch (Exception cause) {
//			throw new ExecutionException(cause);
//		}
//		
//		return null;
//	}
//
//}
