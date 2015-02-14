package fhj.shelf.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.concurrent.ExecutionException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fhj.shelf.CommandParser;
import fhj.shelf.commands.DeleteShelfElement;
import fhj.shelf.commands.DeleteShelfs;
import fhj.shelf.commands.Exit;
import fhj.shelf.commands.GetShelf;
import fhj.shelf.commands.GetShelfElement;
import fhj.shelf.commands.GetShelfElements;
import fhj.shelf.commands.GetShelfs;
import fhj.shelf.commands.GetUser;
import fhj.shelf.commands.GetUsers;
import fhj.shelf.commands.Option;
import fhj.shelf.commands.PatchElement;
import fhj.shelf.commands.PatchUsers;
import fhj.shelf.commands.PostElement;
import fhj.shelf.commands.PostShelf;
import fhj.shelf.commands.PostShelfCollectionElement;
import fhj.shelf.commands.PostUser;
import fhj.shelf.exceptions.CommandException;
import fhj.shelf.exceptions.DuplicateArgumentsException;
import fhj.shelf.exceptions.InvalidCommandArgumentsException;
import fhj.shelf.exceptions.InvalidRegisterException;
import fhj.shelf.exceptions.UnknownCommandException;
import fhj.shelf.inMemoryRepositories.ElementsRepository;
import fhj.shelf.inMemoryRepositories.InMemoryElementsRepository;
import fhj.shelf.inMemoryRepositories.InMemoryShelfRepository;
import fhj.shelf.inMemoryRepositories.InMemoryUserRepository;
import fhj.shelf.inMemoryRepositories.ShelfRepository;
import fhj.shelf.repositories.User;
import fhj.shelf.repositories.UserRepository;



@SuppressWarnings("serial")
public class ShelfManagerServlet extends HttpServlet {
	
	//modelo de memória
	private static final ShelfRepository shelfRepo = new InMemoryShelfRepository();
	private static final ElementsRepository elementsRepo = new InMemoryElementsRepository();
	private static final UserRepository userRepo = new InMemoryUserRepository();
	private final static Logger LOGGER = LoggerFactory.getLogger(ShelfManagerServlet.class);


	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String method = req.getMethod();
		if(method.equals("PATCH")) {
			doPatch(req, resp);
			return;
		}
		super.service(req, resp);
	}
	
	public void doPatch(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		CommandParser parser = CommandParser.getInstance();
		String input = "PATCH"+getImputStreamReader(req);
		//		startParser(parser, input);
		String mensage = startParser(parser, input);
		System.out.println(mensage);

		//        resp.setContentType("application/json");
		PrintWriter out;
		out = resp.getWriter();
		out.print(mensage);
		out.flush();
		
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
		
		CommandParser parser = CommandParser.getInstance();
		String input = getOutputStreamReader(req);
		System.out.println(input);
		String message =startParser(parser, input);

		
		System.err.println(message);
		
		PrintWriter out;
		out = resp.getWriter();
		out.print(message);
		out.flush();
	}

	

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		CommandParser parser = CommandParser.getInstance();
		String input = req.getMethod()+getImputStreamReader(req);
		//		startParser(parser, input);
		String mensage = startParser(parser, input);
		System.out.println(mensage);

		//        resp.setContentType("application/json");
		PrintWriter out;
		out = resp.getWriter();
		out.print(mensage);
		out.flush();

	}
		
		

