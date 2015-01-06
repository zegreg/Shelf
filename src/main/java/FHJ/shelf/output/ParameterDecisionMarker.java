package main.java.FHJ.shelf.output;

import java.util.Map;

import main.java.FHJ.shelf.model.repos.InMemoryUserRepository;

public interface ParameterDecisionMarker {


	String execute(Map<String, String> parameters);


}
