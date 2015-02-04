package fhj.shelf.output;

import java.util.Stack;

public class StackMensage{

	 int pos = 0;
		String[] inf;
	private final Object lock = new Object();
	
	public StackMensage(int maxElements){
		inf = new String[maxElements];
	}
	
	public void push(String val){
		
		synchronized (lock) {
			if (pos>=inf.length) 
			throw new IllegalStateException();
		
			inf[pos++] = val;
		}
				
	}
	
	
	public  String pop() {

		synchronized (lock) {
			if (pos == 0) {
				System.out.println("Stack empty");
			}
			return inf[--pos];
		}

	}
	
	public boolean isEmpty(){
		return pos==0;
		
	}
	
	public boolean isFull(){
		return pos == inf.length;
	}

}
