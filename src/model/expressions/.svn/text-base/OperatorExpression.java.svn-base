package model.expressions;

import model.util.RGBColor;

/**
 * Expression Class for a general operator, including unary and binary operators
 * 
 * @author Yang
 * 
 */
public abstract class OperatorExpression extends OperationExpression {

	public OperatorExpression(String token, Expression... params) {
		super(token, params);

		try {
			m = c.getMethod(methodname, RGBColor[].class);
		} catch (Exception e) {
			// fatal error, problem with resource file
			e.printStackTrace();
			System.exit(1);
		}
	}

	@Override
	public RGBColor evaluate() {
		try {
			return (RGBColor) m.invoke(null, new Object[] { getChildrenRGB() });

		} catch (Exception e) {
			// fatal error, problem with resource file
			e.printStackTrace();
			System.exit(1);
			return null;
		}
	}
}
