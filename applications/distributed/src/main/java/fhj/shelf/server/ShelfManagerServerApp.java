package fhj.shelf.server;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;

import src.main.java.fhj.shelf.http.ShelfManagerServlet;

public class ShelfManagerServerApp {
	

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
