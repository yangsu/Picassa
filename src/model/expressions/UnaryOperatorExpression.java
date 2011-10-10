package model.expressions;

/**
 * Expression Class for Unary operators such as ! and ~
 * 
 * @author Yang
 * 
 */
public class UnaryOperatorExpression extends OperatorExpression {

	public UnaryOperatorExpression(String token, Expression[] params) {
		super(token, params);
	}

	/**
	 * Print the operator and its only child
	 */
	public String toString() {
		return operation + " " + operands.get(0).toString();
	}

}
