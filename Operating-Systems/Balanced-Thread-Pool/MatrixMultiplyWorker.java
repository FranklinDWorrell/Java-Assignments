import java.io.Serializable; 
import java.util.concurrent.LinkedBlockingQueue; 

/**
 * <b>[TODO]</b> Matrix multiplier worker.
 * Implement missing functionality in this class.
 *
 * Threads for the MatrixMultiplyServicePool to run. This implementation allows
 * for this class to be utilized in (1) unbalanced thread pools, (2) thread
 * pools balanced for CPU resources, and (3) balanced thread pools that reuse
 * threads. 
 *
 * @author	Franklin D. Worrell
 * @version	24 November 2016
 */
public class MatrixMultiplyWorker extends AbstractServiceWorker {

    private double[][] a, b, c;	// the multiples and product of the multiplication
    private int mSize;			// the size of the matrices
	private int iterations; 	// the number of times to perform multiplication
	private BalancedMMServicePool servicePool = null;  	// service pool for balanced implementations
	private LinkedBlockingQueue<Serializable> requestQ = null; 
	
	/**
	 * Constructor for non-balanced implementations takes the information 
	 * specifying the matrix multiplications to perform and the result queue
	 * to store the length of execution time. 
	 * @param	parameters		the required info for the matrix multiplications
	 * @param	resultQ			the queue to hold execution time
	 */ 
    public MatrixMultiplyWorker(MatrixMultiplyParameters parameters, MsgQ resultQ) {
        super(parameters, resultQ);
        this.mSize = parameters.matrixSize; 
		this.iterations = parameters.iterations; 
    } // end two-argument constructor

	/**
	 * Overloaded constructor for balanced thread pool implementations. Thread
	 * must keep a reference to the service pool that spawned it to notify pool
	 * once execution ends.
	 * @param	parameters		the required info for the matrix multiplications
	 * @param	resultQ			the queue to hold execution time
	 * @param	servicePool		the thread pool that spawned this worker
	 */ 
	public MatrixMultiplyWorker(MatrixMultiplyParameters parameters, MsgQ resultQ, BalancedMMServicePool servicePool) {
		this(parameters, resultQ); 
		this.servicePool = servicePool; 
    } // end three-argument constructor
	
	
	/**
	 * Overloaded constructor for balanced thread pool implementation that
	 * reuses threads instead of spawning a new one for each request. A null
	 * check on <code>parameters</code> is used later in the implementation to
	 * query whether this is intended to be a reusable thread; hence, the 
	 * precondition. 
	 * 
	 * @require	parameters == null 
	 * @param	parameters		the matrix multiplication specification
	 * @param	requestQ		the queue holding matrix multiplication requests
	 * @param	resultQ			the queue to hold execution time
	 */ 
	public MatrixMultiplyWorker(MatrixMultiplyParameters parameters, LinkedBlockingQueue<Serializable> requestQ, MsgQ resultQ) {
		super(parameters, resultQ); 
		this.requestQ = requestQ; 
    } // end three-argument constructor


    /**
     * Initializes the matrixes based on the size parameter.
     * <b>TODO:</b> Implement this method.
     */
    private void initMatrixes() {
		this.a = new double[this.mSize][this.mSize]; // the first multiple
        this.b = new double[this.mSize][this.mSize]; // the second multiple
        this.c = new double[this.mSize][this.mSize]; // the product

        for (int i = 0; i < this.mSize; i++) {
            for (int j = 0; j < this.mSize; j++) {
				// Populate the first multiple. 
                this.a[i][j] = (i + 1) * (j + 1);
				// Populate the second multiple. 
                this.b[i][j] = (i + 2) * (j + 2);
            }
        }
	} // end method initMatrixes

    /**
     * Performs one iterarion of matrix multiplication.
     * <b>TODO:</b> Implement this method.
     */
    private void doMatrixMultiplication() {
        for(int i = 0; i < this.mSize; i++) {
            for(int j = 0; j < this.mSize; j++) {
                for(int k = 0; k < this.mSize; k++) {
                    this.c[i][j] += this.a[i][k] * this.b[k][j]; 
				}
			}
		}				
	} // end method doMatrixMultiplication

    /**
     * Invokes the initialization of source matrices and the execution of the required number of iterations.
     * The result is a <pre>Long</pre> object, which contains the execution time in milliseconds for all computations, <b>without</b> the initialization.
     * <b>TODO:</b> Implement this method.
     */
    public void run() {
		// Check to see if this is a reusable thread. 
		if (this.parameters == null) {
			reusableRun(); 
		}
		
		// Otherwise, behave as expected for non-reusable implementation. 
		else {
			// Initialize the matrices. 
			this.initMatrixes(); 
			// Start the timer. 
			long start = System.currentTimeMillis();
			
			// Perform the multiplication the specified number of times. 
			for (int i = 0; i < this.iterations; i++) {
				this.doMatrixMultiplication(); 
			} 
			
			// Stop the timer. 
			long end = System.currentTimeMillis();
			
			// If running in balanced implementation, notify the service pool. 
			if (this.servicePool != null) {
				servicePool.addResult(end - start); 
			} 
			
			// If running in unbalanced implementation, simply add result to queue.
			else {
				this.resultQ.append(end - start); 
			}
		}
	} // end method run
	
	
	/**
	 * Defines the run method for threads in a pool that reuses each thread to 
	 * service multiple requests before the thread is interrupted and allowed
	 * to terminate. 
	 */ 
	public void reusableRun() {
		while (!(Thread.currentThread().isInterrupted())) {
			if (this.requestQ.size() > 0) {
				// Pull the parameters for the individual multiplication request.
				this.parameters = (MatrixMultiplyParameters) this.requestQ.poll(); 
				this.mSize = ((MatrixMultiplyParameters)this.parameters).matrixSize; 
				this.iterations = ((MatrixMultiplyParameters)this.parameters).iterations; 
				
				// Initialize the matrices. 
				this.initMatrixes(); 
				
				// Start the timer. 
				long start = System.currentTimeMillis();
				
				// Perform the multiplication the specified number of times. 
				for (int i = 0; i < this.iterations; i++) {
					this.doMatrixMultiplication(); 
				} 
				
				// Stop the timer. 
				long end = System.currentTimeMillis();
				
				// Push the execution time to the message queue. 
				this.resultQ.append(end - start); 
			}
		}
	} // end method reusableRun
} // end class MatrixMultiplyWorker 