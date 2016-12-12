/**
 * A JUnit Test class for the SeriesSolver class testing an implementation of the 
 * overriden computeSum method by comparing the output of the two implementations. 
 * 
 * Assignment: Bonus Homework - Concurrency
 * Class: SeriesSolverTester
 * Author: Franklin D. Worrell
 * Date: 12/11/2015
 */
 
import java.math.BigDecimal; 
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class SeriesSolverTester {
	/**
	 * A method to test the output of the overloaded computeSum method of class 
	 * SeriesSolver. Compares output of multithreaded version against the output of the
	 * single-threaded version. 
	 */ 
	@Test
	public void testComputeSum() {
		/** 
		 * First block of tests tests that the output is the same when the multithreaded
		 * implementation is called using only a single thread. 
		 */ 
		// Using Series.TWO. 
		assertEquals(SeriesSolver.computeSum(Series.TWO, 0, 0), 
			SeriesSolver.computeSum(Series.TWO, 0, 0, 1));
		assertEquals(SeriesSolver.computeSum(Series.TWO, 0, 1), 
			SeriesSolver.computeSum(Series.TWO, 0, 1, 1)); 
		assertEquals(SeriesSolver.computeSum(Series.TWO, 5, 16), 
			SeriesSolver.computeSum(Series.TWO, 5, 16, 1)); 
		assertEquals(SeriesSolver.computeSum(Series.TWO, 501, 507), 
			SeriesSolver.computeSum(Series.TWO, 501, 507, 1)); 
		assertEquals(SeriesSolver.computeSum(Series.TWO, 1095, 1178), 
			SeriesSolver.computeSum(Series.TWO, 1095, 1178, 1)); 
		
		// Using Series.PI.
		assertEquals(SeriesSolver.computeSum(Series.PI, 0, 0), 
			SeriesSolver.computeSum(Series.PI, 0, 0, 1)); 
		assertEquals(SeriesSolver.computeSum(Series.PI, 0, 6543), 
			SeriesSolver.computeSum(Series.PI, 0, 6543, 1)); 
		assertEquals(SeriesSolver.computeSum(Series.PI, 5976, 10017), 
			SeriesSolver.computeSum(Series.PI, 5976, 10017, 1)); 
		assertEquals(SeriesSolver.computeSum(Series.PI, 329, 1094), 
			SeriesSolver.computeSum(Series.PI, 329, 1094, 1)); 
		
		/**
		 * Second block of tests test that the output is the same when the multithreaded
		 * implementation is called using multiple threads. 
		 */ 
		// Using Series.TWO 
		assertEquals(SeriesSolver.computeSum(Series.TWO, 15, 21), 
			SeriesSolver.computeSum(Series.TWO, 15, 21, 3)); 
		assertEquals(SeriesSolver.computeSum(Series.TWO, 941, 1102), 
			SeriesSolver.computeSum(Series.TWO, 941, 1102, 7)); 
		assertEquals(SeriesSolver.computeSum(Series.TWO, 0, 257), 
			SeriesSolver.computeSum(Series.TWO, 0, 257, 4)); 
		assertEquals(SeriesSolver.computeSum(Series.TWO, 1048, 1050), 
			SeriesSolver.computeSum(Series.TWO, 1048, 1050, 2)); 
		assertEquals(SeriesSolver.computeSum(Series.TWO, 0, 342), 
			SeriesSolver.computeSum(Series.TWO, 0, 342, 12)); 
		assertEquals(SeriesSolver.computeSum(Series.TWO, 156, 692), 
			SeriesSolver.computeSum(Series.TWO, 156, 692, 17)); 
		assertEquals(SeriesSolver.computeSum(Series.TWO, 15, 21), 
			SeriesSolver.computeSum(Series.TWO, 15, 21, 4)); 
			
		// Using Series.PI
		assertEquals(SeriesSolver.computeSum(Series.PI, 329, 1094), 
			SeriesSolver.computeSum(Series.PI, 329, 1094, 2)); 
		assertEquals(SeriesSolver.computeSum(Series.PI, 329, 1094), 
			SeriesSolver.computeSum(Series.PI, 329, 1094, 5)); 
		assertEquals(SeriesSolver.computeSum(Series.PI, 16783, 17979), 
			SeriesSolver.computeSum(Series.PI, 16783, 17979, 4)); 
		assertEquals(SeriesSolver.computeSum(Series.PI, 0, 194), 
			SeriesSolver.computeSum(Series.PI, 0, 194, 10)); 
		assertEquals(SeriesSolver.computeSum(Series.PI, 17183, 17187), 
			SeriesSolver.computeSum(Series.PI, 17183, 17187, 3)); 
		assertEquals(SeriesSolver.computeSum(Series.PI, 63, 163), 
			SeriesSolver.computeSum(Series.PI, 63, 163, 12)); 
		assertEquals(SeriesSolver.computeSum(Series.PI, 961, 1254), 
			SeriesSolver.computeSum(Series.PI, 961, 1254, 8)); 
		assertEquals(SeriesSolver.computeSum(Series.PI, 0, 13), 
			SeriesSolver.computeSum(Series.PI, 0, 13, 2)); 
	} // end method testComputeSum
} // end class SeriesSolverTest