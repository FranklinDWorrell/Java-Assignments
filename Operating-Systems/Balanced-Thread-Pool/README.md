Implementation of a balanced thread pool that performs either matrix 
multiplication or calculation of the determinant of a matrix. The size of the 
thread pool is tailored to the number of CPU cores in the machine. The 
calculations are performed in thread pools that reuse threads and others that 
spawn new threads for each request. This project was designed to assist in 
comparing runtimes in these cases. Program output is time in milliseconds for 
each operation. 

I implemented TestService, MatrixMultiplyWorker, BalancedMMServicePool, 
ReuseBMMServicePool, and BasicMsgQ. The other classes were provided by the 
course instructor. 

Written for graduate level course on Operating Systems (CSCI 5401). 

Franklin D. Worrell
12 December 2015