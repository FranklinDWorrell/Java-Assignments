/**
 * A class to create a new thread to compute a partial sum of a series and to return that 
 * partial sum after it has been computed. 
 *
 * Assignment: Bonus Homework - Concurrency
 * Class: SumThread
 * Author: Franklin D. Worrell
 * Date: 12/11/2015
 */ 

import java.math.BigDecimal;

/**
 * A class that creates a new thread to process part of the Series for the multithreaded
 * SeriesSolver method computeSum. 
 */ 
public class SumThread extends Thread {
	private BigDecimal partialSum; 
	private Series series; 
	private int low; 
	private int high; 
	
	/** 
	 * Constructor.
	 *
	 * @param	wholeSeries	the series a partial sum is being computed over
	 * @param	low			the beginning number of the partial series
	 * @param	high		the ending number of the partial series
	 */ 
	public SumThread(Series wholeSeries, int low, int high) {
		this.series = wholeSeries; 
		this.low = low; 
		this.high = high; 
		this.partialSum = new BigDecimal(0); 
	} // end constructor
	
	/**
	 * Calls computeSum from SeriesSolver. 
	 */ 
	@Override
	public void run() {
		this.partialSum = SeriesSolver.computeSum(this.series, this.low, this.high); 
	} // end method run
	
	/**
	 * Returns the sum calculated by the run method. Making the method synchronized 
	 * ensure that it will not attempt to return until the calculation has finished.
	 *
	 * @return 	the partial sum computed by call to SeriesSolver.computeSum
	 */ 
	public synchronized BigDecimal getPartialSum() {
		return this.partialSum; 
	} // end method getPartialSum
} // end inner class sumThread
