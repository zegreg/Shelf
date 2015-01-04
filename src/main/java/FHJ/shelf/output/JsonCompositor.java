package main.java.FHJ.shelf.output;


import java.util.Map;
import java.util.TreeMap;

public class JsonCompositor implements Strategy {

	protected final StringBuilder builder;;
	private boolean firstValue =true;
	
	public JsonCompositor(StringBuilder builder) {
		this.builder = builder;
	}

	void builder( String string ) {
		builder.append( string );
	}


	@Override
	public String encode( Map<String, String>_map ){

		writeBeginObject();

		boolean first = true;

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
		System.out.println(builder.toString());
		return builder.toString();
	}

	private void ToJsonString(Map.Entry<String, String> entry) {
		
		if (entry.getKey() == null)
			builder("null");
		
		if (firstValue) {
			writeQuotatioMarks();
			builder(entry.getValue().substring(0, entry.getValue().indexOf('\n')));
			writeQuotatioMarks();
			writeNameValueSeparator() ;
			writeBeginParenthesis();
			firstValue = false;
		}
//		else {
//			
//			writeQuotatioMarks();
//			builder(entry.getKey());
//			writeQuotatioMarks();
//			writeNameValueSeparator() ;
//		}
		
		if (entry.getValue() instanceof String) {
			
			writeBeginObject();
			RegexValue(entry.getValue());
//			builder(entry.getValue());
			
		}
		writeEndObject();
	}
	
	
	private void RegexValue(String s) {
		String[] result = s.split("[\n]");
		boolean firstValue1 = true;

		for (String r : result) {

			if (r.contains(":")) {
				if (firstValue1) 
					firstValue1 = false;
				else
					writeObjectValueSeparator();
				
				AppendContentWithColon(r);
			}
		}
	}

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

	protected void writeBeginParenthesis()  {
		builder.append( '[' );
		builder.append( '\n' );
	}
	protected void writeEndParenthesis()  {
		builder.append( '\n' );
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



//		protected void encode( ArrayList<String> array ) throws IOException {
//			writeBeginArray();
//			boolean first = true;
//			for( String value : array ) {
//				if( !first ) {
//					writeArrayValueSeparator();
//				}
//				write(value);
//				first = false;
//			}
//			writeEndArray();
//		}
//
//		protected void writeBeginArray() throws IOException {
//			writer.write( '[' );
//		}
//
//		protected void writeEndArray() throws IOException {
//			writer.write( ']' );
//		}
//
//		protected void writeArrayValueSeparator() throws IOException {
//			writer.write( ',' );
//		}
//
//		@Override
//		public void encode(String str) {
//			// TODO Auto-generated method stub
//			
//		}




}
