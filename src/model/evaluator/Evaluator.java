package model.evaluator;

import java.awt.Color;
import java.awt.Dimension;

import model.expressions.*;
import model.util.Pixmap;

/**
 * General Evaluator, provides basic functions that are extensible by
 * subclasses. It evaluates the root expression it stores and plots the returned
 * value as a color on a Pixmap
 * 
 * @author Yang
 * 
 */
public abstract class Evaluator {
	public static final double DOMAIN_MIN = -1;
	public static final double DOMAIN_MAX = 1;

	public Pixmap evaluate(Expression expression, Dimension size) {
		Pixmap result = new Pixmap(size);
		for (int imageY = 0; imageY < size.height; imageY++) {
			double evalY = imageToDomainScale(imageY, size.height);
			for (int imageX = 0; imageX < size.width; imageX++) {
				double evalX = imageToDomainScale(imageX, size.width);
				updateXY(evalY, evalX);
				Color c = getColor(expression,size);
				result.setColor(imageX, imageY, c);
			}
		}
		return result;
	}

	public abstract Color getColor(Expression expression, Dimension size);
	/**
	 * Convert from image space to domain space.
	 */
	protected double imageToDomainScale(double d, double domainMax) {
		double range = DOMAIN_MAX - DOMAIN_MIN;
		return ((double) d / domainMax) * range + DOMAIN_MIN;
	}

	protected void updateXY(double x, double y) {
		Variables.set("x", new NumericExpression(y));
		Variables.set("y", new NumericExpression(x));
	}

}