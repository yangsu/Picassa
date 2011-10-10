package model.expressions;

import model.util.RGBColor;

/**
 * Expression Class functions such as sum, average, min, and max and functions
 * like rgb2ycrcb , rgb2ycrcbClass, perlinColor, and perlinBW
 * 
 * @author Yang
 * 
 */
public class ColorFunctionExpression extends FunctionExpression {

	public ColorFunctionExpression(String token, Expression... params) {
		super(token, params);
		try {
			m = c.getMethod(methodname, RGBColor[].class);
		} catch (Exception e) {
			// fatal error, problem with resource file
			e.printStackTrace();
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
