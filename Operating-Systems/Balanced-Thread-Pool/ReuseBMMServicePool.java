import java.io.Serializable;
import java.util.concurrent.LinkedBlockingQueue; 

/**
 * A balanced implementation of a thread pool that matches the number of 
 * threads to the number of processors available and reuses them instead 
 * simply spawning a new thread for each request. 
 * 
 * @author	Franklin D. Worrell
 * @version	24 November 2016
 */ 
public class ReuseBMMServicePool extends MatrixMultiplyServicePool {
	
	private int availableCores; 
	private LinkedBlockingQueue<Serializable> requestQ; 
	private Thread[] threads; 
	
	/**
	 * Constructor creates the threads for the pool and passes them a 
	 * reference to the request queue. 
	 *
	 * @param	poolMin		not used in this implementation
	 * @param	poolMax		not used in this implementation
	 */ 
	public ReuseBMMServicePool(int poolMin, int poolMax) {
		super(poolMin, poolMax); 
		this.requestQ = new LinkedBlockingQueue<Serializable>(); 
		this.availableCores = Runtime.getRuntime().availableProcessors(); 
		this.threads = new Thread[this.availableCores]; 
		
		// Spawn the threads. 
		for (int i = 0; i < availableCores; i++) {
			this.threads[i] = factory.newServiceWorker(null, requestQ, resultQ);
			this.threads[i].start();
		}	
	} // end constructor
	
	
	/**
	 * Adds a request to the request queue, so that a thread can pull the 
	 * request and service it. 
	 * 
	 * @param	request		the request in need of servicing
	 */ 
	@Override
	public void addRequest(Serializable request) {
		this.requestQ.offer(request); 
	} // end method addRequest 
	
	
	/**
	 * Sets the interrupted flag for each of the threads in the thread pool so
	 * that the threads will terminate after all multiplication requests have 
	 * been serviced. 
	 */ 
	public void cancelAllThreads() {
		for (int i = 0; i < this.threads.length; i++) {
			this.threads[i].interrupt(); 
		} 
	} // end method cancelAllThreads
} // end class ReuseBMMServicePool