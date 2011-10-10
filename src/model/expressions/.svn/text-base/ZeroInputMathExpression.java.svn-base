package model.expressions;

import model.util.RGBColor;

/**
 * Expression Class for Math functions that require 0 input, i.e. random
 * 
 * @author Yang
 * 
 */
public class ZeroInputMathExpression extends FunctionExpression {

	public ZeroInputMathExpression(String token, Expression... params) {
		super(token, params);
		try {
			m = c.getMethod(methodname);
		} catch (Exception e) {
			// fatal error, problem with resource file
			e.printStackTrace();
		}
	}

	@Override
	public RGBColor evaluate() {
		try {
			double r = (Double) m.invoke(null);
			double g = (Double) m.invoke(null);
			double b = (Double) m.invoke(null);
			return new RGBColor(r, g, b);
		} catch (Exception e) {
			// fatal error, problem with resource file
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public String toString() {
		return operation + "()";
	}

}
