package fhj.shelf.output;

import java.lang.reflect.Method;
import java.util.Map;


/**
 * This class defines the accept command
 * 
 * @author Filipa Estiveira, Hugo Leal, José Oliveira
 */
public class Accept implements ParameterDecisionMarker {

	protected final Map<String, String> parameters;
	String key;

	/**
	 * This is the constructor for the class
	 * 
	 * @param parameters
	 *            is an instance of Map<String, String>
	 * @param key
	 *            is an instance of String
	 */
	public Accept(Map<String, String> parameters, String key) {
		this.parameters = parameters;
		this.key = key;
		// execute(parameters);
	}

	/**
	 * This is an override method of the base class that executes the parameters
	 * @throws  
	 */
	@Override
	public String execute(StackMensage stackMensage,Map<String, String> parameters) {

		String methodNameToCreateElement = key + "Parser";
		StrategyFormatter p = null;
		Class<? extends Accept> c = this.getClass();

		Method creatorMethodToCreate;
		try {
			creatorMethodToCreate = c
					.getDeclaredMethod(methodNameToCreateElement);
			p = (StrategyFormatter) creatorMethodToCreate.invoke(this);
		} catch (Exception e) {

		}

		TextFormatterExecuter<StrategyFormatter> context = new TextFormatterExecuter<StrategyFormatter>(
				p);

		stackMensage.push(context.executeStrategy(parameters));
		
		return context.executeStrategy(parameters);

	}
	
	

	@SuppressWarnings("unused")
	private StrategyFormatter JsonParser() {

		StringBuilder builder = new StringBuilder();

		StrategyFormatter strategy = new JsonBuilder(builder);

		return strategy;
	}

	@SuppressWarnings("unused")
	private StrategyFormatter HtmlParser() {
		StringBuilder builder = new StringBuilder();

		StrategyFormatter strategy = new HtmlBuilder(builder);

		return strategy;
	}

	@SuppressWarnings("unused")
	private StrategyFormatter PlainParser() {

		StringBuilder builder = new StringBuilder();

		StrategyFormatter strategy = new PlainBuilder(builder);
		return strategy;
	}

}
