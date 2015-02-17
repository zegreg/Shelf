package fhj.shelf.output;

import java.util.Map;

public class HtmlBuilder implements StrategyFormatter {

	protected static StringBuilder builder;
	private boolean first = true;

	@SuppressWarnings("static-access")
	public HtmlBuilder(StringBuilder buider) {
		this.builder = buider;
	}

	void builder(String str) {

		builder.append(str);
	}

	public String encode(Map<String, String> mapWithResult) {

		for (Map.Entry<String, String> entry : mapWithResult.entrySet()) {

			if (first) {
				appendBeginObject();
				appendNextLine();
				appendBeginHead();
				appendNextLine();
				builder.append("<title>");
				builder(entry.getKey());
				builder.append("</title>");
				appendNextLine();
				builder.append("</head>");
				appendNextLine();
				builder.append("<body>");
				first = false;

			} else {

				appendBeginHeader();
				builder(entry.getKey());
				appendEndHeader();
				appendNextLine();
			}

			htmlToString(entry);

		}
		builder.append("</body>");
		appendEndObject();
		return builder.toString();
	}

	private void htmlToString(Map.Entry<String, String> entry) {

		if (entry.getValue() == null) {
			appendNextLine();

		} else {
			regexValue(entry.getValue());

		}
	}

	private void regexValue(String s) {
		String[] result = s.split("&");
		for (String r : result) {
			appendBeginParagraph();
			r.toString();
			builder.append(r);
			appendEndParagraph();
			appendNextLine();
		}

	}

	protected static void appendBeginHead() {
		builder.append("<head>");
	}

	protected static void appendBeginObject() {
		builder.append("<html>");
	}

	protected static void appendEndObject() {
		builder.append("</html>");
	}

	protected static void writeNameValueSeparator() {
		builder.append(':');
	}

	protected static void appendBeginHeader() {
		builder.append("<h1>");
	}

	protected static void appendEndHeader() {
		builder.append("</h1>");
	}

	protected static void appendBreakLine() {
		builder.append("<br>");

	}

	protected static void appendBeginParagraph() {
		builder.append("<p>");

	}

	protected static void appendEndParagraph() {
		builder.append("</p>");

	}

	protected static void appendNextLine() {
		builder.append("\n\n");
	}

}
