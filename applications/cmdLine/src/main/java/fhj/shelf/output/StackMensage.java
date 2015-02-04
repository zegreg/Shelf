package fhj.shelf.output;



public class StackMensage{

	private volatile int  pos = 0;
	private final String[] inf;
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
	
	public boolean contains(String val){
		
		for (int i = 0; i < pos; i++) {
			if (inf[i] == val) {
				return inf[i] == val;
			}
			
		}
		
		
		return false;
		
	}
	
	public boolean isEmpty(){
		return pos==0;
		
	}
	
	public boolean isFull(){
		return pos == inf.length;
	}

}
