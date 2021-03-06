package fhj.shelf.output;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Class whose instances are responsible for printing commands execution results
 * to console or to a file
 * 
 * @author Filipa Estiveira, Hugo Leal, José Oliveira
 */
public class OutputPrinter {

	/*
	 * String with the result of a command execution
	 */
	private String commandResult;
	
	static Logger LOGGER = LoggerFactory.getLogger(OutputPrinter.class);

	/**
	 * Output constructs receives the string with the command result
	 * 
	 * @param commandResult
	 *            is a String that contains a command execution result
	 */
	public OutputPrinter(String commandResult) {
		this.commandResult = commandResult;
	}

	/**
	 * This method receive a String with the destination path to print the
	 * result in a file If outuputPath is null the result is printed to console
	 * 
	 * @param outputPath
	 */
	public void printResult(String outputPath) {
		if (outputPath == null)
			printToConsole();
		else
			printToFile(outputPath);
	}

	/**
	 * This method prints the command result to console
	 */
	public void printToConsole() {
		System.out.println(commandResult);
	}

	/**
	 * This method prints the command result to a file in the given path
	 * destination
	 * 
	 * @param destination
	 *            path
	 */
	public void printToFile(String destination) {

		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(
					destination));

			writer.write(commandResult);

			writer.close();
		} catch (IOException e) {
			LOGGER.error("Fail to write command result into file", e);
		}

	}

}
