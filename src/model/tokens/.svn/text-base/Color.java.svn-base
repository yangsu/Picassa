package model.tokens;

import java.util.Stack;

import model.expressions.Expression;
import model.expressions.NumericExpression;
import model.util.RGBColor;
/**
 * Color Token, parses a specifically defined color value
 * @author Yang
 *
 */
public class Color extends Token {

	public Color(String t) {
		super(t);
	}

	@Override
	public void addToStacks(Stack<Token> operators, Stack<Expression> operands) {
		operands.add(createExpression(operands));
	}

	@Override
	public Expression createExpression(Stack<Expression> operands) {
		token = token.substring(1, token.length() - 1);
		String[] values = token.split(constantSeperator);
		double r = Double.parseDouble(values[0].trim());
		double g = Double.parseDouble(values[1].trim());
		double b = Double.parseDouble(values[2].trim());
		return new NumericExpression(new RGBColor(r, g, b));
	}

}
