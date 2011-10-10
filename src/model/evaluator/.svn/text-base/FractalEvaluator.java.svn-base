package model.evaluator;

import java.awt.Color;
import java.awt.Dimension;

import model.expressions.Expression;
import model.expressions.Variables;
import model.util.RGBColor;

/**
 * Fractal evaluator, evaluates each the expression at each pixel location
 * 
 * @author Yang
 * 
 */
public class FractalEvaluator extends Evaluator {
	// Mandelbrot
	private RGBColor calculateFractal(Expression ex) {
		int max = 40;
		float x = (float) Variables.get("x").evaluate().getRed();
		float y = (float) Variables.get("y").evaluate().getRed();
		int n = mandlebrotFractal(max, x, y);
		// n=fractalLoop(ex, max);

		if (n == max) {
			return new RGBColor(1);
		} else {
			double r = imageToDomainScale(n, max);
			double g = imageToDomainScale(n * 2, max);
			double b = imageToDomainScale(n * 5, max);
			return new RGBColor(r, g, b);
		}
	}

	private int mandlebrotFractal(int max, float x, float y) {
		float xx = (float) x;
		float yy = (float) y;
		int n = 0;
		while (n < max) {
			float x2 = xx * xx;
			float y2 = yy * yy;
			float twoab = (float) (Variables.get("ans").evaluate().getRed()
					* xx * yy);
			xx = (float) (x2 - y2 + x);
			yy = (float) (twoab + y);
			// Infinty in our finite world is simple, let's just consider it 16
			if (x2 + y2 > 50) {
				break; // Bail
			}
			n++;
		}
		return n;
	}

	private int fractalLoop(Expression ex, int max) {
		int n = 0;
		while (n < max) {
			float x2 = (float) ex.evaluate().getRed();
			float y2 = (float) ex.evaluate().getRed();
			updateXY(x2, y2);
			// System.out.println(x2);
			// Infinty in our finite world is simple, let's just consider it 16
			if (x2 + y2 > 5000000) {
				break; // Bail
			}
			n++;
		}
		return n;
	}

	@Override
	public Color getColor(Expression ex, Dimension size) {
		return calculateFractal(ex).toJavaColor();
	}

}
