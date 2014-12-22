package main.java.FHJ.shelf.output;


import java.lang.reflect.Method;
import java.util.Map;


public class Accept implements OptionalCommand {
	
	protected final Map<String, String> parameters;
	
	public Accept(Map<String, String> parameters) {
		this.parameters =parameters;
	}
	

	private static final String ELEMENT_TYPE = "element_type";
	
	
	private static final String[] OPTIONAL_PARAMETERS = {ELEMENT_TYPE};
	
	
	@Override
	public void execute(Map<String, String> parameters){
		
		String elementType = parameters.get(ELEMENT_TYPE);
	

		String methodNameToCreateElement = "create" + elementType;

		Class<? extends Accept> c = this.getClass();

		Method creatorMethodToCreate;
		try {
			creatorMethodToCreate = c.getDeclaredMethod(
					methodNameToCreateElement, String.class);
			creatorMethodToCreate.invoke(this, parameters);
		} catch (Exception e) {
			
		}
		
	}
	
	
	private void JsonParser(Map<String, String> parameters){
		
		StringBuilder builder = new StringBuilder();

		Strategy strategy = new JsonCompositor(builder);


		Context<Strategy> context = new Context<Strategy>(strategy);
		
		context.executeStrategy(parameters);
	}

	private void HtmlParser(Map<String, String> parameters){
		StringBuilder builder = new StringBuilder();

		Strategy strategy = new HtmlCompositor(builder);


		Context<Strategy> context = new Context<Strategy>(strategy);
		
		context.executeStrategy(parameters);
	}
	
	private void PlainParser(Map<String, String> parameters){

		StringBuilder builder = new StringBuilder();

		Strategy strategy = new PlainCompositor(builder);


		Context<Strategy> context = new Context<Strategy>(strategy);
		
		context.executeStrategy(parameters);
	}
	
	
	
}
