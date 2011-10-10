package model.generator;

import model.expressions.Expression;
import model.expressions.Variables;
/**
 * Stores previously entered expressions and allows the user to access them using special tags.
 * @author Yang
 *
 */
public class HistoryGenerator extends Generator {
	private int count;

	public HistoryGenerator() {
		count = 0;
	}

	@Override
	public Expression generate(String infix) {
		Expression root = parser.makeExpression(infix);
		count++;
		Variables.set("ans", root);
		Variables.set("$" + count, root);
		System.out.println("$" + count + ": " + Variables.get("ans").toString());
		return root;
	}

}
