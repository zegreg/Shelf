package fhj.shelf.server;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;

import fhj.shelf.CommandParser;
import fhj.shelf.http.ServerCommands;
import fhj.shelf.http.ServerRegisterCommandsException;
import fhj.shelf.http.ShelfManagerServlet;
import fhj.shelf.inMemoryRepositories.ElementsRepository;
import fhj.shelf.inMemoryRepositories.InMemoryElementsRepository;
import fhj.shelf.inMemoryRepositories.InMemoryShelfRepository;
import fhj.shelf.inMemoryRepositories.InMemoryUserRepository;
import fhj.shelf.inMemoryRepositories.ShelfRepository;
import fhj.shelf.repositories.UserRepository;

public class ShelfManagerServerApp {

	private static final int LISTEN_PORT = 8081;

	private ShelfManagerServerApp() {

	}

	private static void initializeDatabase() {

		CommandParser parser = CommandParser.getInstance();
		ShelfRepository shelfRepo = new InMemoryShelfRepository();
		ElementsRepository elementsRepo = new InMemoryElementsRepository();
		UserRepository userRepo = new InMemoryUserRepository();

		ServerCommands commands = new ServerCommands();
		try {
			commands.registerServerCommands(parser, shelfRepo, elementsRepo,
					userRepo);
		} catch (ServerRegisterCommandsException e) {
			
		}
	}

	public static void main(String[] args) throws Exception {

		initializeDatabase();

		Server server = new Server(LISTEN_PORT);
		ServletHandler handler = new ServletHandler();
		server.setHandler(handler);

		handler.addServletWithMapping(ShelfManagerServlet.class, "/*");
		server.start();
		System.out.println("Server is started");

		System.in.read();
		server.stop();
		System.out.println("Server is stopped, bye");
	}

}


