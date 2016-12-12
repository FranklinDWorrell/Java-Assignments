import java.io.Serializable;
import java.util.concurrent.LinkedBlockingQueue; 

/**
 * An implementation of the factory specialized for matrix multiplication workers.
 * <b>Provided class--do not modify</b>.
 *
 * MODIFIED WITH PERMISSION OF PROF. ROUSSEV. Implementation of multiple 
 * constructors in <code>MatrixMultiplyWorker</code> require overloading of 
 * method <code>newServiceWorker</code> in this class. 
 */
public class MatrixMultiplyWorkerFactory implements ServiceWorkerFactory {
    /**
     * Instantiates a new matrix multiplication service worker.
     */
    public AbstractServiceWorker newServiceWorker(Serializable parameters, MsgQ resultQ) {
        return new MatrixMultiplyWorker( (MatrixMultiplyParameters)parameters, resultQ);
    }

    /**
     * Instantiates a new matrix multiplication service worker for balanced 
	 * thread pool implementations. 
	 * @param	parameters		information about how to perform matrix multiplications
	 * @param	resultQ			queue to store execution time
	 * @param	servicePool		the balanced thread pool spawning the thread
	 * @return	a new thread to run a matrix multiplication in
     */
    public AbstractServiceWorker newServiceWorker(Serializable parameters, MsgQ resultQ, BalancedMMServicePool servicePool) {
        return new MatrixMultiplyWorker( (MatrixMultiplyParameters)parameters, resultQ, servicePool);
    }


    /**
     * Instantiates a new matrix multiplication service worker for balanced 
	 * thread pool implementations where each thread is reused and services
	 * multiple requests before it is interrupted and allows to terminate. 
	 * @param	parameters		information about how to perform matrix multiplications 
	 * @param	requestQ		queue to store matrix multiplication requests
	 * @param	resultQ			queue to store execution time
	 * @return	a new thread to run a matrix multiplication in
     */
    public AbstractServiceWorker newServiceWorker(Serializable parameters, LinkedBlockingQueue<Serializable> requestQ, MsgQ resultQ) {
        return new MatrixMultiplyWorker( (MatrixMultiplyParameters)parameters, requestQ, resultQ);
    }
}