//			SwingWorker<StackMensage, Void> worker = new SwingWorker<StackMensage, Void>() {
//			@Override
//			protected StackMensage doInBackground() throws Exception {
//				
//				
//				return mensage;
//			}
//			@Override
//			protected void done() {
//				
//				try {
//					
//
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} catch (ExecutionException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//
//			}
//		};


		
		
	

		



	

	public String startParser(CommandParser parser, String input) {

        String result = null;
      
		getCommandParser(userRepo, shelfRepo, elementsRepo, parser);

		System.out.println(input);

		User admin = new User("Lima", "SLB", "OMAIOREMail", "Lima");
		userRepo.add(admin);

		do {


			try {

				result = parser.getCommand(input.split(" ")).execute();
			} catch (IllegalArgumentException e) {
				LOGGER.error( "FailedCreateActivityFunction Exception Occured : " ,e );

			} catch (UnknownCommandException e) {
				LOGGER.error( "FailedCreateActivityFunction Exception Occured : " ,e );

			} catch (DuplicateArgumentsException e) {
				LOGGER.error( "FailedCreateActivityFunction Exception Occured : " ,e );

			} catch (InvalidCommandArgumentsException e) {
				LOGGER.error( "FailedCreateActivityFunction Exception Occured : " ,e );

			} catch (CommandException e) {
				LOGGER.error( "FailedCreateActivityFunction Exception Occured : " ,e );

			} catch (ExecutionException e) {
				LOGGER.error( "FailedCreateActivityFunction Exception Occured : " ,e );

			}

			return result;

		} while (true);
	}

	/**
	 * Gets the singleton {@link CommandParser} created and configured in the
	 * application
	 * 
	 * @return the application {@link CommandParser}
	 */
	private void getCommandParser(UserRepository userRepo, ShelfRepository shelfRepo, ElementsRepository elementsRepo, CommandParser parser) {
		try {
			parser.registerCommand("POST", new StringBuilder("/users/").toString(),
					new PostUser.Factory(userRepo));

			parser.registerCommand("GET", new StringBuilder("/users").toString(),
					new GetUsers.Factory(userRepo));

			parser.registerCommand(
					"PATCH",
					new StringBuilder("/shelfs/{").append(PatchElement.SID)
							.append("}").append("/elements/{")
							.append(PatchElement.EID).append("}").toString(),
					new PatchElement.Factory(userRepo, shelfRepo, elementsRepo));

			parser.registerCommand(
					"GET",
					new StringBuilder("/users/{").append(GetUser.USERNAME)
							.append("}").toString(), new GetUser.Factory(userRepo));

			parser.registerCommand("POST",
					new StringBuilder("/shelfs/").toString(),
					new PostShelf.Factory(userRepo, shelfRepo));

			parser.registerCommand(
					"POST",
					new StringBuilder("/shelfs/{").append(PostElement.SID)
							.append("}").append("/elements/{")
							.append(PostElement.ELEMENT_TYPE).append("}")
							.toString(), new PostElement.Factory(userRepo,
							shelfRepo, elementsRepo));

			parser.registerCommand(
					"POST",
					new StringBuilder("/shelfs/{")
							.append(PostShelfCollectionElement.SID).append("}")
							.append("/elements/{")
							.append(PostShelfCollectionElement.ELEMENT_TYPE)
							.append("}/{").append(PostShelfCollectionElement.EID)
							.append("}").toString(),
					new PostShelfCollectionElement.Factory(userRepo, shelfRepo,
							elementsRepo));

			parser.registerCommand("GET",
					new StringBuilder("/shelfs/{").append(GetShelfElements.SID)
							.append("}").append("/elements").toString(),
					new GetShelfElements.Factory(shelfRepo));

			parser.registerCommand(
					"GET",
					new StringBuilder("/shelfs/{").append(GetShelfElement.SID)
							.append("}").append("/elements/{")
							.append(GetShelfElement.EID).append("}").toString(),
					new GetShelfElement.Factory(shelfRepo, elementsRepo));

			parser.registerCommand("GET",
					new StringBuilder("/shelfs/{").append(GetShelf.SID).append("}")
							.append("/details").toString(), new GetShelf.Factory(
							shelfRepo));

			parser.registerCommand("GET", new StringBuilder("/shelfs/").toString(),
					new GetShelfs.Factory(shelfRepo));

			parser.registerCommand(
					"DELETE",
					new StringBuilder("/shelfs/{").append(DeleteShelfs.SID)
							.append("}").toString(), new DeleteShelfs.Factory(
							userRepo, shelfRepo));

			parser.registerCommand("DELETE",
					new StringBuilder("/shelfs/{").append(DeleteShelfElement.SID)
							.append("}/elements/{").append(DeleteShelfElement.EID)
							.append("}").toString(),
					new DeleteShelfElement.Factory(userRepo, shelfRepo,
							elementsRepo));

			parser.registerCommand("PATCH",
					new StringBuilder("/users/{").append(PatchUsers.USERNAME)
							.append("}").toString(), new PatchUsers.Factory(
							userRepo));

			parser.registerCommand(
					"PATCH",
					new StringBuilder("/shelfs/{").append(PatchElement.SID)
							.append("}/elements/{").append(PatchElement.EID)
							.append("}").toString(), new PatchElement.Factory(
							userRepo, shelfRepo, elementsRepo));

			parser.registerCommand("OPTION", new StringBuilder("/").toString(),
					new Option.Factory(userRepo));

			parser.registerCommand("EXIT", new StringBuilder("/").toString(),
					new Exit.Factory(userRepo));
			
		} catch (InvalidRegisterException e) {
			LOGGER.error( "Invalid Register Command!: " ,e );
			
		}

	}

	/**
	 * returns a string with the command line request needed for command parser.
	 * This string is created based on {@link HttpServletRequest} data.
	 * 
	 * @param req
	 *            the {@link HttpServletRequest} corresponding to the current
	 *            HTTP request
	 * @return the string to send to {@link CommandParser}
	 */
	private String getImputStreamReader(HttpServletRequest req) {
		
		
		StringBuilder out = new StringBuilder();
//		String method = req.getMethod();
		String path = req.getQueryString();
		String header= req.getHeader("Accept");
		
		out.append(" ").append(path).append(" ");
		System.out.println("path"+ path);
		try {
			InputStream input = req.getInputStream();

			BufferedReader br = new BufferedReader(new InputStreamReader(input));
			for (String line = br.readLine(); line != null; line = br
					.readLine())
				out.append(line);
			br.close();

		} catch (IOException e) {
			LOGGER.error( "FailedCreateActivityFunction Exception Occured : " ,e );
		}

		out.append("&accept=").append(header);
		return out.toString();
	}


	private String getOutputStreamReader(HttpServletRequest req) {
		StringBuilder in = new StringBuilder();
		String method =req.getMethod();
		String pathInfo = req.getPathInfo();
		String params = req.getHeader("params");
		String queryString = req.getQueryString();
		String header= req.getHeader("Accept");
		String p = req.getContentType();
		return in.append(method).append(" ").append(queryString).append(" accept=").append(header).toString();

	}

}
