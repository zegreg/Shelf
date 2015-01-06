package main.java.FHJ.shelf.commandParser;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

import main.java.FHJ.shelf.commands.Command;
import main.java.FHJ.shelf.commands.CommandFactory;




/**
 * Class whose instances are responsible for translating command strings into their
 * {@link main.java.FHJ.shelf.commands.Command} instance counterparts.
 * 
 * Implementation notes: (TODO) 
 */
public class CommandParser {
	
	/**
	 * The root of the parser tree, which is updated every time a new command is registered 
	 */
	private final Node root = new Node("/"); 
	
	/**
	 * Class whose instances represent the parser tree nodes
	 */
	private static class Node {
		
		public final String content;
		public final Map<String, Node> fixedChilds;
		public Node placeholderChild;
		public CommandFactory factory;
		
		public Node(String content)
		{
			this.content = content;
			this.fixedChilds = new HashMap<>();
		}

		public boolean hasPlaceholderChild(String currentContent) {
			return placeholderChild != null && !placeholderChild.content.equals(currentContent);
		}

		public Node addFixedChild(String currentContent) {
			Node n;
			fixedChilds.put(currentContent, n = new Node(currentContent));
			return n;
			
		}

		public Node getAndCreateIfDoesNotExitFixedNode(String currentContent) {
			Node node = fixedChilds.get(currentContent);
			if(node == null)
				node = addFixedChild(currentContent);
			return node;
		}

		public Node getAndCreateIfDoesNotExitPlacehoderNode(String currentContent) {
			if(placeholderChild == null) {
				placeholderChild = new Node(currentContent);
			}
			return placeholderChild;
		}

		/**
		 * Returns the child with the specified content or {@code null} if not found.
		 * Implementation details: 
		 * This method first tries to find the value in fixedNodes. If it finds it, returns that node.
		 * if it does not, returns the placeholderChild, that may be null; 
		 * @param currentContent the current content to find
		 * @return
		 */
		public Node getChildForValue(String content) {
			Node n = fixedChilds.get(content);
			if(n == null) {
				n = placeholderChild;
			}
			return n;
		}
	}
	
	/**
	 * Checks whether the given content is relative to a placeholder node.
	 * 
	 * @param currentContent The content to be evaluated 
	 * @return <code>true</code> if it is a placeholder node content, <code>false</code> otherwise
	 */
	private boolean isPlaceHolderNode(String currentContent) 
	{
		return currentContent.startsWith("{") && currentContent.endsWith("}");
	}

	/**
	 * Produces the {@link Map} with the parameters 
	 * 
	 * @throws InvalidCommandArgumentsException
	 * @throws DuplicateArgumentsException 
	 */
	private Map<String, String> getParameters( String parameters ) throws InvalidCommandArgumentsException, DuplicateArgumentsException
	{
		Map<String, String> parametersMap = new HashMap<>();

		if (parameters != null)
		{
			String[] parametersElements = parameters.split("&");
			for (String parameterElement : parametersElements)
			{
				String[] keyAndValues = parameterElement.split("=");
				if( keyAndValues.length != 2 )
					throw new InvalidCommandArgumentsException("Invalid CommandArguments Exception");
				if( parametersMap.containsKey(keyAndValues[0]) )
					throw new DuplicateArgumentsException("Duplicate Arguments Exception");
				parametersMap.put(keyAndValues[0], keyAndValues[1]);
			}
		}
		return parametersMap;
	}


	/**
	 * Helper method that recursively updates the parser tree whenever a new command 
	 * is registered.
	 * 
	 * @param rootNode The tree's root
	 * @param pathElements An array containing the elements of the path (i.e. /users/{username})
	 * results in an array containing "users" and "{username}" 
	 * @param pathStartIndex The start index of the array (to enable recursion)
	 * @param cmdFactory The {@link CommandFactory} instance
	 * @throws InvalidRegisterException If the given command cannot be registered (i.e. perhaps 
	 * the grammar is not correct)
	 */
	private void updateSubtree(Node rootNode, String[] pathElements, int pathStartIndex, CommandFactory cmdFactory) throws InvalidRegisterException 
	{
		if(pathStartIndex == pathElements.length) 
		{
			rootNode.factory = cmdFactory;
			return;
		}
		
		String currentContent = pathElements[pathStartIndex];
		Node node;
		if(!isPlaceHolderNode(currentContent)) {
			node = rootNode.getAndCreateIfDoesNotExitFixedNode(currentContent);
		} else {
			if(rootNode.hasPlaceholderChild(currentContent)) {
				throw new InvalidRegisterException(
						MessageFormat.format("Command registred with a placeholder with name '{0}', at node with an already existant placeholder child with name '{1}'", currentContent, rootNode.placeholderChild.content)
						);
			}
			node = rootNode.getAndCreateIfDoesNotExitPlacehoderNode(currentContent);
		}
		updateSubtree(node, pathElements, pathStartIndex + 1, cmdFactory);
	}
	
	/**
	 * Method used to register an association between the string and its associated 
	 * command factory.
	 *  
	 * @param method The request method (i.e. GET, POST)
	 * @param path The resource path (e.g. /users/{username})
	 * @param cmdFactory The command factory instance 
	 * @throws InvalidRegisterException If the given command cannot be registered (i.e. perhaps 
	 * the grammar is not correct)
	 */
	public void registerCommand(String method, String path, CommandFactory cmdFactory) throws InvalidRegisterException 
	{
		path = method.trim() + path; 
		String[] treePathElementsArray = path.split("/");
		updateSubtree(root, treePathElementsArray, 0, cmdFactory);
	}
	
	/**
	 * Parses the given arguments and produces the corresponding {@link Command} instance. Arguments are composed 
	 * of two or three elements. 
	 * Examples are:
	 * POST /products/hotel strs=5&name=Hotel1&description=hotel maravilha1&loginName=lfalcao&loginPassword=slb
	 * GET /products/hotel
	 * 
	 * @param args The command's {@link String} representation that will be parsed
	 * @return The {@link Command} instance
	 * @throws UnknownCommandException if the command is not recognized
	 * 
	 */
	public Command getCommand(String... args) throws UnknownCommandException, DuplicateArgumentsException, InvalidCommandArgumentsException 
	{
		if(args.length < 2 || args.length > 3) {
			throw new UnknownCommandException("args must have 2 or three elements");
		}
		 
		String cmd = args[0] + args[1];
		String[] pathElements = cmd.split("/");
		
		 Map<String, String> parametersMap = (args.length == 2)
		 			? new HashMap<String, String>()
		 			: getParameters( args[2] );
		
		Command c = getCommandInternal(root, pathElements, 0, parametersMap);
		return c;		
	}

	/**
	 * 
	 * @param rootNode
	 * @param pathElements
	 * @param pathStartIndex
	 * @return
	 * @throws UnknownCommandException
	 */
	private Command getCommandInternal(Node rootNode, String[] pathElements, int pathStartIndex, Map<String,String> parameters) throws UnknownCommandException 
	{
		if(pathStartIndex == pathElements.length) {
			if(rootNode.factory == null)
				throw new UnknownCommandException("Current node has no command factory!");
			
			return rootNode.factory.newInstance(parameters);
		}
		
		String currentContent = pathElements[pathStartIndex];
		Node child = rootNode.getChildForValue(currentContent);
		if(child == null)
			throw new UnknownCommandException("Command path not found!");
		
		if (isPlaceHolderNode(child.content))
			parameters.put(child.content.substring(1, child.content.length()-1), currentContent);

		return getCommandInternal(child, pathElements, pathStartIndex+1, parameters);
	}
}
