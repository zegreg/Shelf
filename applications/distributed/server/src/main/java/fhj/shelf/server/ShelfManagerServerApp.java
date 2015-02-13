package fhj.shelf.server;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;

import fhj.shelf.http.ShelfManagerServlet;
import fhj.shelf.inMemoryRepositories.ElementsRepository;
import fhj.shelf.inMemoryRepositories.ShelfRepository;
import fhj.shelf.repositories.UserRepository;


public class ShelfManagerServerApp {

	
	private static UserRepository userRepo;
	private static ShelfRepository shelfRepo;
	private static ElementsRepository elementsRepo;
	
	private ShelfManagerServerApp() {

	}

	private static final int LISTEN_PORT = 8081;

	public static void main(String[] args) throws Exception {

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
