package model.expressions;

import model.util.RGBColor;

/**
 * Expression Class for Math functions that require 1 input, i.e. sin, cos, ...
 * 
 * @author Yang
 * 
 */
public class OneInputMathExpression extends FunctionExpression {

	public OneInputMathExpression(String token, Expression... params) {
		super(token, params);

		try {
			m = c.getMethod(methodname, double.class);
		} catch (Exception e) {
			// fatal error, problem with resource file
			e.printStackTrace();
		}
	}

	@Override
	public RGBColor evaluate() {
		try {
			RGBColor child = operands.get(0).evaluate();
			double r = (Double) m.invoke(null, child.getRed());
			double g = (Double) m.invoke(null, child.getGreen());
			double b = (Double) m.invoke(null, child.getBlue());
			return new RGBColor(r, g, b);

		} catch (Exception e) {
			// fatal error, problem with resource file
			e.printStackTrace();
			System.exit(1);
			return null;
		}
	}

}