package model.tokens;

import java.util.Stack;

/**
 * Class used to keep track of the number of arguments a function has
 * 
 * @author Yang
 * 
 */
public class ArgNumber {
	public static Stack<Integer> argCount = new Stack<Integer>();

	public static void add() {
		argCount.push(0);
	}

	public static void increment() {
		argCount.push(argCount.pop().intValue() + 1);
	}

	public static int pop() {
		return argCount.pop().intValue();
	}
}
