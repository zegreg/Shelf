package fhj.shelf.http;


import java.util.ArrayList;

public class DataObject {
	 
	private int data1 = 100;
	private String data2 = "hello";
	
	private java.util.List <String> list = new ArrayList<String>() {
	  {
		add("String 1");
		add("String 2");
		add("String 3");
	  }
	};
 
	//getter and setter methods
 
	@Override
	public String toString() {
	   return "DataObject [data1=" + data1 + ", data2=" + data2 + ", list="
		+ list + "]";
	}
 
}
