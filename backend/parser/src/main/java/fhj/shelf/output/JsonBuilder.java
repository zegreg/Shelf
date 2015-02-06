package fhj.shelf.output;


import java.util.Map;

/**
 * 
 * 
 *@author Filipa Estiveira, Hugo Leal, José Oliveira
 */
public class JsonBuilder implements StrategyFormatter {

	/**
	 * Declare a StringBuilder object
	 */
	protected final StringBuilder builder;;
	
	/**
	 * Flag to get the first parameter's value 
	 */
	private boolean firstValue =true;

	boolean first = true;
	/**
	 * Constructor
	 * @param builder
	 */
	public JsonBuilder(StringBuilder builder) {
		this.builder = builder;
	}

	/**
	 * The builder method that append a given string by argument
	 * @param string
	 */
	private void builder( String string ) {
		builder.append( string );
	}


	/**
	 * The overrride Encode Method. This method receives a map <String, String>  and iterate her
	 * Return a String
	 */
	@Override
	public String encode( Map<String, String>_map ){

//		writeBeginObject();

		

		for (Map.Entry<String, String> entry : _map.entrySet()) {	

			if (first){
				first = false;

			}
			else
				writeObjectValueSeparator();
			
			ToJsonString(entry);
		}
		
		writeEndParenthesis();
		writeEndObject();
		return builder.toString();
	}

	
	/**
	 * This method make the decision for getting the first map's value
	 * @param entry
	 */
	private void ToJsonString(Map.Entry<String, String> entry) {

		if (entry.getKey() == null)
			builder("null");

		if (firstValue) {
			writeBeginObject();
			writeQuotatioMarks();
//			builder(entry.getValue().substring(0, entry.getValue().indexOf('\n')));
//			builder(entry.getKey().substring(0, entry.getValue().indexOf('\n')));
			builder(entry.getKey());
			writeQuotatioMarks();
			writeNameValueSeparator() ;
			
			
//			firstValue = false;
		}

//       if (entry.getValue() instanceof String) {
    	   if (entry.getValue() == null){
    		   writeBeginParenthesis();
    		   first = true;
   			
    	   } else{

//			writeBeginObject();
			writeQuotatioMarks();
//			builder(entry.getKey());
			RegexValue(entry.getValue());
		
//			writeNameValueSeparator();
//			writeBeginObject() ;
//			builder(entry.getValue());
//			writeEndObject() ;
			
		}
//		writeEndObject();
//		writeNextLine();
	}


	/**
	 * This method iterate an array string result of a  given  map's value string.
	 * @param s
	 */
	private void RegexValue(String s) {
		String[] result = s.split(":");
		boolean Istrue = true;

		for (String r : result) {

			if (r.contains(":")) {
				if (Istrue) 
					Istrue = false;
				else
					writeObjectValueSeparator();

				AppendContentWithColon(r);
			}
		}
//		writeNameValueSeparator();
		builder.append(s);
		writeQuotatioMarks();
		writeEndObject();
	}

	/**
	 * This method append ':' between the key and value string.<p>
	 *  {"Key":"Value"}
	 * @param r
	 */
	private void AppendContentWithColon(String r) {
		
		writeQuotatioMarks();
		String[] p =r.split(":");
		String firstExpression = p[0];
		builder.append(firstExpression);
		writeQuotatioMarks();
		writeNameValueSeparator();
		writeQuotatioMarks();
		
		String secondExpression = p[1];
		builder.append(secondExpression);
		
		writeQuotatioMarks();
		
	}

	protected void writeNextLine() {
		builder.append( '\n' );
	}
	
	
	protected void writeBeginParenthesis()  {
		builder.append( '[' );
	
	}
	protected void writeEndParenthesis()  {
		builder.append( ']' );
	}

	protected void writeQuotatioMarks()  {
		builder.append( '"' );
	}

	protected void writeBeginObject()  {
		builder.append( '{' );
	}

	protected void writeEndObject()  {
		builder.append( '}' );
	}

	protected void writeNameValueSeparator() {
		builder.append( ':' );
	}

	protected void writeObjectValueSeparator()  {
		builder.append( ',' );
	}


}
