package model.tokens;

import java.lang.reflect.Constructor;
import java.util.MissingResourceException;
import java.util.Stack;

import model.expressions.Expression;
import model.util.PicassaException;

/**
 * A general entity that represents both operators and functions. It includes
 * common functions and algorithms and generalizes the behavior of different
 * operators and functions
 * 
 * @author Yang
 * 
 */
public abstract class OperationToken extends Token {
	public OperationToken(String t) {
		super(t);
	}
	
	/**
	 * Specifies how this token behaves when it's added to the stack
	 */
	@Override
	public void addToStacks(Stack<Token> operators, Stack<Expression> operands) {
		while (operators.size() > 0
				&& operators.peek().orderOfOperation() >= orderOfOperation()) {
			Token op = operators.pop();
			operands.push(op.createExpression(operands));
		}
		operators.push(this);
	}

	public abstract int getNum();
	/**
	 * Creates the expression that this token represents
	 */
	@Override
	public Expression createExpression(Stack<Expression> operands) {
		Expression[] ops = new Expression[getNum()];
		for (int i = ops.length - 1; i >= 0; i--) {
			ops[i] = operands.pop();
		}
		return createExpression(ops);
	}

	public Expression createExpression(Expression... operands) {
		for (String key : patternList) {
			if (token.matches(key)) {
				try {
					String className = resources.getString(key
							+ "ExpressionClass");

					Class<?> c = Class.forName(className);
					Constructor<?> cons = c.getConstructors()[0];
					return (Expression) cons.newInstance(token, operands);

				} catch (MissingResourceException e) {
					throw PicassaException.badResourceFile(key);
				} catch (Exception e) {
					e.printStackTrace();
					throw PicassaException.badToken(token);
				}

			}
		}
		return null;
	}

}
