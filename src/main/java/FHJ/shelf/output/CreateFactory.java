package main.java.FHJ.shelf.output;

import java.util.Map;


import main.java.FHJ.shelf.model.repos.InMemoryUserRepository;

import main.java.FHJ.shelf.model.repos.UserRepository;

public class CreateFactory {

	
	Map<String, String> parameters;
	public CreateFactory(Map<String, String> parameters ) {
		this.parameters = parameters;
		
	}
	

	public OptionalCommand getCommand( String format, String destination)
	{
		if(format == null|| destination == null)
		{
			return null;
		}		

		if (format.equalsIgnoreCase ("txt/plain") )
		{
			return new Accept(parameters, "Plain");
			
		}
		else if(format.equalsIgnoreCase ("txt/html"))
		{
			return new Accept(parameters, "Html");
			
		}
		else if(format.equalsIgnoreCase("aplication/json"))
		{
			return new Accept(parameters, "Json");	
		}


		if(destination.equalsIgnoreCase("output"))
		{
			//	         return new Output(parameters);
			System.out.println("outuput");
		} 
		return null;
	}
}
