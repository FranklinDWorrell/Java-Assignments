/**
 * Code used to generate all data cited in the included report. <b>THIS CODE 
 * WILL TAKE A VERY LONG TIME TO EXECUTE.</b> Performs multiple sequential and 
 * parallel tests of matrix multiplication with different size matrices and 
 * different numbers of iterations. Likewise performs multiple sequential and
 * parallel determinant calculations. 
 * 
 * @author	Franklin D. Worrell
 * @version	24 November 2016
 */
public class TestService {

    public static void main(String[] args) throws Exception {
		// Tests of multiplication calculations. 
		testMultiplication(); 
	} // end method main
	
	/**
	 * Performs sequential and parallel tests of matrix multiplication with 
	 * varying numbers of iterations and different sized matrices. Prints the 
	 * results of each test. 
	 */ 
	private static void testMultiplication() throws Exception {
		for (int size = 400; size <= 1000; size += 150) {
			for (int iterations = 2; iterations <= 10; iterations += 2) {
				// Use the balanced thread pool WITHOUT thread reuse. 
				System.out.println("Parallel multiplication with size " + size + 
								   " and " + iterations + " iterations:"); 
				long parStart = System.currentTimeMillis();
				parallelTest(size, iterations); 
				long parTime = System.currentTimeMillis() - parStart; 
				System.out.println("Parallel total runtime: " + parTime); 
				System.out.println(); 
				
				// Use the balanced thread pool that REUSES threads. 
				System.out.println("Reusing threads for size " + size + 
								   " and " + iterations + " iterations:"); 
				long reuseStart = System.currentTimeMillis();
				threadReuseTest(size, iterations); 
				long reuseTime = System.currentTimeMillis() - reuseStart; 
				System.out.println("Reusing threads total runtime: " + reuseTime); 
				System.out.println(); 
			}
		}
	} // end method testMultiplication


    /**
     * Tests the matrix multiplication methods. Launches the workers in a 
	 * thread pool balanced for CPU resources that run in parallel.
     *
	 * @param	size		the size of the matrix to be multiplied by itself
	 * @param	iterations	the number of times to perform the multiplication
     */
    private static void parallelTest(int size, int iterations) throws Exception {
        MatrixMultiplyServicePool servicePool = new BalancedMMServicePool(5, 10);

        int max = 8;
        for(int i=0; i<max; i++) {
            servicePool.addRequest(new MatrixMultiplyParameters(size, iterations));
        }
        for(int i=0; i<max; i++) {
            System.out.println(servicePool.getResponse());
        } 		
    } // end method parallelTest


    /**
     * Tests the matrix multiplication methods. Launches the workers in a pool
	 * of reusable threads balanced to CPU resources that run in parallel.
     *
	 * @param	size		the size of the matrix to be multiplied by itself
	 * @param	iterations	the number of times to perform the multiplication
     */
    private static void threadReuseTest(int size, int iterations) throws Exception {
        ReuseBMMServicePool servicePool = new ReuseBMMServicePool(5, 10);

        int max = 8;
        for(int i=0; i<max; i++) {
            servicePool.addRequest(new MatrixMultiplyParameters(size, iterations));
        }
        for(int i=0; i<max; i++) {
            System.out.println(servicePool.getResponse());
        } 
		
		servicePool.cancelAllThreads(); 
    } // end method threadReuseTest
} // end class TestService