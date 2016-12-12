/**
 * Code used to generate all data cited in the included report. <b>THIS CODE 
 * WILL TAKE A VERY LONG TIME TO EXECUTE.</b> Performs multiple sequential and 
 * parallel tests of matrix multiplication with different size matrices and 
 * different numbers of iterations. Likewise performs multiple sequential and
 * parallel determinant calculations. 
 * 
 * @author	Franklin D. Worrell
 * @version	12 November 2016
 */
public class TestService {

    public static void main(String[] args) throws Exception {
		// Tests of multiplication calculations. 
		testMultiplication(); 

		// Tests of determinant calculations. 
		testDeterminant(); 
	} // end method main
	
	/**
	 * Performs sequential and parallel tests of matrix multiplication with 
	 * varying numbers of iterations and different sized matrices. Prints the 
	 * results of each test. 
	 */ 
	private static void testMultiplication() throws Exception {
		for (int size = 400; size <= 1000; size += 150) {
			for (int iterations = 2; iterations <= 10; iterations += 2) {
				// Perform test sequentially. 
				System.out.println("Sequential multiplication with size " + size + 
								   " and " + iterations + " iterations:"); 
				long seqStart = System.currentTimeMillis();
				sequentialTest(size, iterations); 
				long seqTime = System.currentTimeMillis() - seqStart; 
				System.out.println("Sequential total runtime: " + seqTime); 
				System.out.println(); 
				
				// Perform the same test in parallel. 
				System.out.println("Parallel multiplication with size " + size + 
								   " and " + iterations + " iterations:"); 
				long parStart = System.currentTimeMillis();
				parallelTest(size, iterations); 
				long parTime = System.currentTimeMillis() - parStart; 
				System.out.println("Parallel total runtime: " + parTime); 
				System.out.println(); 
			}
		}
	} // end method testMultiplication

	/**
	 * Performs sequential and parallel tests of determinant calculation with 
	 * varying numbers of iterations and different sized matrices. Prints the 
	 * results of each test. 
	 */ 
	private static void testDeterminant() throws Exception {
		for (int size = 9; size <= 12; size++) {
			// Perform test sequentially. 
			System.out.println("Sequential determinant of matrix with size " + 
							   size + ":"); 
			long seqStart = System.currentTimeMillis();
			sequentialDeterminantTest(size); 
			long seqTime = System.currentTimeMillis() - seqStart; 
			System.out.println("Sequential total runtime: " + seqTime); 
			System.out.println(); 
			
			// Perform the same test in parallel. 
			System.out.println("Parallel determinant of matrix with size " + 
							   size + ":"); 
			long parStart = System.currentTimeMillis();
			parallelDeterminantTest(size); 
			long parTime = System.currentTimeMillis() - parStart; 
			System.out.println("Parallel total runtime: " + parTime); 
			System.out.println(); 
		}
	} // end method testDeterminant

    /**
     * Tests the matrix multiplication methods. Launches the workers one after the other.
     *
	 * @param	size		the size of the matrix to be multiplied by itself
	 * @param	iterations	the number of times to perform the multiplication
     */
    private static void sequentialTest(int size, int iterations) throws Exception {
        MatrixMultiplyServicePool servicePool = new MatrixMultiplyServicePool(5, 10);

        int max = 8;
        for(int i=0; i<max; i++) {
            servicePool.addRequest(new MatrixMultiplyParameters(size, iterations));
            System.out.println(servicePool.getResponse());
        }
    } // end method sequentialTest

    /**
     * Tests the matrix multiplication methods. Launches the workers in parallel.
     *
	 * @param	size		the size of the matrix to be multiplied by itself
	 * @param	iterations	the number of times to perform the multiplication
     */
    private static void parallelTest(int size, int iterations) throws Exception {
        MatrixMultiplyServicePool servicePool = new MatrixMultiplyServicePool(5, 10);

        int max = 8;
        for(int i=0; i<max; i++) {
            servicePool.addRequest(new MatrixMultiplyParameters(size, iterations));
        }
        for(int i=0; i<max; i++) {
            System.out.println(servicePool.getResponse());
        }
    } // end method parallelTest
	
    /**
     * Tests the matrix determinant calculations. Launches the workers one after the other.
     *
	 * @param	size	the size of the matrix whose determinant will be calculated
	 */
    private static void sequentialDeterminantTest(int size) throws Exception {
        MatrixDeterminantServicePool servicePool = new MatrixDeterminantServicePool(5, 10);

        int max = 8;
        for(int i=0; i<max; i++) {
            servicePool.addRequest(new MatrixDeterminantParameters(size));
            System.out.println(servicePool.getResponse());
        }
    } // end method sequentialDeterminantTest

    /**
     * Tests the matrix determinant calculations. Launches the workers in parallel.
     *
	 * @param	size	the size of the matrix whose determinant will be calculated
     */
    private static void parallelDeterminantTest(int size) throws Exception {
        MatrixDeterminantServicePool servicePool = new MatrixDeterminantServicePool(5, 10);

        int max = 8;
        for(int i=0; i<max; i++) {
            servicePool.addRequest(new MatrixDeterminantParameters(size));
        }
        for(int i=0; i<max; i++) {
            System.out.println(servicePool.getResponse());
        }
    } // end method parallelDeterminantTest
} // end class TestService