package oldStuff;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import model.util.PicassaException;
import model.util.RGBColor;
import model.util.Resources;

/**
 * 
 * @author Yang
 * 
 */
public class ParserV1 implements Resources {
	private static final String numbers = resources.getString("numbers");
	private static final String UnaryOperators = resources
			.getString("UnaryOperators");
	private static final String BinaryOperators = resources
			.getString("BinaryOperators");
	private static final String variables = resources.getString("variables");
	private static final String Functions0IN = resources
			.getString("Functions0IN");
	private static final String Functions1IN = resources
			.getString("Functions1IN");
	private static final String Functions2IN = resources
			.getString("Functions2IN");
	private static final String SpecialChars = resources
			.getString("SpecialChars");
	private static final String delimiter = resources.getString("delimiter");

	private static final int SPECIAL = Integer.parseInt(resources
			.getString("SPECIAL"));
	private static final int UNARY = Integer.parseInt(resources
			.getString("UNARY"));
	private static final int BINARY = Integer.parseInt(resources
			.getString("BINARY"));
	private static final int FUNCTION0 = Integer.parseInt(resources
			.getString("FUNCTION0"));
	private static final int FUNCTION1 = Integer.parseInt(resources
			.getString("FUNCTION1"));
	private static final int FUNCTION2 = Integer.parseInt(resources
			.getString("FUNCTION2"));
	private static final int VARIABLE = Integer.parseInt(resources
			.getString("VARIABLE"));
	private static final int CONSTANT = Integer.parseInt(resources
			.getString("CONSTANT"));

	private static final int constantDef = Integer.parseInt(resources
			.getString("constantDef"));;

	public Queue<String> shunting_yard(String[] input) {
		int index = 0;
		int endIndex = input.length;
		Stack<String> stack = new Stack<String>();
		Queue<String> q = new LinkedList<String>();
		while (index < endIndex) {
			String c = input[index];
			// System.out.println(c +" char : " + c.charAt(0));
			if (!c.isEmpty()) {
				// If the token is a number (identifier), then add it to the
				// output queue.
				if (isNumber(c) || isSpecialVariable(c)) {
					q.add(c);
				}
				// If the token is a function token, then push it onto the
				// stack.
				else if (isFunction(c)) {
					stack.push(c);
				}
				// If the token is a function argument separator (e.g., a
				// comma):
				else if (c.equals(",")) {
					boolean pe = false;
					while (stack.size() > 0) {
						String sc = stack.peek();
						// System.out.println(stack.size()+ "comma, pop="+sc );
						if (sc.length() == 1 && sc.charAt(0) == '(') {
							pe = true;
							break;
						} else {
							// Until the token at the top of the stack is a left
							// parenthesis,
							// pop operators off the stack onto the output
							// queue.
							q.add(stack.pop());
						}
					}
					// If no left parentheses are encountered, either the
					// separator was misplaced
					// or parentheses were mismatched.
					if (!pe) {
						System.out
								.println("Error: separator or parentheses mismatched\n");
						return q;
					}
				}
				// If the token is an operator, op1, then:
				else if (isOperator(c)) {
					while (stack.size() > 0) {
						// Assumes sc and c are 1 char strings and operators
						String sc = stack.peek();

						// While there is an operator token, o2, at the top of
						// the stack
						// op1 is left-associative and its precedence is less
						// than or equal to that of op2,
						// or op1 is right-associative and its precedence is
						// less than that of op2,
						if (isOperator(sc)
								&& ((isLeftAssociative(c) && (getOperatorPrecendence(c) <= getOperatorPrecendence(sc))) || (!isLeftAssociative(c) && (getOperatorPrecendence(c) < getOperatorPrecendence(sc))))) {
							// Pop o2 off the stack, onto the output queue;
							q.add(stack.pop());
						} else {
							break;
						}
					}
					// push op1 onto the stack.
					stack.push(c);
				}
				// If the token is a left parenthesis, then push it onto the
				// stack.
				else if (c.equals("(")) {
					stack.push(c);

				}
				// If the token is a right parenthesis:
				else if (c.equals(")")) {
					boolean pe = false;
					// Until the token at the top of the stack is a left
					// parenthesis,
					// pop operators off the stack onto the output queue
					while (stack.size() > 0) {
						String sc = stack.peek();
						if (sc.equals("(")) {
							pe = true;
							break;
						} else {
							q.add(stack.pop());
						}
					}
					// If the stack runs out without finding a left parenthesis,
					// then there are mismatched parentheses.
					if (!pe) {
						System.out.println("Error: parentheses mismatched\n");
						return q;
					}
					// Pop the left parenthesis from the stack, but not onto the
					// output queue.
					// TODO potential problem
					// System.out.println(stack.peek());

					// If the token at the top of the stack is a function token,
					// pop it onto the output queue.
					if (stack.size() > 0) {
						String sc = stack.pop();
						if (isFunction(sc)) {
							q.add(sc);
						}
					}
				} else {
					System.out.println("Unknown token " + c);
					return q; // Unknown token
				}
			}
			index++;
		}

		// When there are no more tokens to read:
		// While there are still operator tokens in the stack:
		while (stack.size() > 0) {
			String sc = stack.pop();
			if (sc.indexOf(0) == '(' || sc.indexOf(0) == ')') {
				System.out.println("Error: parentheses mismatched\n");
				return q;
			}
			q.add(sc);
		}
		// *outpos = 0; // Null terminator
		return q;
	}

