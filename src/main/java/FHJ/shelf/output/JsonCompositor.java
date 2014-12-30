package main.java.FHJ.shelf.output;


import java.util.Map;

public class JsonCompositor implements Strategy {

	protected final StringBuilder builder;;

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

			if (first)
				first = false;
			else
				writeObjectValueSeparator();

			ToJsonString(entry);
		}
		writeEndObject();
		System.out.println(builder.toString());
		return builder.toString();
	}

	private void ToJsonString(Map.Entry<String, String> entry) {
		writeBeginObject();
		if (entry.getKey() == null)
			builder("null");
		else {
			writeQuotatioMarks();
			builder(entry.getKey());
			writeQuotatioMarks();
		}
		writeNameValueSeparator() ;
		if (entry.getValue() instanceof String) {
			writeQuotatioMarks();
			builder(entry.getValue());
			writeQuotatioMarks();
		}
		writeEndObject();
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
