package fhj.shelf.output;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class PlainBuilder implements StrategyFormatter {

	protected static StringBuilder builder;

	@SuppressWarnings("static-access")
	public PlainBuilder(StringBuilder builder) {
		this.builder = builder;
	}

	void builder(String str) {

		builder.append(str);
	}

	@Override
	public String encode(Map<String, String> mapWithResult) {
		Iterator<Entry<String, String>> iterator = mapWithResult.entrySet()
				.iterator();

		while (iterator.hasNext()) {
			builder.append(iterator.next().toString()).append("\n\n\n");
		}
		return builder.toString();
	}

}
