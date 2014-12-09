package commads;

/**
 * Contract to be supported by all commands. Instances cannot be executed multiple times.
 */
public interface Command {
	
	void execute();
}
