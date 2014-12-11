package App;
import java.io.PrintStream;

/**
 * A poor log implementation. 
 */
public class Logger {

	private final PrintStream out;
	
	public Logger(PrintStream out)
	{
		this.out = out;
	}
	
	public void log(String msg)
	{
		out.println(msg);
	}
}
