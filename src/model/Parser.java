package model;

import java.lang.reflect.Constructor;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.expressions.*;
import model.tokens.Token;
import model.util.PicassaException;
import model.util.Resources;

/**
 * 
 * @author Yang
 * 
 */
public class Parser implements Resources {

	public Iterable<String> tokenize(String infix) {
		// Strip all the spaces
		// infix = infix.replaceAll("\\s+", "");

		ArrayList<String> a = new ArrayList<String>();
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(infix);

		// Find all the matches.
		while (matcher.find()) {
			String token = matcher.group();
			a.add(token);
		}
		return a;

	}

	public Expression makeExpression(String infix) {
		Stack<Token> operators = new Stack<Token>();
		Stack<Expression> operands = new Stack<Expression>();

		for (String s : tokenize(infix)) {
			Token t = makeToken(s);
			t.addToStacks(operators, operands);
		}
		processRemainingOperators(operators, operands);
		if (operands.size() == 1)
			return operands.pop();
		else {
			System.out.println(operands.size());
			throw PicassaException.BAD_SYNTAX;
		}
	}

	private Token makeToken(String token) {
		for (String key : patternList) {
			if (token.matches(key)) {
				try {
					String classname = resources.getString(key + "TokenClass");
					Class<?> c = Class.forName(classname);
					Constructor<?> con = c.getConstructor(String.class);
					return (Token) (con.newInstance(token));

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

	private void processRemainingOperators(Stack<Token> operators,
			Stack<Expression> operands) {
		while (operands.size() > 0 && operators.size() > 0) {
			Token t = operators.pop();
			Expression a = t.createExpression(operands);
			operands.add(a);
		}
	}

}
