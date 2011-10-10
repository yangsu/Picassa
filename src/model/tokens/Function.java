package model.tokens;

import java.util.Stack;

import model.expressions.Expression;

/**
 * Functions uses the ArgNumber class to keep count of the parameters each
 * function has. The incremental changes are made in the Function separator
 * class because the number of commas are used to count the number of parameters
 * 
 * @author Yang
 * 
 */
public class Function extends OperationToken {

	public Function(String t) {
		super(t);
		ArgNumber.add();
	}

	@Override
	public void addToStacks(Stack<Token> operators, Stack<Expression> operands) {
		operators.push(this);
	}

	public int getNum() {
		return ArgNumber.pop() + 1;
	}

}
