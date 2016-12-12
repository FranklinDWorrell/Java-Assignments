/**
 * A class that computes the sum of an series. Has single- and multi-threaded methods. 
 *
 * Assignment: Bonus Homework - Concurrency
 * Class: SeriesSolver
 * Author: Franklin D. Worrell
 * Date: 12/11/2015
 */ 

import java.math.BigDecimal;
import java.util.ArrayList; 

/**
 * Utility class that computes approximations of series.
 */
public class SeriesSolver {
	
	/**
	 * Computes the series sequentially using only one thread.
	 *
	 * @param series The Series to solve.
	 * @param a The min n value to iterate to.
	 * @param b The max n value to iterate to.
	 * @return The sum of the series from a to b.
	 */
	public static BigDecimal computeSum(final Series series, final int a, final int b){
		if (a > b){
			throw new IllegalArgumentException("a cannot be greater than b.");
		}
		BigDecimal sum = new BigDecimal(0);
		for (int n = a; n <= b; n++){
			sum = sum.add(series.computeValue(n));
		}
		return sum;
	}
	
	/**
	 * Computes the series using multiple threads.
	 *
	 * SOMETHING WEIRD HAPPENS WHEN NUMBER OF THREADS IS TOO CLOSE TO b - a.
	 * UNSURE WHAT THIS BUG IS, BUT IT IS PERNICIOUS! 
	 *
	 * @param series The Series to solve.
	 * @param a The min n value to iterate to.
	 * @param b the max n value to iterate to.
	 * @param threads The number of threads to use (can be less if b-a is small).
	 * @return The sum of the series from a to b.
	 */
	public static BigDecimal computeSum(final Series series, final int a, final int b, final int threads){
		// Validate range of series. 
		if (a > b){
			throw new IllegalArgumentException("a cannot be greater than b.");
		}
		
		// Validate number of threads. 
		if (threads <= 0){
			throw new IllegalArgumentException("Number of threads must be positive.");
		} 
		
		// Declare variable to hold total and ArrayList to hold Runnables. 
		BigDecimal total = new BigDecimal(0); 
		ArrayList<SumThread> sumThreads = new ArrayList<SumThread>(); 
		
		// Break the series into the appropriate chunks. 
		int rangeOfSubs = (b - a) / threads; 
		
		// Feed those chunks into a thread. 
		int aNuStart = a; 
		int thread = 1; 
		while (thread < threads) {
			SumThread partialSum = new SumThread(series, aNuStart, (aNuStart + rangeOfSubs)); 
			sumThreads.add(partialSum); 
			thread++; 
			aNuStart += (rangeOfSubs + 1); 
		} 
		
		// To avoid integer division worries, last thread called outside of loop. 
		SumThread partialSum = new SumThread(series, aNuStart, b); 
		sumThreads.add(partialSum); 
		
		// Run each thread and return the sum. 
		executeThreads(sumThreads); 
		return getTotal(sumThreads);
	} // end method computeSum
	
	
	/**
	 * Runs each of the Threads passed in as an argument. Waits until they are done 
	 * executing before another method is allowed to access them. 
	 * 
	 * @param	threads	the ArrayList containing all the Threads to execute
	 */ 
	public static synchronized void executeThreads(ArrayList<SumThread> threads) {
		// Start each Thread running. 
		for (SumThread partial : threads) {
			partial.start(); 
		}
		
		// Wait for each thread to finish executing before releasing lock on Threads. 
		for (SumThread partial : threads) {
			try {
				partial.join(); 
			} 
			catch (InterruptedException e) {
				e.printStackTrace(); 
			}
		}
	} // end method executeThreads
	
	
	/**
	 * Calculates the total of a series by adding together all the partial sums computed 
	 * by the Threads passed in as an argument. 
	 *
	 * @param	threads	the ArrayList containing all the Threads to execute 
	 * @return	the sum of the series
	 */ 
	public static synchronized BigDecimal getTotal(ArrayList<SumThread> threads) {
		BigDecimal total = new BigDecimal(0); 
		
		// Get each partial sum and add it to the total. 
		for (SumThread partial : threads) {
			BigDecimal pSum = partial.getPartialSum(); 
			total = total.add(pSum); 
		} 
		
		return total; 
	} // end method getTotal
}