	public String[] process(String s) {
		String output = "";
		int i = 0;
		String numTemp = "";
		String funcTemp = "";
		while (i < s.length()) {
			char c = s.charAt(i);
			if (c != ' ') {
				if (isSpecialVariable(c + "") || isOperator(c + "")
						|| isSpecialCharacter(c + "")) {
					if (!numTemp.isEmpty()) {
						// format number
						if (numTemp.charAt(0) == '.') {
							numTemp = "0" + numTemp;
						}
						output += numTemp + delimiter;
						numTemp = "";
					}
					if (!funcTemp.isEmpty()) {
						// TODO -lowercase or not?
						// output += funcTemp.toLowerCase() + delimiter;
						output += funcTemp + delimiter;
						funcTemp = "";
					}
					output += c + delimiter;
				} else {
					if (isNumber(c + "")) {
						numTemp += c;
					} else if (isLetter(c + "")) {
						funcTemp += c;
					} else
						throw PicassaException.badToken(c+"");

				}
			}

			i++;
		}
		output += numTemp + delimiter;

		return output.split(delimiter);
	}

	public String[] checkSyntax(String[] s) {
		ArrayList<String> output = new ArrayList<String>();
		int prevType = -1;
		for (int i = 0; i < s.length; i++) {
			String current = s[i];
			if (current.equals("-")) {
				//TODO check for variable/function after the - sign
				if (i==0 || (i+1<s.length&&isOperator(s[i - 1]) && (isFunction(s[i + 1])||isSpecialVariable(s[i + 1])))) {
					output.add(current + s[i + 1]);
					i++;
					prevType = CONSTANT;
				} else {
					output.add(current);
					prevType = BINARY;
				}
			}
			else if (current.equals("[")) {
				i++;
				int count = 0;
				String constant = "[";
				while (i < s.length) {
					if (s[i].equals("]")) {
						break;
					} else {
						count++;
						constant += s[i];
						i++;
					}
				}
				if (count == constantDef) {
					output.add(constant + "]");
					prevType = getType(output.get(output.size() - 1));
					constant = "[";
					continue;
				} else {
					//TODO known bug, can't handle - signs in constants
					throw PicassaException.BAD_SYNTAX; // constant problem
				}
			}
			// else if(isBinaryOperators(current)) {
			// if(i-1>=0&&i+1<s.length) {
			// if
			// ((isOperator(s[i-1])||isFunction(s[i-1]))||(isOperator(s[i+1])||isFunction(s[i+1])))
			// {
			// throw ParserException.BAD_SYNTAX;
			// }
			// }
			// else {
			// output.add(current);
			// prevType = BINARY;
			// }
			// }
			// else if(isUnaryOperators(current)) {
			//
			// if(i+1<s.length) {
			// if (isOperator(s[i+1])||isFunction(s[i+1])) {
			// System.out.println(isOperator(s[i+1])||isFunction(s[i+1]));
			// throw ParserException.BAD_SYNTAX;
			// }
			// else {
			// output.add(current+s[i+1]);
			// i++;
			// prevType = UNARY;
			// }
			// }
			// }
			else {
				if (checkType(current, prevType)) {
					throw PicassaException.BAD_SYNTAX;
				} else {
					output.add(current);
					prevType = getType(output.get(output.size() - 1));
				}
			}
		}

		return output.toArray(new String[output.size()]);
	}

	private boolean checkType(String current, int prevType) {
		if (getType(current) == SPECIAL) {
			return false;
		} else
			return getType(current) == prevType;
	}

