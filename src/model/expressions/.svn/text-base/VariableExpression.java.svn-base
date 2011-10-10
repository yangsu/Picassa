package model.expressions;

import model.util.RGBColor;

/**
 * Class for handling variables
 * 
 * @author Yang
 * 
 */
public class VariableExpression extends Expression {
	private String variable;
	
	public VariableExpression(String operator,Expression... ex) {
		this(((VariableExpression)ex[0]).getVariable(), ex[1]);
	}
	public VariableExpression(String token, Expression ex) {
		this(token);
		Variables.set(variable, ex);
	}

	/**
	 * If the variable is not known, it's given a default value, which in this
	 * case is 0. This value is stored in the properties file and can be easily
	 * changed
	 * 
	 * @param token
	 */
	public VariableExpression(String token) {
		variable = token;
		if (!Variables.contains(variable)) {
			Variables.set(
					variable,
					new NumericExpression(Double.parseDouble(resources
							.getString("defaultValue"))));
		}
	}

	public VariableExpression(Expression ex) {
		Variables.set(variable, ex);
	}

	public String getVariable() {
		return variable;
	}

	@Override
	public RGBColor evaluate() {
		return Variables.get(variable).evaluate();
	}

	@Override
	public String toString() {
		return variable + " = " + Variables.get(variable).toString();

	}
}
