package model.expressions;

import model.util.RGBColor;
import model.util.Resources;

/**
 * The top level Expression class. It defines the fundamental functionalities of
 * an expression
 * 
 * @author Yang
 * 
 */
public abstract class Expression implements Resources {

	public abstract RGBColor evaluate();

	public abstract String toString();

}
