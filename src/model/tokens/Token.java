package model.tokens;

import java.util.MissingResourceException;
import java.util.Stack;

import model.expressions.Expression;
import model.util.Resources;

public abstract class Token implements Resources {
	protected String token;

	public Token(String t) {
		token = t;
	}

	public String toString() {
		return token;
	}

	public abstract void addToStacks(Stack<Token> operators,
			Stack<Expression> operands);

	public abstract Expression createExpression(Stack<Expression> operands);

	protected int orderOfOperation() {
		char c = token.charAt(0);
		try {
			return Integer.parseInt(resources.getString(token+"Order"));
		} catch (MissingResourceException e) {
			return Integer.parseInt(resources.getString("defaultOrder"));
		}
	}
}
