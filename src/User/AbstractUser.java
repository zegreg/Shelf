package User;

import java.util.concurrent.atomic.AtomicInteger;


public abstract class AbstractUser implements UserInterface {
	
	
	private static AtomicInteger uniqueId=new AtomicInteger();
    private long id;

    public AbstractUser() {
	   	
	 this.id=uniqueId.getAndIncrement();
	}
    
    
    @Override
    public long getId() {
    	
    	return id;
    }
}
