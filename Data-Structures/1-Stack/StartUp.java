/**
 * Contains the implementation of the infix to postfix algorithm. Main method
 * creates three infix strings and then calls the infix to postfix method to 
 * convert them. The results are printed, as are what the expected results were.
 * 
 * Assignment 1
 * Author: Franklin D. Worrell
 * Email: fdworrel@uno.edu
 * Date: February 25, 2016
 * Class: StartUp
 */ 

import java.util.NoSuchElementException; 

/** 
 * Contains main method that calls and tests the infix to postfix algorithm. 
 */ 
public class StartUp {
	// Create MyStack for use in infix-to-postfix conversion and some test Strings. 
	private static MyStack<Character> conversionStack = new MyStack<Character>(); 
	private static String infixString1 = "a+b*(c-d)"; 
	private static String infixString2 = "a+b*c+(d*e+f)*g"; 
	private static String infixString3 = "2+3*4*(5+6)+8/2"; 
	
	
	/**
	 * Main method calls infixToPostfix() on the test Strings and prints output. 
	 */ 
	public static void main(String[] args) {
		// Expected infixToPostfix() output: a b c d - * +
		System.out.println(infixString1 + " converted to postfix: " + 
								 infixToPostfix(infixString1)); 
		System.out.println("Expected infixToPostfix() output: a b c d - * +"); 
		System.out.println(); 
		
		// Expected infixToPostfix() output: a b c * + d e * f + g * + 
		System.out.println(infixString2 + " converted to postfix: " + 
								 infixToPostfix(infixString2)); 
		System.out.println("Expected infixToPostfix() output: a b c * + d e * f + g * +"); 
		System.out.println(); 
		
		// Expected infixToPostfix() output: 2 3 4 * 5 6 + * + 8 2 / +
		System.out.println(infixString3 + " converted to postfix: " + 
								 infixToPostfix(infixString3)); 
		System.out.println("Expected infixToPostfix() output: 2 3 4 * 5 6 + * + 8 2 / +"); 
		System.out.println(); 
	} // end method main
	
	
	/**
	 * Converts an infix arithmetic expression (represented as a String) to a 
	 * postfix arithmetic expression (also represented as a String). 
	 *
	 * @param	infixString 	the infix expression to be converted
	 * @return	the postfix representation of the inputted infix expression
	 */ 
	public static String infixToPostfix(String infixString) {
		String postfixString = ""; 
		
		// Iterate until infixString is empty. 
		while (infixString.length() > 0) {
			// Character is not an operator, so add it to postfixString
			if ((!isOperator(infixString.charAt(0))) && (!isParenthesis(infixString.charAt(0)))) {
				postfixString += infixString.charAt(0) + " "; 
			} 
			
			// Character is a parenthesis. 
			else if (isParenthesis(infixString.charAt(0))) {
				postfixString += processParentheses(infixString.charAt(0)); 
			} 
			
			// Character is an operator.
			else {
				postfixString += processOperator(infixString.charAt(0)); 
			}
				
			// Remove the leading character from the infixString for next loop. 
			infixString = infixString.substring(1); 
		} // infixString should be empty after loop exits. 
			
		// Empty any remaining operators from the underlying stack 
		while (!conversionStack.isEmpty()) {
			postfixString += conversionStack.pop() + " "; 
		} // conversionStack should be empty after loop exits. 
		
		return postfixString; 
	} // end method infixToPostfix
	
	
	/**
	 * Returns whether or not an inputted char is an arithmetic operator. 
	 * 
	 * @param	nextChar		the char being considered
	 * @return	true if char is an operator, false otherwise
	 */ 
	public static boolean isOperator(char nextChar) {
		if (((nextChar == '+') || (nextChar == '-')) || 
		     ((nextChar == '*') || (nextChar == '/'))) {
			return true; 
		} 
		
		else {
			return false; 
		} 
	} // end method isOperator 
	
	
	/**
	 * Returns whether or not an inputted char is a parenthesis. 
	 * 
	 * @param	nextChar 	the char being considered
	 * @return	true if char is a parenthesis, false otherwise
	 */ 
	public static boolean isParenthesis(char nextChar) {
		if ((nextChar == '(') || (nextChar == ')')) {
			return true; 
		} 
		
		else {
			return false; 
		} 
	} // end method isParenthesis 
	
	
	/**
	 * Appropriately deals with parentheses for infix to postfix conversion by
	 * pushing open parentheses to the stack and popping everything off the stack
	 * until an open parenthesis is reached for a closing parenthesis.
	 * 
	 * @param	parenthesis		the parenthesis being handled 
	 * @throws	NoSuchElementException
	 * @return	a String of symbols to be added to the postfix String
	 */ 
	public static String processParentheses(char parenthesis) {
		String stringToAdd = ""; 
		
		// Push open parentheses to stack. 
		if (parenthesis == '(') {
			conversionStack.push(parenthesis); 
		} 
		
		// Pop all operators until an open parenthesis is reached. 
		else {
			while ((!conversionStack.isEmpty()) && (conversionStack.top() != '(')) {
				stringToAdd += conversionStack.pop() + " "; 
			} 
			
			// Expression was invalid and no matching '(' was provided. 
			if (conversionStack.isEmpty() || (conversionStack.top() != '(')) {
				throw new NoSuchElementException("Unmatched ), input expression invalid."); 
			} 
			
			// Pop the open parenthesis from the stack. 
			else {
				conversionStack.pop(); 
			}
		} 
		
		return stringToAdd; 
	} // end method processParentheses
	
	
	/**
	 * Takes an inputted operator and makes call to overloaded recursive
	 * method that will return the String to be returned back to the 
	 * infixToPostfix method. 
	 *
	 * @param	operator 	the operator being processed
	 * @return 	a String of symbols to be added to the postfix String
	 */ 
	public static String processOperator(char operator) {
		String stringToAdd = ""; 
		stringToAdd = processOperator(operator, stringToAdd);  
		return stringToAdd; 
	} // end method processOperator
	
	
	/**
	 * Checks a the precedence of an operator against the precedence
	 * of the operator at the top of the stack. If the inputted 
	 * operator has a higher precedence it is pushed to the stack. If
	 * it has a lower precedence, the operator at the top of the stack
	 * is popped to the output String and the method is recursively
	 * called to continue the check. 
	 *
	 * @param	operator 	the operator being processed
	 * @param	stringToAdd	the String being build to add to postfix String
	 * @return 	a String of symbols to be added to the postfix String
	 */ 
	public static String processOperator(char operator, String stringToAdd) {
		// Base case: stack is empty or top is '('. 
		if ((conversionStack.isEmpty()) || (conversionStack.top() == '(')) {
			conversionStack.push(operator); 
			return stringToAdd; 
		} 

		// Base case: operator's precedence > precedence of operator at top of stack. 		
		else if (getOperatorPrecedence(operator) > getOperatorPrecedence(conversionStack.top())) {
			conversionStack.push(operator); 
			return stringToAdd; 
		}
		
		// Complex case: operator's precedence <= precedence of operator at top of stack. 
		else {
			stringToAdd += conversionStack.pop() + " "; 
			return processOperator(operator, stringToAdd); 
		} 
	} // end overloaded method processOperator
	
	
	/**
	 * Returns the precedence of an arithmetic operator. * and / return 2; + and -
	 * return 1; and ( returns 0. 
	 *
	 * @param	operator		the operator whose precedence is being checked
	 * @return	an int value representing the operator's precedence
	 */ 
	public static int getOperatorPrecedence(char operator) {
		if ((operator == '*') || (operator == '/')) {
			return 2; 
		} 
		
		else {
			return 1; 
		} 
	} // end method getOperatorPrecedence
} // end class StartUp