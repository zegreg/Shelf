package main.java.FHJ.shelf.output;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class Output{

	private String commandResult;
	
	public Output(String commandResult)
	{
		this.commandResult = commandResult;
	}

	public void printResult(String outputPath)
	{
		if(outputPath == null)
			printToConsole();
		else
			printToFile(outputPath);
	}
	
	public void printToConsole()
	{
		System.out.println(commandResult);
	}
	
	public void printToFile(String destination) {
				
	try{
		BufferedWriter writer = new BufferedWriter(new FileWriter(destination));
		
		writer.write(commandResult);
		
		writer.close();
	} catch (IOException e) {
		e.printStackTrace();
		}
		
	}




}
