/**
 * <b>[TODO]</b> Matrix multiplier worker.
 * Implement missing functionality in this class.
 */
public class MatrixMultiplyWorker extends AbstractServiceWorker {

    private double[][] a, b, c;	// the multiples and product of the multiplication
    private int mSize;			// the size of the matrices
	private int iterations; 	// the number of times to perform multiplication

    public MatrixMultiplyWorker(MatrixMultiplyParameters parameters, MsgQ resultQ) {
        super(parameters, resultQ);
        this.mSize = parameters.matrixSize; 
		this.iterations = parameters.iterations; 
    }

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
	} // end method run
} // end class MatrixMultiplyWorker 