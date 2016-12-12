/**
 * Simple class (struct) to pass on parameters for the matrix determinant computation.
 * @author	Franklin D. Worrell
 * @version 9 November 2016
 */
public class MatrixDeterminantParameters implements java.io.Serializable {

    public int matrixSize;	// the size of the matrix to build and use

    /**
     * @param 	matrixSize    size of the matrix
     */
    public MatrixDeterminantParameters(int matrixSize) {
            this.matrixSize = matrixSize;
    } // end constructor
} // end class MatrixDeterminantParameters
