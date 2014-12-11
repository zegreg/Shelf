package commads;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Map;




import exceptions.CommandException;
import App.Logger;
import Database.Repository;



/**
 * Class that implements command execution interception making use of reflection.
 * The implementation is based on the Decorator design pattern.
 * 
 * TODO: Implementation notes
 */
public class IntrospectiveLoggingInterceptor implements Command {
	/**
	 * Class that implements the {@link IntrospectiveLoggingInterceptor} factory, according to the 
	 * AbstratFactory design pattern. 
	 */
	public static class Factory implements CommandFactory {

		
		
		private Logger logger;
		private CommandFactory realCommandFactory;

		public Factory(Logger logger, CommandFactory realCommandFactory)
		{
			this.realCommandFactory = realCommandFactory;
			this.logger = logger;
			
		}
		
		@Override
		public Command newInstance(Map<String, String> parameters) 
		{
			return new IntrospectiveLoggingInterceptor(logger, realCommandFactory.newInstance(parameters));
		}
	}

	
	
	/**
	 * 
	 */
	private final Command command;
	
	/**
	 * 
	 */
	private final Logger logger;
	
	/**
	 * 
	 * @param command
	 */
	public IntrospectiveLoggingInterceptor(Logger logger, Command command) 
	{
		this.logger = logger;
		this.command = command;
	}
	
	/**
	 * @throws CommandException 
	 * @see formjava.module2.travelAgency.commands.Command#execute()
	 */
	@Override
	public void execute() throws CommandException 
	{
		Class<?> commandClass = command.getClass();
		StringBuilder msg = new StringBuilder("Executing ")
			.append(commandClass.getSimpleName())
			.append(":");
		Field[] commandFields = commandClass.getDeclaredFields();
		try {
			for(Field field : commandFields)
			{
				if(!Repository.class.isAssignableFrom(field.getType()) && !Modifier.isStatic(field.getModifiers()))
				{
						field.setAccessible(true);
						msg
							.append(field.getName())
							.append("=")
							.append(field.get(command).toString())
							.append(";");
				}
			}
			logger.log(msg.toString());
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			logger.log(e.toString());
		}
		
		command.execute() ;
	}
}


