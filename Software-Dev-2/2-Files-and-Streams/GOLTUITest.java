/**
 * A JUnit test class that tests the methods of GameOfLifeTUI
 * that are appropriate for unit testing, namely getMinimumXY, 
 * getMaximumXY, transformCoordinates, and wrapCoordinates. 
 * 
 * Assignment: Homework 2 - Files and Streams
 * Class: GOLTUITest
 * Author: Franklin D. Worrell
 * Date: 9/18/2015
 */ 

import java.util.ArrayList; 
import java.util.Arrays; 
import static org.junit.Assert.assertEquals; 
import org.junit.Test; 

/**
 * A JUNit test class for the GameOfLifeTUI class.
 */ 
public class GOLTUITest {
	
	/**
	 * Initializes variables for use in the tests.
	 */ 
	// First pattern requires no shifting, but requires wrapping. 
	// Minimum x-coordinate is 2; minimum y-coordinate is 0.  
	// Maximum x-coordinate is 45; maximum y-coordinate is 39. 
	ArrayList<int[]> pattern1 = new ArrayList<int[]>(Arrays.asList(
		new int[] {12, 18}, new int[] {5, 6}, new int[] {23, 39}, 
		new int[] {45, 8}, new int[] {31, 27}, new int[] {2, 0}));

	// Second pattern requires no vertical change, but it does 
	// require wrapping after horizontal change. 
	// Minimum x-coordinate is -33; minimum y-coordinate is 3. 
	// Maximum x-coordinate is 20; maximum y-coordinate is 37. 
	ArrayList<int[]> pattern2 = new ArrayList<int[]>(Arrays.asList(
		new int[] {1, 22}, new int[] {20, 3}, new int[] {-16, 37}, 
		new int[] {-33, 18}, new int[] {-26, 26}, new int[] {-1, 9}));

	// Third pattern requires neither horizontal change nor any
	// wrapping. 
	// Minimum x-coordinate is 20; minimum y-coordinate is -17. 
	// Maximum x-coordinate is 39; maximum y-coordinate is 22. 
	ArrayList<int[]> pattern3 = new ArrayList<int[]>(Arrays.asList(
		new int[] {27, 13}, new int[] {39, -17}, new int[] {30, 22}, 
		new int[] {31, 22}, new int[] {20, 3}, new int[] {30, 13}));

	// Fourth pattern requires both horizontal and vertical change
	// as well as wrapping after the shift. 
	// Minimum x-coordinate is -9; minimum y-coordinate is -22. 
	// Maximum x-coordinate is 32; maximum y-coordinate is 13. 
	ArrayList<int[]> pattern4 = new ArrayList<int[]>(Arrays.asList(
		new int[] {-9, 13}, new int[] {-1, 12}, new int[] {1, -20}, 
		new int[] {32, 0}, new int[] {0, -22}, new int[] {25, 2}));
	
	// Fifth pattern requires both horizontal and vertical change 
	// as well as extensive wrapping. 
	// Minimum x-coordinate is -54; minimum y-coordinate is -67. 
	// Maximum x-coordinate is 53; maximum y-coordinate is 62. 
	ArrayList<int[]> pattern5 = new ArrayList<int[]>(Arrays.asList(
		new int[] {-4, -1}, new int[] {-54, -60}, new int[] {3, 62}, 
		new int[] {-3, -23}, new int[] {-50, 3}, new int[] {53, -67})); 
	
	
	/**
	 * Initializes manually transformed patterns for comparison in tests.
	 */ 
	// x-shift = 0 & y-shift = 0
	ArrayList<int[]> pattern1Transformed = new ArrayList<int[]>(Arrays.asList(
		new int[] {12, 18}, new int[] {5, 6}, new int[] {23, 39}, 
		new int[] {45, 8}, new int[] {31, 27}, new int[] {2, 0}));
	
	// x-shift = 33 & y-shift = 0
	ArrayList<int[]> pattern2Transformed = new ArrayList<int[]>(Arrays.asList(
		new int[] {34, 22}, new int[] {53, 3}, new int[] {17, 37}, 
		new int[] {0, 18}, new int[] {7, 26}, new int[] {32, 9}));

	// x-shift = 0 & y-shift = 17
	ArrayList<int[]> pattern3Transformed = new ArrayList<int[]>(Arrays.asList(
		new int[] {27, 30}, new int[] {39, 0}, new int[] {30, 39}, 
		new int[] {31, 39}, new int[] {20, 20}, new int[] {30, 30}));
	
	// x-shift = 9 & y-shift = 22
	ArrayList<int[]> pattern4Transformed = new ArrayList<int[]>(Arrays.asList(
		new int[] {0, 35}, new int[] {8,34}, new int[] {10,2}, 
		new int[] {41, 22}, new int[] {9, 0}, new int[] {34, 24}));
	
