package model.tokens;

import java.util.Stack;

import model.expressions.Expression;
import model.util.PicassaException;

/**
 * Function separators, mainly used to count the number of arguments for a
 * function
 * 
 * @author Yang
 * 
 */
public class FunctionSeperator extends Token {

	public FunctionSeperator(String t) {
		super(t);
	}

	@Override
	public void addToStacks(Stack<Token> operators, Stack<Expression> operands) {
		try {
			ArgNumber.increment();
		} catch (Exception e) {
			throw PicassaException.BAD_SYNTAX;
		}
	}

	/**
	 * This method should never be called. Throws a bad parentheses exception to
	 * for error checking purposes
	 */
	@Override
	public Expression createExpression(Stack<Expression> operands) {
		throw PicassaException.BAD_PARENTHESES;
	}

}
