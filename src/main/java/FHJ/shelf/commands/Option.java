package main.java.FHJ.shelf.commands;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

import main.java.FHJ.shelf.commands.exceptions.CommandException;
import main.java.FHJ.shelf.model.repos.UserInterface;
import main.java.FHJ.shelf.model.repos.UserRepository;

public class Option extends BaseGetCommand implements Command {

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

	protected Map<String, String> putCommandResultInAMap(
			Map<String, UserInterface> map) {
		Map<String, String> tmp = new TreeMap<String, String>();

		for (Entry<String, UserInterface> entry : map.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue().toString();
			tmp.put(key, value);
		}

		return tmp;
	}

	@Override
	protected String[] getMandatoryParameters() {
		return DEMANDING_PARAMETERS;
	}
}
