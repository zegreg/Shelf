package main.java.FHJ.shelf.output;


import java.lang.reflect.Method;
import java.util.Map;
import java.util.TreeMap;

import main.java.FHJ.shelf.model.AbstractElement;
import main.java.FHJ.shelf.model.repos.InMemoryUserRepository;



public class Accept implements ParameterDecisionMarker {
	
	
	protected final Map<String, String> parameters;
	String key;
	
	
	public Accept(Map<String, String> parameters, String key) {
		this.parameters =parameters;
		this.key = key;
		//execute(parameters);
	}
	
	@Override
	public String execute(Map<String, String> parameters){
	
		String methodNameToCreateElement =  key + "Parser";
		StrategyFormatter p = null;
		Class<? extends Accept> c = this.getClass();

		Method creatorMethodToCreate;
		try {
			creatorMethodToCreate = c.getDeclaredMethod(
					methodNameToCreateElement);
			p =(StrategyFormatter) creatorMethodToCreate.invoke(this);
		} catch (Exception e) {
			
		}
		

		TextFormatterExecuter<StrategyFormatter> context = new TextFormatterExecuter<StrategyFormatter>(p);
		
		
		// Aqui o argumento devia ser um map, mas estou a enviar o reposit�rio ?????
		return context.executeStrategy( parameters);
		
	}
	

//	protected Map<String, String> getParameterAsMap() {
//		Map<String, UserInterface > pa = parameters.getUsers();
//		
//		for(Map.Entry<String, UserInterface> e : pa.entrySet())
//			  if(!para.containsKey(e.getKey())){
//			    para.put(e.getKey(), e.getValue().toString());
//			  }
//			
//
//		return para;
//	}
	
	
	private StrategyFormatter JsonParser(){
		
		StringBuilder builder = new StringBuilder();

		StrategyFormatter strategy = new JsonBuilder(builder);


		return strategy;
	}

	private StrategyFormatter HtmlParser(){
		StringBuilder builder = new StringBuilder();

		StrategyFormatter strategy = new HtmlBuilder(builder);


		return strategy;
	}
	
	private StrategyFormatter PlainParser(){

		StringBuilder builder = new StringBuilder();

		StrategyFormatter strategy = new PlainBuilder(builder);
		return strategy;


	}
	
	
	
}
