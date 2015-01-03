package main.java.FHJ.shelf.output;

import java.io.IOException;
import java.util.Map;
import java.util.regex.Pattern;

import org.hamcrest.Matcher;


public class HtmlCompositor implements Strategy {

	protected static StringBuilder builder;

	public HtmlCompositor(StringBuilder buider) {
		this.builder =buider;
	}

	void builder( String str ){
		
		builder.append(str);
	}


	public String encode(Map<String, String>_map){
	
		for (Map.Entry<String, String> entry : _map.entrySet()) {	

			if (entry.getKey() != null) {
				appendBeginHeader();
				builder(entry.getKey());
				appendEndHeader();
				appendNextLine();
				RegexValue(entry.getValue());
				
			}
			
			
		}
		appendEndObject();
		System.out.println(builder.toString());
		return builder.toString();
	}

	private void RegexValue(String s) {
		String[] result = s.split("[\n]");
		for (String r : result) {
			appendBeginParagraph();
			r.toString();
			builder.append(r);
			appendEndParagraph();
			appendNextLine();    
		}


	}

	
	
	protected static void appendBeginHead()  {
		builder.append("<head>");
	}

	protected static void appendBeginObject()  {
		builder.append("<html>");
	}

	protected static void appendEndObject() {
		builder.append( "</html>" );
	}

	protected static void writeNameValueSeparator() {
		builder.append( ':' );
	}

	protected static void appendBeginHeader() {
		builder.append( "<h1>" );
	}

	protected static void appendEndHeader() {
		builder.append( "</h1>" );
	}

	protected static void appendBreakLine() {
		builder.append( "<br>" );
		
	}
	
	protected static void appendBeginParagraph() {
		builder.append( "<p>" );
		
	}
	protected static void appendEndParagraph() {
		builder.append( "</p>" );
		
	}
	
	protected static void appendNextLine() {
		builder.append( "\n\n" );
	}

}
