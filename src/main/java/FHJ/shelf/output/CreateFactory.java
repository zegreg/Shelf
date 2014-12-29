package main.java.FHJ.shelf.output;

import java.util.Map;


import main.java.FHJ.shelf.model.repos.InMemoryUserRepository;

import main.java.FHJ.shelf.model.repos.UserRepository;

public class CreateFactory {

	InMemoryUserRepository userParameters;
	Map<String, String> parameters;
	public CreateFactory(UserRepository userRepository,Map<String, String> parameters ) {
		this.userParameters =(InMemoryUserRepository) userRepository;
		
	}
	

	public OptionalCommand getCommand( String format, String destination)
	{
		if(format == null|| destination == null)
		{
			return null;
		}		

		if (format.equalsIgnoreCase ("txt/plain") )
		{
			return new Accept(userParameters, "Plain");
			
		}
		else if(format.equalsIgnoreCase ("txt/html"))
		{
			return new Accept(userParameters, "Html");
			
		}
		else if(format.equalsIgnoreCase("aplication/json"))
		{
			return new Accept(userParameters, "Json");	
		}


		if(destination.equalsIgnoreCase("output"))
		{
			//	         return new Output(parameters);
			System.out.println("outuput");
		} 
		return null;
	}
}
