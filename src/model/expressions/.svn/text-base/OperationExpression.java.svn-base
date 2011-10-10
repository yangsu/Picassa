package model.expressions;

import java.lang.reflect.Method;
import java.util.ArrayList;

import model.util.RGBColor;

/**
 * Expression Class for the general operation expression, which is defined as
 * any expression that manipulates or combines one or more expressions. This
 * class is then split into 2 separate subclasses, OperatorExpression and
 * FunctionExpression
 * 
 * @author Yang
 * 
 */
public abstract class OperationExpression extends Expression {
	// All fields are encapsulated as protected because they need to be accessed
	// by subclasses
	/**
	 * Each operator expression keeps track of the operation it represents and a
	 * list of operands. ArrayList is chosen as the data structure because of
	 * the ease of adding and removing entries.
	 */
	protected String operation;
	protected ArrayList<Expression> operands;
	/**
	 * All of the following fields are used to find the method that specifies
	 * how an operation is evaluated
	 */
	protected Class<?> c;
	protected Method m;
	protected String methodname;

	public OperationExpression(String token, Expression... params) {
		operation = token;

		String reference = resources.getString(operation);
		int index = reference.lastIndexOf(".");
		String classname = reference.substring(0, index);
		methodname = reference.substring(index + 1);

		try {
			c = Class.forName(classname);
		} catch (Exception e) {
			// fatal error, problem with resource file
			e.printStackTrace();
			System.exit(1);
		}

		operands = new ArrayList<Expression>();
		for (Expression e : params) {
			operands.add(e);
		}
	}

	/**
	 * Get the evaluations of the children as a list of RGBColor. It's protected
	 * because it needs to be accessed by subclasses to retrieve the list of
	 * colors of the children
	 * 
	 * @return a list of the evaluations results of the children
	 */
	protected RGBColor[] getChildrenRGB() {
		if (operands.size() == 0)
			return null;
		else {
			RGBColor[] output = new RGBColor[operands.size()];
			for (int i = 0; i < output.length; i++) {
				output[i] = operands.get(i).evaluate();
			}
			return output;
		}
	}
}