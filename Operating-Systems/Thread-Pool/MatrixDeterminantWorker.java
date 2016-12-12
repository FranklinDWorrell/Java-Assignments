/**
 * Performs the matrix determinant calculation and stores the result in the
 * <code>MsgQ</code>. 
 * @author	Franklin D. Worrell
 * @version	11 November 2016
 */
public class MatrixDeterminantWorker extends AbstractServiceWorker {

    private double[][] a;	// the matrix whose determinant will be calculated
    private int mSize;		// the size of the matrix

    /**
	 * Constructor initializes <code>mSize</code> to size of matrix to use.
	 * @param	parameters	the MatrixDeterminantParameters object carrying matrix size
	 * @param	resultQ		the queue to store execution time in
	 */ 
	public MatrixDeterminantWorker(MatrixDeterminantParameters parameters, MsgQ resultQ) {
        super(parameters, resultQ);
        this.mSize = parameters.matrixSize; 
    } // end constructor

    /**
     * Initializes the matrix based on the size parameter.
     */
    private void initMatrix() {
		this.a = new double[this.mSize][this.mSize]; 

        for (int i = 0; i < this.mSize; i++) {
            for (int j = 0; j < this.mSize; j++) {
				// Populate the matrix. 
                this.a[i][j] = (i + 1) * (j + 1);
            }
        }
	} // end method initMatrix

    /**
     * Calculates the determinant of the matrix. Calls recursive helper method.
     */
    private void findMatrixDeterminant() {
		findMatrixDeterminant(this.a, this.mSize); 
	} // end method findMatrixDeterminant
	
	/**
	 * Recursively finds determinant of a matrix. 
	 * 
	 * @require	matrix.length == matrix[0].length
	 * @require	size >= 2
	 * @param	matrix	the matrix whose determinant is to be calculated
	 * @param	size	the dimensions of the matrix
	 * @return	the determinant of the matrix
	 */ 
	private double findMatrixDeterminant(double[][] matrix, int size) {
		double sum = 0; // the determinant to return
		
		// Base case: 2x2 Matrix, find determinant. 
		if (size == 2) {
			sum = (matrix[0][0] * matrix[1][1]) - (matrix[0][1] * matrix[1][0]); 
		} 
		
		// Complex case
		else {
			int oscillator = 1; 
			for (int i = 0; i < size; i++) {
				sum += (oscillator * matrix[0][i] * findMatrixDeterminant(getCofactor(matrix, size, i), size - 1)); 
				oscillator *= -1; // Flip sign of next addend. 
			} 
		} 
		
		return sum;
	} // end overload findMatrixDeterminant 
	
	/**
	 * Finds a cofactor matrix used for calculation of the determinant.
	 *
	 * @param	matrix	the matrix whose determinant is being calculated
	 * @param	size	the size of the matrix
	 * @param	column	index of top-row element of matrix being considered
	 * @return	the cofactor of the matrix, given the top-row element
	 */ 
	private double[][] getCofactor(double[][] matrix, int size, int column) { 
		double[][] cofactor = new double[size - 1][size - 1]; 
		
		for (int i = 0; i < (size - 1); i++) { // rows
			for (int j = 0; j < size; j++) { // columns
				// Skip the top-row element's column. 
				if (j < column) {
					cofactor[i][j] = matrix[i + 1][j]; 
				} 
				else if (j > column) {
					cofactor[i][j - 1] = matrix[i + 1][j]; 
				} 
			}
		}
		
		return cofactor; 
	} // end method getCofactor

    /**
     * Invokes the initialization of source matrices and the execution of the required number of iterations.
     * The result is a <pre>Long</pre> object, which contains the execution time in milliseconds for all computations, <b>without</b> the initialization.
     * <b>TODO:</b> Implement this method.
     */
    public void run() {
		// Initialize the matrix. 
		this.initMatrix(); 
		
		// Start the timer. 
        long start = System.currentTimeMillis();
		
		// Calculate the determinant. 
		this.findMatrixDeterminant(); 
	
		// Stop the timer. 
        long end = System.currentTimeMillis();
		
		// Push execution time to message queue. 
		this.resultQ.append(end - start); 
	} // end method run
} // end class MatrixDeterminantWorker 