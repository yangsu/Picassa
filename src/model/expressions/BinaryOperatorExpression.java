package model.expressions;

/**
 * Expression class for binary operators such as +,-,/...
 * 
 * @author Yang
 * 
 */
public class BinaryOperatorExpression extends OperatorExpression {

	public BinaryOperatorExpression(String token, Expression... params) {
		super(token, params);
	}

	@Override
	public String toString() {

		StringBuffer result = new StringBuffer();
		result.append("(" + operands.get(0) + " " + operation + " "
				+ operands.get(1) + ")");
		return result.toString();
	}

}
