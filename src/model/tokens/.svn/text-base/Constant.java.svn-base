package model.tokens;

import java.util.Stack;

import model.expressions.Expression;
import model.expressions.NumericExpression;
import model.util.RGBColor;
/**
 * A number
 * @author Yang
 *
 */
public class Constant extends Token {

	public Constant(String t) {
		super(t);
	}

	@Override
	public void addToStacks(Stack<Token> operators, Stack<Expression> operands) {
		operands.add(createExpression(operands));
	}

	@Override
	public Expression createExpression(Stack<Expression> operands) {
		double value = Double.parseDouble(token);
		return new NumericExpression(new RGBColor(value));
	}

}
