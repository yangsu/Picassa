package model.tokens;

import java.util.Stack;

import model.expressions.Expression;
import model.util.PicassaException;
/**
 * the Closing parenthesis
 * @author Yang
 *
 */
public class ClosingGroup extends Token {

	public ClosingGroup(String t) {
		super(t);
	}

	/**
	 * Pop off all operators until the corresponding opening group is found.
	 */
	@Override
	public void addToStacks(Stack<Token> operators, Stack<Expression> operands) {
		try {
			while (!(operators.peek() instanceof OpeningGroup)) {
				operands.add(operators.pop().createExpression(operands));
			}
			operators.pop();
		} catch (Exception e) {
			throw PicassaException.BAD_PARENTHESES;
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