	private int getType(String token) {
		if (isSpecialCharacter(token)) {
			return SPECIAL;
		} else if (isUnaryOperators(token)) {
			return UNARY;
		} else if (isBinaryOperators(token)) {
			return BINARY;
		} else if (isSpecialVariable(token)) {
			return VARIABLE;
		} else if (is0InFunction(token)) {
			return FUNCTION0;
		} else if (is1InFunction(token)) {
			return FUNCTION1;
		} else if (is2InFunction(token)) {
			return FUNCTION2;
		} else
			return CONSTANT;
	}

	public Expression_Old makeExpression(String infix) {
		Queue<String> tokens = shunting_yard(checkSyntax(process(infix)));
		Stack<Expression_Old> stack = new Stack<Expression_Old>();
		for (String t : tokens) {
			int type = getType(t);

			if (type == VARIABLE || type == CONSTANT || type == FUNCTION0) {
				stack.push(createExpression(t, type));
			} else if ((type == UNARY || type == FUNCTION1) && stack.size() > 0) {
				stack.push(createExpression(t, stack.pop()));
			} else if ((type == BINARY || type == FUNCTION2)
					&& stack.size() > 1) {
				// TODO comment
				Expression_Old temp = stack.pop();
				stack.push(createExpression(t, stack.pop(), temp));
			} else
				throw PicassaException.BAD_SYNTAX;
		}
		;
		// all operators handled, should be only one operand left, the result
		if (stack.size() == 1)
			return stack.pop();
		else
			throw PicassaException.BAD_SYNTAX;
	}

	private Expression_Old createExpression(String token, int type) {
		if (type == CONSTANT) {
			if (token.charAt(0) == '[') {
				token = token.substring(1, token.length() - 1);
				int next = token.indexOf(",");
				double r = Double.parseDouble(token.substring(0, next));
				token = token.substring(next + 1);
				next = token.indexOf(",");
				double g = Double.parseDouble(token.substring(0, next));
				token = token.substring(next + 1);
				double b = Double.parseDouble(token);

				return new Expression_Old(new RGBColor(r, g, b));
			} else {
				return new Expression_Old(new RGBColor(Double.parseDouble(token)));
			}

		} else if (type == FUNCTION0) {
			return createExpression(token);
		} else {
			return createExpression(token);
		}
	}

	private Expression_Old createExpression(String token, Expression_Old... params) {
		return new Expression_Old(token, params);
	}

	private int getOperatorPrecendence(String operator) {
		char c = operator.charAt(0);
		switch (c) {
		case '!':
			return 4;
		case '*':
		case '/':
		case '%':
			return 3;
		case '+':
		case '-':
			return 2;
		case '=':
			return 1;
		}
		return 0;
	}

	private boolean isLeftAssociative(String operator) {
		char c = operator.charAt(0);
		switch (c) {
		// left to right
		case '*':
		case '/':
		case '%':
		case '+':
		case '-':
			return true;
			// right to left
		case '=':
		case '!':
			return false;
		}
		return false;
	}

	private boolean isFunction(String func) {
		return is0InFunction(func) || is1InFunction(func)
				|| is2InFunction(func);
	}

	private boolean is0InFunction(String func) {
		return Functions0IN.contains(func);
	}

	private boolean is1InFunction(String func) {
		return Functions1IN.contains(func);
	}

	private boolean is2InFunction(String func) {
		return Functions2IN.contains(func);
	}

	private boolean isSpecialCharacter(String character) {
		return SpecialChars.contains(character);
	}

	private boolean isSpecialVariable(String var) {
		return variables.contains(var);
	}

	private boolean isOperator(String op) {
		return isUnaryOperators(op) || isBinaryOperators(op);
	}

	private boolean isUnaryOperators(String op) {
		return UnaryOperators.contains(op);
	}

	private boolean isBinaryOperators(String op) {
		return BinaryOperators.contains(op);
	}

	private boolean isLetter(String letter) {
		char c = letter.charAt(0);
		return (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z');
	}

	private boolean isNumber(String str) {
		if (str.charAt(0) == '[') {
			return true;
		}
		if (str.charAt(0) == '-' && str.length() > 1
				&& isNumber(str.charAt(1) + "")) {// explain and precedence
			return true;
		} else {
			for (int i = 0; i < str.length(); i++) {
				char c = str.charAt(i);
				if (!((numbers.indexOf(c) >= 0)))
					return false;
			}
			return true;
		}
		// return str.length()==1&&(numbers.indexOf(c)>=0 ||
		// variables.indexOf(c)>=0);
	}
}
