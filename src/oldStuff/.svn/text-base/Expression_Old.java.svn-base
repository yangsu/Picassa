package oldStuff;

import java.lang.reflect.Method;
import java.util.ArrayList;

import model.util.RGBColor;
import model.util.Resources;

/**
 * An Expression represents a mathematical expression as a tree.
 * 
 * In this format, the internal nodes represent mathematical functions and the
 * leaves represent constant values.
 * 
 * @author former student solution
 * @author Robert C. Duvall (added comments, some code)
 */
public class Expression_Old implements Resources {
	private static final String VARIABLES = resources.getString("variables");
	private RGBColor value;
	private String operator;
	private ArrayList<Expression_Old> children;

	/**
	 * Create expression representing the given constant value
	 */
	public Expression_Old(RGBColor val) {
		value = val;
		operator = null;
		children = null;
	}

	public Expression_Old(String token, Expression_Old... params) {
		operator = token;
		value = new RGBColor();
		if (params.length == 0) {
			children = null;
		} else {
			children = new ArrayList<Expression_Old>();
			for (Expression_Old e : params) {
				children.add(e);
			}
		}
	}

	/**
	 * @param colorY
	 * @param colorX
	 * @return value of expression
	 */
	public RGBColor evaluate(RGBColor colorX, RGBColor colorY) {
		if (operator == null) {
			return value;
		}
		if (VARIABLES.contains(operator)) {
			if (operator.equals("x")) {
				return colorX;
			}
			if (operator.equals("y")) {
				return colorY;
			}
		}
		String operation = resources.getString(operator);
		int index = operation.lastIndexOf(".");
		String classname = operation.substring(0, index);
		String methodname = operation.substring(index + 1);

		try {
			Class<?> op = Class.forName(classname);
			Method m;
			double r,g,b;
			// TODO fix this so the function calls don't need to be hard coded
			 
//			 return (RGBColor) m.invoke(null, new RGBColor[] { new
//			 RGBColor(0,0,0) });
			switch (children.size()) {
			case 0:
				m = op.getMethod(methodname);
				return (RGBColor) m.invoke(null);
			case 1:
				m = op.getMethod(methodname, double.class);
				RGBColor child = children.get(0).evaluate(colorX, colorY);
				r = (Double) m.invoke(null, child.getRed());
				g = (Double) m.invoke(null, child.getGreen());
				b = (Double) m.invoke(null, child.getBlue());

				return new RGBColor(r, g, b);
			case 2:
				m = op.getMethod(methodname, RGBColor.class, RGBColor.class);
				return (RGBColor) m.invoke(null,
						children.get(0).evaluate(colorX, colorY),
						children.get(1).evaluate(colorX, colorY));
			}
			return null;

		} catch (Exception e) {
			// fatal error, problem with resource file
			e.printStackTrace();
			System.exit(1);
			// make compiler happy :)
			return null;
		}
	}

	/**
	 * @return string representation of expression
	 */
	public String toString() {
		StringBuffer result = new StringBuffer();
		if (operator == null) {
			result.append(value);
		} else {
			result.append("(");
			if (children.size() == 1) {
				result.append(" " + operator + " ");
			}
			for (int i = 0; i < children.size(); i++) {
				result.append(children.get(i).toString());
				if (i < children.size() - 1)
					result.append(" " + operator + " ");
			}
			result.append(")");
		}
		return result.toString();
	}
}
