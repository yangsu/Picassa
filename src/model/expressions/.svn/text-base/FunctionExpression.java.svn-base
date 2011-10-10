package model.expressions;

/**
 * Expression class for the general Function expression, it's subclasses include
 * ZeroInputMathExpression, OneInputMathExpression, and MiscFunctionsExpression
 * 
 * @author Yang
 * 
 */
public abstract class FunctionExpression extends OperationExpression {

	public FunctionExpression(String token, Expression[] params) {
		super(token, params);
	}

	public String toString() {
		StringBuffer result = new StringBuffer();
		result.append(operation);
		result.append("(");
		for (int i = 0; i < operands.size(); i++) {
			result.append(operands.get(i).toString());
			result.append(functionSeperator);
		}
		result.append(")");
		return result.toString().replace(",)", ")");
	}
}
