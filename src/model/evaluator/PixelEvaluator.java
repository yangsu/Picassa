package model.evaluator;

import java.awt.Color;
import java.awt.Dimension;

import model.expressions.Expression;

/**
 * Default evaluator, evaluates each the expression at each pixel location
 * 
 * @author Yang
 * 
 */
public class PixelEvaluator extends Evaluator {
	@Override
	public Color getColor(Expression expression, Dimension size) {
		return expression.evaluate().toJavaColor();
	}

}
