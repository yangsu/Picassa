package model.tokens;

import java.util.Stack;

import model.expressions.Expression;
import model.util.PicassaException;
/**
 * opening parenthesis
 * @author Yang
 *
 */
public class OpeningGroup extends Token {

	public OpeningGroup(String t) {
		super(t);
	}

	@Override
	public void addToStacks(Stack<Token> operators, Stack<Expression> operands) {
		operators.add(this);
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
