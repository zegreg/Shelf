package fhj.shelf.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.LinkedBlockingQueue;

import javax.servlet.AsyncContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fhj.shelf.CommandParser;
import fhj.shelf.commands.GetUser;
import fhj.shelf.commands.GetUsers;
import fhj.shelf.commands.PostUser;
import fhj.shelf.exceptions.CommandException;
import fhj.shelf.exceptions.DuplicateArgumentsException;
import fhj.shelf.exceptions.InvalidCommandArgumentsException;
import fhj.shelf.exceptions.InvalidRegisterException;
import fhj.shelf.exceptions.UnknownCommandException;
import fhj.shelf.inMemoryRepositories.InMemoryUserRepository;
import fhj.shelf.output.StackMensage;
import fhj.shelf.repositories.User;
import fhj.shelf.repositories.UserRepository;


public class ThreadDoPost implements ServletContextListener{
	
	  private static final BlockingQueue queue = new LinkedBlockingQueue();
	  private final static Logger LOGGER = LoggerFactory.getLogger(ThreadDoPost.class);

	  private volatile Thread thread;

	  public static void add(AsyncContext c) {
	    queue.add(c);
	  }

	  @Override
	  public void contextInitialized(ServletContextEvent servletContextEvent) {
	    thread = new Thread(new Runnable() {
	      @Override
	      public void run() {
	        while (true) {
	          try {
	            Thread.sleep(2000);
	            AsyncContext context;
	            while ((context = (AsyncContext) queue.poll()) != null) {
	              try {
	                ServletRequest req = context.getRequest();
	               
	        		
	                
	              } catch (Exception e) {
	                throw new RuntimeException(e.getMessage(), e);
	              } finally {
	                context.complete();
	              }
	            }
	          } catch (InterruptedException e) {
	            return;
	          }
	        }
	      }
	    });
	    thread.start();
	  }

	  @Override
	  public void contextDestroyed(ServletContextEvent servletContextEvent) {
	    thread.interrupt();
	  }
	  
	  public StackMensage run(CommandParser parser, String input, HttpServletResponse resp) {
			
			StackMensage stackMensage = new StackMensage(10);
			
			UserRepository userRepo = new InMemoryUserRepository();

			getCommandParser(userRepo, parser);

			System.out.println(input);

			User admin = new User("Lima", "SLB", "OMAIOREMail", "Lima");
			userRepo.add(admin);

			try {
				
				parser.getCommand(input.split(" ")).execute();
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

