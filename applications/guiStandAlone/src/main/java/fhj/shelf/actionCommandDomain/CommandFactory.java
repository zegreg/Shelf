package fhj.shelf.actionCommandDomain;

import java.util.Map;

public interface CommandFactory {

	StandAloneCommand newInstance(Map<String, String> parameters);

}
