package tests;

import model.*;
import model.expressions.Expression;
import model.util.*;

public class Tester implements Resources {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		System.out.println("- 1".matches("[-]?\\d*\\.?\\d+"));
		Parser a = new Parser();
		String parse = "A+5*(123.3+2%3)-!5*b^4/c+!5+sin([2,3  ,4]+[ -1 , .3 , -5 ]+abcdef123)";
		parse = "a+ x- 1 *y/3%0.3^4";
		// parse = "sum(sin(1),2,-1)";
		// parse = "random()";
		System.out.print("parse: ");
		Iterable<String> parsed = a.tokenize(parse);
		for (String s : parsed) {
			System.out.print(s.toString() + " ");
		}
		// System.out.print("\n");
		Expression ex = a.makeExpression(parse);
		System.out.println(ex + " = " + ex.evaluate());

	}
}
