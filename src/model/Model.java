package model;

import java.awt.Dimension;

import model.evaluator.*;
import model.generator.*;
import model.util.*;

/**
 * Evaluate an expression for each pixel in a image.
 * 
 * @author Robert C Duvall
 */
public class Model {
	private Evaluator evaluator;
	private Generator generator;

	/**
	 * Create a basic model of the system.
	 */
	public Model() {

		evaluator = new PixelEvaluator();
		generator=new HistoryGenerator();
	}

	/**
	 * Evaluate an expression for each point in the image.
	 */
	public Pixmap evaluate(String infix, Dimension size) {
		return evaluator.evaluate(generator.generate(infix), size);
	}
}
