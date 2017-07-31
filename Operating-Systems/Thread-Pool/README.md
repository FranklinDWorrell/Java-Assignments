Implementation of a thread pool that performs either matrix multiplication or 
calculation of the determinant of a matrix. These operations can be performed
in parallel or sequentially, and this project was designed to assist in 
comparing runtimes in this cases. Program output is time in milliseconds for 
each operation. 

I implemented TestService, MatrixMultiplyWorker, MatrixDeterminantWorker, and 
BasicMsgQ. The other classes were provided by the course instructor. 

Written for graduate level course on Operating Systems (CSCI 5401). 

Franklin D. Worrell
12 December 2015