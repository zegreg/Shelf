package main.java.FHJ.shelf.commands;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

import main.java.FHJ.shelf.commands.exceptions.CommandException;
import main.java.FHJ.shelf.model.repos.UserRepository;

/**
 * This Class defines the userguide of the app
 * 
 *@author Filipa Estiveira, Hugo Leal, José Oliveira
 */
public class Option extends BaseGetCommand implements Command {

	/**
	 * Class that implements a dummy factory, according to the 
	 * AbstratFactory design pattern. This command doesn't need a repository
	 */
	public static class Factory implements CommandFactory {

		private final UserRepository repository;

		public Factory(UserRepository repository) {
			this.repository = repository;

		}

		@Override
		public Command newInstance(Map<String, String> parameters) {
			return new Option(repository, parameters);
		}

	}

	@SuppressWarnings("unused")
	private final UserRepository userRepository;

	private static final String[] DEMANDING_PARAMETERS = {};

	public Option(UserRepository repository, Map<String, String> parameters) {
		super(parameters);
		this.userRepository = repository;
	}

	@Override
	protected Map<String, String> actionExecute() throws CommandException {
		String source = "src/main/java/FHJ/shelf/app/ShelfUserGuide.txt";
		try (BufferedReader reader = new BufferedReader(new FileReader(source))) {
			String nextLine = reader.readLine();
			String userguide = "";
			while (nextLine != null) {
				userguide += nextLine + "\n";
				nextLine = reader.readLine();
			}
			reader.close();

			Map<String, String> userguideMap = new TreeMap<String, String>();
			userguideMap.put("USERGUIDE", userguide);

			return userguideMap;

		} catch (FileNotFoundException e) {
			System.out.println(source + " not found or is inaccessible");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println(" Fail reading" + source);
			e.printStackTrace();
		}
		return null;
	}


	@Override
	protected String[] getMandatoryParameters() {
		return DEMANDING_PARAMETERS;
	}
}
