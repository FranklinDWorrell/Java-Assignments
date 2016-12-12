import java.io.Serializable;
import java.util.concurrent.LinkedBlockingQueue; 

/**
 * A more advanced implementaion of a service pool for matrix multiplication workers, 
 * which matches the number of outstanding jobs to the number of available hardware-supported threads.
 * <b>TODO:</b> Implement this class.
 */
public class BalancedMMServicePool extends MatrixMultiplyServicePool {
	
	private int availableCores; 
	private int numberOfThreads; 
	private LinkedBlockingQueue<Serializable> requestQ; // queue to hold requests
	
    /**
     * Constructor initializes all the instance variables. The pool size 
	 * parameters passed to the super class constructor are not used in this
	 * subclass. 
	 *
	 * @param poolMin   minimum pool size (not used)
     * @param poolMax   maximum pool size (not used)
     */
    public BalancedMMServicePool(int poolMin, int poolMax) {
        super(poolMin, poolMax); 			// not used in this service pool 
		this.requestQ = new LinkedBlockingQueue<Serializable>(); 
		this.availableCores = Runtime.getRuntime().availableProcessors(); 
		this.numberOfThreads = 0; 
    } // end constructor
	
	
	/**
	 * Add a request to the thread pool. If there are fewer threads than CPU
	 * cores, spawn a new thread and service the request. Otherwise, add the 
	 * request to the request queue. 
	 * 
	 * @param	request		the parameters for the matrix multiplication
	 */ 
	@Override
	public void addRequest(Serializable request) { 
		if (numberOfThreads < availableCores) {
			factory.newServiceWorker(request, resultQ, this).start();
			numberOfThreads++; 
		}  
		
		else {
			requestQ.offer(request); 
		} 
	} // end method addRequest
	
	
    /**
     * Notifies the service pool that a worker has completed the computation with the given result.
     * If there are any outstanding requests, the first in line should be serviced.
     * 
     * <b>TODO:</b> Implement this method.
     */
    public void addResult(Serializable result) {
		// Add the result to the result queue. 
		resultQ.append(result); 
		
		// Decrement the number of threads. 
		numberOfThreads--; 
		
		// If there are outstanding requests, service them in a new thread. 
		if (requestQ.size() > 0) {
			factory.newServiceWorker(requestQ.poll(), resultQ, this).start();
			numberOfThreads++; 
		} 		
	} // end method addResult
} // end class BalancedMMServicePool
