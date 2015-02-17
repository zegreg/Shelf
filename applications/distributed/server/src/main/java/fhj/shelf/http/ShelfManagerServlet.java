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
import fhj.shelf.exceptions.CommandException;
import fhj.shelf.exceptions.DuplicateArgumentsException;
import fhj.shelf.exceptions.InvalidCommandArgumentsException;
import fhj.shelf.exceptions.UnknownCommandException;

public class ShelfManagerServlet extends HttpServlet {

	private final static Logger LOGGER = LoggerFactory
			.getLogger(ServerCommands.class);

	private static final long serialVersionUID = 1L;

	private CommandParser parser = CommandParser.getInstance();

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String method = req.getMethod();
		if (method.equals("PATCH")) {
			doPatch(req, resp);
			return;
		}
		super.service(req, resp);
	}

	public void doPatch(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		CommandParser parser = CommandParser.getInstance();
		String input = "PATCH" + getInputStreamReader(req);

		String mensage = startParser(parser, input);
		System.out.println(mensage);

		PrintWriter out;
		out = resp.getWriter();
		out.print(mensage);
		out.flush();

	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		String input = getOutputStreamReader(req);
		System.out.println(input);

		String message = startParser(getCommandParser(), input);

		System.err.println(message);

		PrintWriter out;
		out = resp.getWriter();
		out.print(message);
		out.flush();
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

	
		String input = req.getMethod() + getInputStreamReader(req);
		String mensage = startParser(getCommandParser(), input);
		System.out.println(mensage);

		resp.setContentType("application/json");
		PrintWriter out;
		out = resp.getWriter();
		out.print(mensage);
		out.flush();

	}

	public String startParser(CommandParser parser, String input) {

		String result = null;

		System.out.println(input);

		do {

			try {

				result = getCommandParser().getCommand(input.split(" "))
						.execute();
			} catch (IllegalArgumentException e) {
				LOGGER.error(
						"FailedCreateActivityFunction Exception Occured : ", e);

			} catch (UnknownCommandException e) {
				LOGGER.error(
						"FailedCreateActivityFunction Exception Occured : ", e);

			} catch (DuplicateArgumentsException e) {
				LOGGER.error(
						"FailedCreateActivityFunction Exception Occured : ", e);

			} catch (InvalidCommandArgumentsException e) {
				LOGGER.error(
						"FailedCreateActivityFunction Exception Occured : ", e);

			} catch (CommandException e) {
				LOGGER.error(
						"FailedCreateActivityFunction Exception Occured : ", e);

			} catch (ExecutionException e) {
				LOGGER.error(
						"FailedCreateActivityFunction Exception Occured : ", e);

			}

			return result;

		} while (true);
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
	private String getInputStreamReader(HttpServletRequest req) {

		StringBuilder out = new StringBuilder();

		String path = req.getRequestURI();
		String header = req.getHeader("accept");
		System.out.println(header);

		out.append(" ").append(path).append(" ");
		System.out.println("path" + path);

		try {
			InputStream input = req.getInputStream();

			BufferedReader br = new BufferedReader(new InputStreamReader(input));
			for (String line = br.readLine(); line != null; line = br
					.readLine())
				out.append(line);
			br.close();

		} catch (IOException e) {
			LOGGER.error("FailedCreateActivityFunction Exception Occured : ", e);
		}

		out.append("&accept=").append(header);
		return out.toString();
	}

	private String getOutputStreamReader(HttpServletRequest req) {
		StringBuilder in = new StringBuilder();

		String method = req.getMethod();
		String path = req.getRequestURI();
		String header = req.getHeader("accept");
		if (!header.equals("application/json")) {
			String[] headerFinal = header.split(",");
			header = headerFinal[0];
		}
		return in.append(method).append(" ").append(path).append(" accept=")
				.append(header).toString();

	}

	private CommandParser getCommandParser() {
		return parser;
	}
	
	
}
