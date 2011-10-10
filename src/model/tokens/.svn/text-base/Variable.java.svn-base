package model.tokens;

import java.util.Stack;

import model.expressions.Expression;
import model.expressions.VariableExpression;

public class Variable extends Token {

	public Variable(String t) {
		super(t);
	}

	@Override
	public void addToStacks(Stack<Token> operators, Stack<Expression> operands) {
		operands.push(createExpression(operands));
	}

	@Override
	public Expression createExpression(Stack<Expression> operands) {
		return new VariableExpression(token);
	}

}