	// x-shift = 54 & y-shift = 67
	ArrayList<int[]> pattern5Transformed = new ArrayList<int[]>(Arrays.asList(
		new int[] {50, 66}, new int[] {0, 7}, new int[] {57, 129}, 
		new int[] {51, 44}, new int[] {4, 70}, new int[] {107, 0})); 

	
	/**
	 * Initializes manually wrapped transformed patterns for comparison 
	 * in tests. 
	 */ 
	ArrayList<int[]> pattern1TransWrapped = new ArrayList<int[]>(Arrays.asList(
		new int[] {12, 18}, new int[] {5, 6}, new int[] {23, 39}, 
		new int[] {5, 8}, new int[] {31, 27}, new int[] {2, 0}));
	
	ArrayList<int[]> pattern2TransWrapped = new ArrayList<int[]>(Arrays.asList(
		new int[] {34, 22}, new int[] {13, 3}, new int[] {17, 37}, 
		new int[] {0, 18}, new int[] {7, 26}, new int[] {32, 9}));

	ArrayList<int[]> pattern3TransWrapped = new ArrayList<int[]>(Arrays.asList(
		new int[] {27, 30}, new int[] {39, 0}, new int[] {30, 39}, 
		new int[] {31, 39}, new int[] {20, 20}, new int[] {30, 30}));
	
	ArrayList<int[]> pattern4TransWrapped = new ArrayList<int[]>(Arrays.asList(
		new int[] {0, 35}, new int[] {8,34}, new int[] {10,2}, 
		new int[] {1, 22}, new int[] {9, 0}, new int[] {34, 24}));
	
	ArrayList<int[]> pattern5TransWrapped = new ArrayList<int[]>(Arrays.asList(
		new int[] {10, 26}, new int[] {0, 7}, new int[] {17, 9}, 
		new int[] {11, 4}, new int[] {4, 30}, new int[] {27, 0})); 


