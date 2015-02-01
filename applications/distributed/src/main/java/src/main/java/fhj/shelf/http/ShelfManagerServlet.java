package src.main.java.fhj.shelf.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fhj.shelf.commandParser.CommandParser;
import fhj.shelf.commandParser.DuplicateArgumentsException;
import fhj.shelf.commandParser.InvalidCommandArgumentsException;
import fhj.shelf.commandParser.InvalidRegisterException;
import fhj.shelf.commandParser.UnknownCommandException;
import fhj.shelf.commands.GetUsers;
import fhj.shelf.commands.PostUser;
import fhj.shelf.commands.exceptions.CommandException;
import fhj.shelf.utils.repos.ElementsRepository;
import fhj.shelf.utils.repos.InMemoryElementsRepository;
import fhj.shelf.utils.repos.InMemoryShelfRepository;
import fhj.shelf.utils.repos.InMemoryUserRepository;
import fhj.shelf.utils.repos.ShelfRepository;
import fhj.shelf.utils.repos.User;
import fhj.shelf.utils.repos.UserRepository;



public class ShelfManagerServlet extends HttpServlet{



		
		public ShelfManagerServlet()
		{
			
		}
	    
	    @Override
	    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
	    	String commandString = getCommandStringFromRequest(req);
	    	

	     	CommandParser parser = new CommandParser();
	    	String input  = getCommandStringFromRequest(req);
	    	run(parser, input, resp);
	    	
//	    	resp.getWriter().write();
	    }
	    
		
	    @Override
	    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
	    	

	    	
	    	CommandParser parser = new CommandParser();
	    	String input  = getCommandStringFromRequest(req);
	    	run(parser, input, resp);
	    	
	    	
	    }
	    
	    
	    public void run(CommandParser parser,String input,HttpServletResponse resp) {
			@SuppressWarnings("resource")
//			Scanner input = new Scanner(System.in);

			
			UserRepository userRepo = new InMemoryUserRepository();

			getCommandParser(userRepo, parser );

			System.out.println(input);

			User admin = new User("Lima", "SLB", "OMAIOREMail", "Lima");
			userRepo.add(admin);
			

				
					try {
//						resp.getWriter().write(parser.getCommand(input.split(" ")).execute());
						parser.getCommand(input.split(" ")).execute();
					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (UnknownCommandException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (DuplicateArgumentsException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InvalidCommandArgumentsException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (CommandException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ExecutionException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			


		}

		/**
		 * Gets the singleton {@link CommandParser} created and configured in the application
		 * @return the application {@link CommandParser}
		 */
		private void getCommandParser(UserRepository userRepo,CommandParser parser ) {
			try {
			parser.registerCommand("POST", new StringBuilder("/users/").toString(),
						new PostUser.Factory(userRepo));
			
			
			parser.registerCommand("GET", new StringBuilder("/users").toString(),
					new GetUsers.Factory(userRepo));
			
			} catch (InvalidRegisterException e) {
				System.out.println("Invalid Register Command!");
			}
		
		}

		/**
		 * returns a string with the command line request needed for command parser. This string is created 
		 * based on {@link HttpServletRequest} data.
		 * @param req the {@link HttpServletRequest} corresponding to the current HTTP request
		 * @return the string to send to {@link CommandParser}
		 */
		private String getCommandStringFromRequest(HttpServletRequest req) {
			StringBuilder out = new StringBuilder();
			InputStream input = null;
			try {
				input = req.getInputStream();

				BufferedReader br = new BufferedReader(new InputStreamReader(input));
				for(String line = br.readLine(); line != null; line = br.readLine()) 
					out.append(line);
				br.close();


			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


			return out.toString();
		}

	    
	    
	}
