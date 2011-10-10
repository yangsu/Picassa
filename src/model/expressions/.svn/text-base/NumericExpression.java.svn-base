package model.expressions;

import model.util.RGBColor;

/**
 * Expression class for a general Numeric Expression that stores a color value
 * 
 * @author Yang
 * 
 */
public class NumericExpression extends Expression {
	private RGBColor value;
	
	public NumericExpression(double val) {
		setValue(new RGBColor(val));
	}
	public NumericExpression(RGBColor val) {
		setValue(val);
	}
	
	protected void setValue(RGBColor val) {
		value = val;
	}

	public RGBColor evaluate() {
		return value;
	}

	public String toString() {
		return value.toString();
	}

}
