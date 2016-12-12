/**
 * A JUnit test class for class Pokemon.
 * 
 * Assignment: Homework 1 - Pokemon reboot
 * Class: JUnitPokemonTest
 * Author: Franklin D. Worrell
 * Date: 9/9/2015
 */ 

import static org.junit.Assert.assertEquals; 
import static org.junit.Assert.assertTrue; 
import org.junit.Test; 
import org.junit.Before; 
import java.lang.IllegalArgumentException;

/**
 * A test class for class Pokemon.
 */ 
public class JUnitPokemonTest {
	
	private Pokemon pokemon1, 
	                pokemon2,
	                pokemon3,
	                pokemon4,
	                pokemon5,
	                pokemon6; 
	
	@Before
	/**
	 * Initializes variables for test fixture, 
	 * thereby testing the constructor method 
	 * for class Pokemon. 
	 */
	public void setUp() {
		pokemon1 = new Pokemon("_Jake8Snake_", 1, 1, 1); 
		pokemon2 = new Pokemon("Billy", 50, 50, 10); 
		pokemon3 = new Pokemon("  * * * *   ", 100, 100, 20); 
	} // end method setUp
	
	
	
	
	@Test(expected = IllegalArgumentException.class)
	/**
	 * Test constructor for Pokemon class to ensure that 
	 * exceptions are thrown when values outside the appropriate
	 * ranges are provided for instance variables initialSpeed,
	 * initialTolerance, and initialPower. Uses the "expected"
	 * parameter of the @Test annotation. 
	 */ 
	public void testConstructorExceptions1() {
		// IllegalArgumentException for tolerance.
		pokemon4 = new Pokemon("ErrorTol", 101, 98, 15); 
		
		// IllegalArgumentException for speed. 
		pokemon5 = new Pokemon("ErrorSpeed", 75, -15, 15); 
		
		// IllegalArgumentException for power. 
		pokemon6 = new Pokemon("ErrorPower", 75, 53, 27); 
	} // end method testConstructorExceptions1
	
	
	
	
	@Test
	/**
	 * Tests attack method of class Pokemon to ensure that values within 
	 * the correct ranges are returned and that tolerance does not go 
	 * below zero. Also checks whether attack method correctly sets the
	 * isDefending instance variable to false. Must ensure that tolerance 
	 * does not go below zero here, because getHit method of Pokemon class 
	 * is private. 
	 */ 
	public void testAttack() { 
		// Since pokemon1 was initialized with only 1 tolerance, it is easiest
		// test that tolerance is always >= 0. The tests with pokemon1 focus
		// primarily on this condition. 
		int tempPokemon1Tolerance = pokemon1.tolerance(); 
		pokemon3.attack(pokemon1); 
		assertTrue((tempPokemon1Tolerance >= pokemon1.tolerance()) && 
			(pokemon1.tolerance() >= 0)); 	
		
		tempPokemon1Tolerance = pokemon1.tolerance(); 
		pokemon3.attack(pokemon1); 
		assertTrue((tempPokemon1Tolerance >= pokemon1.tolerance()) && 
			(pokemon1.tolerance() >= 0)); 	
		
		tempPokemon1Tolerance = pokemon1.tolerance(); 
		pokemon3.attack(pokemon1); 
		assertTrue((tempPokemon1Tolerance >= pokemon1.tolerance()) && 
			(pokemon1.tolerance() >= 0)); 	
		
		tempPokemon1Tolerance = pokemon1.tolerance(); 
		pokemon3.attack(pokemon1); 
		assertTrue((tempPokemon1Tolerance >= pokemon1.tolerance()) && 
			(pokemon1.tolerance() >= 0)); 	
		
		tempPokemon1Tolerance = pokemon1.tolerance(); 
		pokemon3.attack(pokemon1); 
		assertTrue((tempPokemon1Tolerance >= pokemon1.tolerance()) && 
			(pokemon1.tolerance() >= 0)); 	
		
		tempPokemon1Tolerance = pokemon1.tolerance(); 
		pokemon3.attack(pokemon1); 
		assertTrue((tempPokemon1Tolerance >= pokemon1.tolerance()) && 
			(pokemon1.tolerance() >= 0)); 	
		
		// Additional tests with pokemon2 and pokemon3. More rigorouly
		// tests to ensure that all possible outcomes of attack method
		// function properly. Hence, every possible combination of 
		// attacking and defending between these Pokemon is tested. 
		// Also checks to make sure attack method sets isDefending
		// variable to false. 
		int tempPokemon2Tolerance = pokemon2.tolerance(); 
		int tempPokemon3Tolerance = pokemon3.tolerance(); 
		pokemon2.attack(pokemon3); 
		assertTrue((tempPokemon2Tolerance >= pokemon2.tolerance()) && 
			(pokemon2.tolerance() >= 0)); 	
		assertTrue((tempPokemon3Tolerance >= pokemon3.tolerance()) && 
			(pokemon3.tolerance() >= 0)); 	
		
		tempPokemon2Tolerance = pokemon2.tolerance(); 
		tempPokemon3Tolerance = pokemon3.tolerance(); 
		pokemon3.attack(pokemon2); 
		assertTrue((tempPokemon2Tolerance >= pokemon2.tolerance()) && 
			(pokemon2.tolerance() >= 0)); 	
		assertTrue((tempPokemon3Tolerance >= pokemon3.tolerance()) && 
			(pokemon3.tolerance() >= 0)); 	

		tempPokemon2Tolerance = pokemon2.tolerance(); 
		tempPokemon3Tolerance = pokemon3.tolerance(); 
		pokemon3.defend(); 
		pokemon2.attack(pokemon3); 
		assertTrue((tempPokemon2Tolerance >= pokemon2.tolerance()) && 
			(pokemon2.tolerance() >= 0)); 	
		assertTrue((tempPokemon3Tolerance >= pokemon3.tolerance()) && 
			(pokemon3.tolerance() >= 0)); 	
		
		tempPokemon2Tolerance = pokemon2.tolerance(); 
		tempPokemon3Tolerance = pokemon3.tolerance(); 
		pokemon2.defend(); 
		pokemon3.attack(pokemon2); 
		assertTrue((tempPokemon2Tolerance >= pokemon2.tolerance()) && 
			(pokemon2.tolerance() >= 0)); 	
		assertTrue((tempPokemon3Tolerance >= pokemon3.tolerance()) && 
			(pokemon3.tolerance() >= 0)); 	

		tempPokemon2Tolerance = pokemon2.tolerance(); 
		tempPokemon3Tolerance = pokemon3.tolerance(); 
		pokemon3.defend();
		pokemon2.attack(pokemon3); 
		assertTrue((tempPokemon2Tolerance >= pokemon2.tolerance()) && 
			(pokemon2.tolerance() >= 0)); 	
		assertTrue((tempPokemon3Tolerance >= pokemon3.tolerance()) && 
			(pokemon3.tolerance() >= 0)); 	
		
		tempPokemon2Tolerance = pokemon2.tolerance(); 
		tempPokemon3Tolerance = pokemon3.tolerance(); 
		pokemon2.defend(); 
		pokemon3.attack(pokemon2); 
		assertTrue((tempPokemon2Tolerance >= pokemon2.tolerance()) && 
			(pokemon2.tolerance() >= 0)); 	
		assertTrue((tempPokemon3Tolerance >= pokemon3.tolerance()) && 
			(pokemon3.tolerance() >= 0)); 	

		// Makes sure attack method sets isDefending to false. 
		tempPokemon2Tolerance = pokemon2.tolerance(); 
		tempPokemon3Tolerance = pokemon3.tolerance(); 
		pokemon2.attack(pokemon3); 
		assertEquals(false, pokemon2.getIsDefending()); 
		assertTrue((tempPokemon2Tolerance >= pokemon2.tolerance()) && 
			(pokemon2.tolerance() >= 0)); 	
		assertTrue((tempPokemon3Tolerance >= pokemon3.tolerance()) && 
			(pokemon3.tolerance() >= 0)); 	
		
		// Makes sure attack method sets isDefending to false. 
		tempPokemon2Tolerance = pokemon2.tolerance(); 
		tempPokemon3Tolerance = pokemon3.tolerance(); 
		pokemon3.attack(pokemon2); 
		assertEquals(false, pokemon3.getIsDefending()); 
		assertTrue((tempPokemon2Tolerance >= pokemon2.tolerance()) && 
			(pokemon2.tolerance() >= 0)); 	
		assertTrue((tempPokemon3Tolerance >= pokemon3.tolerance()) && 
			(pokemon3.tolerance() >= 0)); 	

		// Makes sure attack method sets isDefending to false. 
		tempPokemon2Tolerance = pokemon2.tolerance(); 
		tempPokemon3Tolerance = pokemon3.tolerance(); 
		pokemon3.defend(); 
		pokemon2.defend(); 
		pokemon2.attack(pokemon3); 
		assertEquals(false, pokemon2.getIsDefending()); 
		assertTrue((tempPokemon2Tolerance >= pokemon2.tolerance()) && 
			(pokemon2.tolerance() >= 0)); 	
		assertTrue((tempPokemon3Tolerance >= pokemon3.tolerance()) && 
			(pokemon3.tolerance() >= 0)); 	
		
		// Makes sure attack method sets isDefending to false. 
		tempPokemon2Tolerance = pokemon2.tolerance(); 
		tempPokemon3Tolerance = pokemon3.tolerance(); 
		pokemon2.defend(); 
		pokemon3.defend(); 
		pokemon3.attack(pokemon2); 
		assertEquals(false, pokemon3.getIsDefending()); 
		assertTrue((tempPokemon2Tolerance >= pokemon2.tolerance()) && 
			(pokemon2.tolerance() >= 0)); 	
		assertTrue((tempPokemon3Tolerance >= pokemon3.tolerance()) && 
			(pokemon3.tolerance() >= 0)); 	

		tempPokemon2Tolerance = pokemon2.tolerance(); 
		tempPokemon3Tolerance = pokemon3.tolerance(); 
		pokemon3.defend(); 
		pokemon2.attack(pokemon3); 
		assertTrue((tempPokemon2Tolerance >= pokemon2.tolerance()) && 
			(pokemon2.tolerance() >= 0)); 	
		assertTrue((tempPokemon3Tolerance >= pokemon3.tolerance()) && 
			(pokemon3.tolerance() >= 0)); 	
		
		tempPokemon2Tolerance = pokemon2.tolerance(); 
		tempPokemon3Tolerance = pokemon3.tolerance(); 
		pokemon2.defend();
		pokemon3.attack(pokemon2); 
		assertTrue((tempPokemon2Tolerance >= pokemon2.tolerance()) && 
			(pokemon2.tolerance() >= 0)); 	
		assertTrue((tempPokemon3Tolerance >= pokemon3.tolerance()) && 
			(pokemon3.tolerance() >= 0)); 	
	} // end method testAttack
} // end class JUnitPokemonTest