package oldStuff;

import java.util.Arrays;
import java.util.Stack;

import model.util.PicassaException;
import model.util.RGBColor;
import model.util.Resources;

/**
 * Parses a string into an expression tree based on rules for arithmetic.
 * 
 * Uses Dijkstra's Shunting-Yard algorithm for converting infix to postfix:
 * http://en.wikipedia.org/wiki/Shunting_yard_algorithm
 * 
 * @author former student solution
 * @author Robert C. Duvall (added comments, exceptions, some functions)
 */
public class ParserOld {
	// different kinds of expressions
	private static final int CONSTANT = 0;

	/**
	 * Breaks given infix expression into separate tokens. Currently, assumes
	 * spaces exist between each token.
	 * 
	 * @return collection of individual tokens that can be looped over
	 */
	protected Iterable<String> tokenize(String infix) {
		return Arrays.asList(infix.split(" "));
	}

	/**
	 * @return true iff operator signifies beginning of group expression
	 */
	protected boolean isOpenGroup(String operator) {
		return operator.equals("(");
	}

	/**
	 * @return true iff operator signifies end of group expression
	 */
	protected boolean isCloseGroup(String operator) {
		return operator.equals(")");
	}

	/**
	 * @return true if operator signifies beginning or end of group expression
	 */
	protected boolean isGrouping(String operator) {
		return isOpenGroup(operator) || isCloseGroup(operator);
	}

	/**
	 * @return relative importance of operators (highest precedence indicates
	 *         that operation should be performed first)
	 */
	protected int orderOfOperation(String operator) {
		if (isGrouping(operator))
			return 1;
		else if (operator.equals("+") || operator.equals("-"))
			return 2;
		else if (operator.equals("*") || operator.equals("/"))
			return 3;
		else
			return 0;
	}

	// Operator -- convert previous higher order operators before push
	private void processOperator(String token, int order,
			Stack<String> operators, Stack<Expression_Old> operands) {
		if (!isOpenGroup(token)) {
			while (operators.size() > 0
					&& orderOfOperation(operators.peek()) >= order) {
				String op = operators.pop();
				if (isOpenGroup(op)) {
					break;
				} else {
					operands.add(createExpression(op, operands));
				}
			}
		}
		if (!isCloseGroup(token)) {
			if (operands.size() > 0 && operands.size() >= operators.size()) {
				operators.push(token);
			} else {
				throw PicassaException.BAD_ARGUMENTS;
			}
		}
	}

	// Resolve any remaining operators
	private void processRemainingOperators(Stack<String> operators,
			Stack<Expression_Old> operands) {
		while (operators.size() > 0) {
			String op = operators.pop();
			if (!isOpenGroup(op)) {
				operands.add(createExpression(op, operands));
			}
		}
	}

	/**
	 * Converts given string into expression tree.
	 * 
	 * @param infix
	 *            space separated infix notation form of an expression
	 * @return expression tree representing the given infix formula
	 */
	public Resources makeExpression(String infix) {
		Stack<String> operators = new Stack<String>();
		Stack<Expression_Old> operands = new Stack<Expression_Old>();

		for (String token : tokenize(infix)) {
			int order = orderOfOperation(token);
			if (order == CONSTANT) {
				operands.add(createExpression(token));
			} else {
				processOperator(token, order, operators, operands);
			}
		}
		processRemainingOperators(operators, operands);

		// all operators handled, should be only one operand left, the result
		if (operands.size() == 1)
			return operands.pop();
		else
			throw PicassaException.BAD_SYNTAX;
	}

	// Create expression form a constant value
	private Expression_Old createExpression(String token) {
		try {
			return new Expression_Old(new RGBColor(Double.parseDouble(token)));
		} catch (NumberFormatException e) {
			throw PicassaException.BAD_TOKEN;
		}
	}

	// Create expression from an operator
	private Expression_Old createExpression(String op,
			Stack<Expression_Old> operands) {
		// convert operator into expression
		if (operands.size() > 1) {
			// Resources right = operands.pop();
			// Expression_Old left = operands.pop();
			//
			// return new Expression(op, left, right);
			return null;
		} else {
			throw PicassaException.BAD_ARGUMENTS;
		}
	}
}
