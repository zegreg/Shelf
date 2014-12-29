package main.java.FHJ.shelf.commands;

import java.util.Map;

import main.java.FHJ.shelf.commands.exceptions.CommandException;
import main.java.FHJ.shelf.model.repos.UserRepository;
import main.java.FHJ.shelf.output.CreateFactory;

public abstract class BaseGetCommand extends BaseCommand {
	
		

	public static String ACCEPT = "accept";

	//public static final String OUTPUT = "output";

	public static final String[] OPTIONAL_PARAMETERS = new String[] {ACCEPT};

	public BaseGetCommand(  Map<String, String> parameters)
	{
		super(parameters);
		

	}

	
		@Override
		protected void internalExecute() throws CommandException {
			validateDemandingParameters(ACCEPT);
					
			
			String textFormat = getParameterAsString(ACCEPT);
			
			//String destination = getParameterAsString(OUTPUT);
			
			ExecuteUser(textFormat);
			
			
		
		}
		
		abstract protected void ExecuteUser(String format) throws CommandException;
		
		
		@Override
		protected String[] getMandatoryParameters() {
			return OPTIONAL_PARAMETERS ;
		}

		

	}

