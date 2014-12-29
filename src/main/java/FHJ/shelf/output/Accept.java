package main.java.FHJ.shelf.output;


import java.lang.reflect.Method;
import java.util.Map;
import java.util.TreeMap;

import main.java.FHJ.shelf.model.AbstractElement;
import main.java.FHJ.shelf.model.repos.InMemoryUserRepository;



public class Accept implements OptionalCommand {
	
	protected final InMemoryUserRepository parameters;
	protected final Map<String, String> para;
	String key;
	
	
	public Accept(InMemoryUserRepository parameters, String key) {
		this.parameters =parameters;
		this.key = key;
		para = new TreeMap<String, String>();
		execute(parameters);
	}
	
	
	
	@Override
	public void execute(InMemoryUserRepository parameters2){
	
		String methodNameToCreateElement =  key + "Parser";
		Strategy p = null;
		Class<? extends Accept> c = this.getClass();

		Method creatorMethodToCreate;
		try {
			creatorMethodToCreate = c.getDeclaredMethod(
					methodNameToCreateElement, String.class);
			p =(Strategy) creatorMethodToCreate.invoke(this);
		} catch (Exception e) {
			
		}
		

		Context<Strategy> context = new Context<Strategy>(p);
		
		
		// Aqui o argumento devia ser um map, mas estou a enviar o repositório ?????
		context.executeStrategy(parameters.getUsers());
		
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
	
	
	private Strategy JsonParser(){
		
		StringBuilder builder = new StringBuilder();

		Strategy strategy = new JsonCompositor(builder);


		return strategy;
	}

	private Strategy HtmlParser(){
		StringBuilder builder = new StringBuilder();

		Strategy strategy = new HtmlCompositor(builder);


		return strategy;
	}
	
	private Strategy PlainParser(){

		StringBuilder builder = new StringBuilder();

		Strategy strategy = new PlainCompositor(builder);
		return strategy;


	}
	
	
	
}
