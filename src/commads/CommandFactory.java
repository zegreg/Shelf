package commads;

import java.util.Map;


/**
 * Contract to be supported by all {@link commads.Command}
 * factories. 
 */
public interface CommandFactory {
	
	public Command newInstance(Map<String,String> parameters);
}