	@Test
	/**
	 * Tests getMinimumXY method of GameOfLifeTUI class.
	 */ 
	public void testGetMinimumXY() { 
		// Tests getMinimumXY(pattern1). 
		assertEquals(0, GameOfLifeTUI.getMinimumXY(pattern1)[0]); 
		assertEquals(0, GameOfLifeTUI.getMinimumXY(pattern1)[1]); 
		
		// Tests getMinimumXY(pattern2). 
		assertEquals(-33, GameOfLifeTUI.getMinimumXY(pattern2)[0]); 
		assertEquals(0, GameOfLifeTUI.getMinimumXY(pattern2)[1]); 
		
		// Tests getMinimumXY(pattern3). 
		assertEquals(0, GameOfLifeTUI.getMinimumXY(pattern3)[0]); 
		assertEquals(-17, GameOfLifeTUI.getMinimumXY(pattern3)[1]); 
		
		// Tests getMinimumXY(pattern4). 
		assertEquals(-9, GameOfLifeTUI.getMinimumXY(pattern4)[0]); 
		assertEquals(-22, GameOfLifeTUI.getMinimumXY(pattern4)[1]); 
		
		// Tests getMinimumXY(pattern5). 
		assertEquals(-54, GameOfLifeTUI.getMinimumXY(pattern5)[0]); 
		assertEquals(-67, GameOfLifeTUI.getMinimumXY(pattern5)[1]); 
	} // end method testGetXYMinimum
	
	
	@Test
	/** 
	 * Tests transformCoordinates method of GameOfLifeTUI class.
	 */ 
	public void testTransformCoordinates() {
		// Tests transformCoordinates(pattern1).		
		ArrayList<int[]> testTrans1 = GameOfLifeTUI.transformCoordinates(pattern1); 
		for (int cell = 0; cell < testTrans1.size(); cell++) {
			int[] testTrans1Cell = testTrans1.get(cell); 
			assertEquals(pattern1Transformed.get(cell)[0], testTrans1Cell[0]); 
			assertEquals(pattern1Transformed.get(cell)[1], testTrans1Cell[1]); 
		}
		
		// Tests transformCoordinates(pattern2). 
		ArrayList<int[]> testTrans2 = GameOfLifeTUI.transformCoordinates(pattern2); 
		for (int cell = 0; cell < testTrans2.size(); cell++) {
			int[] testTrans2Cell = testTrans2.get(cell); 
			assertEquals(pattern2Transformed.get(cell)[0], testTrans2Cell[0]); 
			assertEquals(pattern2Transformed.get(cell)[1], testTrans2Cell[1]); 
		}
		
		// Tests transformCoordinates(pattern3). 
		ArrayList<int[]> testTrans3 = GameOfLifeTUI.transformCoordinates(pattern3); 
		for (int cell = 0; cell < testTrans3.size(); cell++) {
			int[] testTrans3Cell = testTrans3.get(cell); 
			assertEquals(pattern3Transformed.get(cell)[0], testTrans3Cell[0]); 
			assertEquals(pattern3Transformed.get(cell)[1], testTrans3Cell[1]); 
		}

		// Tests transformCoordinates(pattern4). 
		ArrayList<int[]> testTrans4 = GameOfLifeTUI.transformCoordinates(pattern4); 
		for (int cell = 0; cell < testTrans4.size(); cell++) {
			int[] testTrans4Cell = testTrans4.get(cell); 
			assertEquals(pattern4Transformed.get(cell)[0], testTrans4Cell[0]); 
			assertEquals(pattern4Transformed.get(cell)[1], testTrans4Cell[1]); 
		}

		// Tests transformCoordinates(pattern5). 
		ArrayList<int[]> testTrans5 = GameOfLifeTUI.transformCoordinates(pattern5); 
		for (int cell = 0; cell < testTrans5.size(); cell++) {
			int[] testTrans5Cell = testTrans5.get(cell); 
			assertEquals(pattern5Transformed.get(cell)[0], testTrans5Cell[0]); 
			assertEquals(pattern5Transformed.get(cell)[1], testTrans5Cell[1]); 
		}
	} // end method testTransformCoordinates
	
	
	@Test
	/**
	 * Tests getMaximumXY method of GameOfLifeTUI class.
	 */ 
	public void testGetMaximumXY() {
		// Tests getMaximumXY(pattern1). 
		assertEquals(45, GameOfLifeTUI.getMaximumXY(pattern1)[0]); 
		assertEquals(39, GameOfLifeTUI.getMaximumXY(pattern1)[1]); 
		
		// Tests getMaximumXY(pattern2). 
		assertEquals(20, GameOfLifeTUI.getMaximumXY(pattern2)[0]); 
		assertEquals(37, GameOfLifeTUI.getMaximumXY(pattern2)[1]); 
		
		// Tests getMaximumXY(pattern3). 
		assertEquals(39, GameOfLifeTUI.getMaximumXY(pattern3)[0]); 
		assertEquals(22, GameOfLifeTUI.getMaximumXY(pattern3)[1]); 
		
		// Tests getMaximumXY(pattern4). 
		assertEquals(32, GameOfLifeTUI.getMaximumXY(pattern4)[0]); 
		assertEquals(13, GameOfLifeTUI.getMaximumXY(pattern4)[1]); 
		
		// Tests getMaximumXY(pattern5). 
		assertEquals(53, GameOfLifeTUI.getMaximumXY(pattern5)[0]); 
		assertEquals(62, GameOfLifeTUI.getMaximumXY(pattern5)[1]); 	
	} // end method testGetMaximumXY
	
	
	@Test
	/**
	 * Tests method wrapCoordinates of GameOfLifeTUI class. 
	 */ 
	public void testWrapCoordinates() {
		// Tests wrapCoordinates(pattern1Transformed).		
		ArrayList<int[]> transWrap1 = GameOfLifeTUI.wrapCoordinates(pattern1Transformed); 
		for (int cell = 0; cell < transWrap1.size(); cell++) {
			int[] transWrap1Cell = transWrap1.get(cell); 
			assertEquals(pattern1TransWrapped.get(cell)[0], transWrap1Cell[0]); 
			assertEquals(pattern1TransWrapped.get(cell)[1], transWrap1Cell[1]); 
		}
		
		// Tests transformCoordinates(pattern2Transformed). 
		ArrayList<int[]> transWrap2 = GameOfLifeTUI.wrapCoordinates(pattern2Transformed); 
		for (int cell = 0; cell < transWrap2.size(); cell++) {
			int[] transWrap2Cell = transWrap2.get(cell); 
			assertEquals(pattern2TransWrapped.get(cell)[0], transWrap2Cell[0]); 
			assertEquals(pattern2TransWrapped.get(cell)[1], transWrap2Cell[1]); 
		}
		
		// Tests transformCoordinates(pattern3Transformed). 
		ArrayList<int[]> transWrap3 = GameOfLifeTUI.wrapCoordinates(pattern3Transformed); 
		for (int cell = 0; cell < transWrap3.size(); cell++) {
			int[] transWrap3Cell = transWrap3.get(cell); 
			assertEquals(pattern3TransWrapped.get(cell)[0], transWrap3Cell[0]); 
			assertEquals(pattern3TransWrapped.get(cell)[1], transWrap3Cell[1]); 
		}

		// Tests transformCoordinates(pattern4Transformed). 
		ArrayList<int[]> transWrap4 = GameOfLifeTUI.wrapCoordinates(pattern4Transformed); 
		for (int cell = 0; cell < transWrap4.size(); cell++) {
			int[] transWrap4Cell = transWrap4.get(cell); 
			assertEquals(pattern4TransWrapped.get(cell)[0], transWrap4Cell[0]); 
			assertEquals(pattern4TransWrapped.get(cell)[1], transWrap4Cell[1]); 
		}

		// Tests transformCoordinates(pattern5Transformed). 
		ArrayList<int[]> transWrap5 = GameOfLifeTUI.wrapCoordinates(pattern5Transformed); 
		for (int cell = 0; cell < transWrap5.size(); cell++) {
			int[] transWrap5Cell = transWrap5.get(cell); 
			assertEquals(pattern5TransWrapped.get(cell)[0], transWrap5Cell[0]); 
			assertEquals(pattern5TransWrapped.get(cell)[1], transWrap5Cell[1]); 
		}
	} // end method testWrapCoordinates
} // end class GOLTUITest