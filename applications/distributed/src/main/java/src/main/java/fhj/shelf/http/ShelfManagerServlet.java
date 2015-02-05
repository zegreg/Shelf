package src.main.java.fhj.shelf.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Map.Entry;
import java.util.concurrent.ExecutionException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.SwingWorker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

import fhj.shelf.commandParser.CommandParser;
import fhj.shelf.commandParser.DuplicateArgumentsException;
import fhj.shelf.commandParser.InvalidCommandArgumentsException;
import fhj.shelf.commandParser.InvalidRegisterException;
import fhj.shelf.commandParser.UnknownCommandException;
import fhj.shelf.commands.GetUser;
import fhj.shelf.commands.GetUsers;
import fhj.shelf.commands.PostUser;
import fhj.shelf.commands.exceptions.CommandException;
import fhj.shelf.output.StackMensage;
import fhj.shelf.repos.AbstractUser;
import fhj.shelf.repos.InMemoryUserRepository;
import fhj.shelf.repos.User;
import fhj.shelf.repos.UserRepository;

@SuppressWarnings("serial")
public class ShelfManagerServlet extends HttpServlet {
	
	
	StackMensage stackMensage = new StackMensage(10);
	
	UserRepository userRepo = new InMemoryUserRepository();


	private final static Logger LOGGER = LoggerFactory.getLogger(ShelfManagerServlet.class);
	
	
	public ShelfManagerServlet() {

	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
		
		CommandParser parser = new CommandParser();
		String input = getCommandStringFromRequest(req);
		startParser(parser, input);

	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		CommandParser parser = new CommandParser();
		String input = getCommandStringFromRequest(req);
		startParser(parser, input);
//		StackMensage mensage = startParser(parser, input);
		
		
	
	DataObject ob= new DataObject();
			Gson gson = new Gson();
		  String mensage = gson.toJson(ob);
	
			
		
		
		resp.setContentType("application/json");
		PrintWriter out;
		out = resp.getWriter();
		out.print(mensage);
		out.flush();

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


		
		
	}

		



	

	public StackMensage startParser(CommandParser parser, String input) {


		getCommandParser(userRepo, parser);

		System.out.println(input);

		User admin = new User("Lima", "SLB", "OMAIOREMail", "Lima");
		userRepo.add(admin);

		do {


			try {

				parser.getCommand(input.split(" ")).execute(stackMensage);
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

			return stackMensage;

		} while (true);
	}

	/**
	 * Gets the singleton {@link CommandParser} created and configured in the
	 * application
	 * 
	 * @return the application {@link CommandParser}
	 */
	private void getCommandParser(UserRepository userRepo, CommandParser parser) {
		try {
			parser.registerCommand("POST",
					new StringBuilder("/users/").toString(),
					new PostUser.Factory(userRepo));

			parser.registerCommand("GET",
					new StringBuilder("/users").toString(),
					new GetUsers.Factory(userRepo));

			parser.registerCommand(
					"GET",
					new StringBuilder("/users/{").append(GetUser.USERNAME)
							.append("}").toString(), new GetUser.Factory(userRepo));
			
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
	private String getCommandStringFromRequest(HttpServletRequest req) {
		StringBuilder out = new StringBuilder();
		InputStream input = null;
		try {
			input = req.getInputStream();

			BufferedReader br = new BufferedReader(new InputStreamReader(input));
			for (String line = br.readLine(); line != null; line = br
					.readLine())
				out.append(line);
			br.close();

		} catch (IOException e) {
			LOGGER.error( "FailedCreateActivityFunction Exception Occured : " ,e );
		}

		return out.toString();
	}

}
