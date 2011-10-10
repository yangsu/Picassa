package model.util;

/**
 * Combine two colors by combining their components.
 * 
 * This is a separate class from color since it is just one set of ways to
 * combine colors, many may exist and we do not want to keep modifying the
 * RGBColor class.
 * 
 * @author Robert C. Duvall
 */
public class ColorCombinations {

	public static RGBColor negate(RGBColor... colors) {
		return new RGBColor(-colors[0].getRed(), -colors[0].getGreen(),
				-colors[0].getBlue());
	}

	public static RGBColor grey(RGBColor... colors) {
		return new RGBColor(colors[0].getRed() * 0.2126,
				colors[0].getGreen() *0.7152, colors[0].getBlue() * 0.0722);
	}

	public static RGBColor add(RGBColor... colors) {
		return sum(colors);
	}

	public static RGBColor subtract(RGBColor... colors) {
		return new RGBColor(colors[0].getRed() - colors[1].getRed(),
				colors[0].getGreen() - colors[1].getGreen(),
				colors[0].getBlue() - colors[1].getBlue());
	}

	public static RGBColor multiply(RGBColor... colors) {
		return new RGBColor(colors[0].getRed() * colors[1].getRed(),
				colors[0].getGreen() * colors[1].getGreen(),
				colors[0].getBlue() * colors[1].getBlue());
	}

	public static RGBColor divide(RGBColor... colors) {
		return new RGBColor(colors[0].getRed() / colors[1].getRed(),
				colors[0].getGreen() / colors[1].getGreen(),
				colors[0].getBlue() / colors[1].getBlue());
	}

	public static RGBColor mod(RGBColor... colors) {
		return new RGBColor(colors[0].getRed() % colors[1].getRed(),
				colors[0].getGreen() % colors[1].getGreen(),
				colors[0].getBlue() % colors[1].getBlue());
	}

	public static RGBColor power(RGBColor... colors) {
		return new RGBColor(Math.pow(colors[0].getRed(), colors[1].getRed()),
				Math.pow(colors[0].getGreen(), colors[1].getGreen()), Math.pow(
						colors[0].getBlue(), colors[1].getBlue()));
	}

	public static RGBColor max(RGBColor... colors) {
		RGBColor max =colors[0];
		for (RGBColor color : colors) {
			max = findMax(max, color);
		}
		return new RGBColor(max);
	}

	public static RGBColor findMax(RGBColor a, RGBColor b) {
		if(grey(a).getRed()>=grey(b).getRed())
			return a;
		else
			return b;
	}

	public static RGBColor min(RGBColor... colors) {
		RGBColor min =colors[0];
		for (RGBColor color : colors) {
			min = findMin(min, color);
		}
		return new RGBColor(min);
	}
	public static RGBColor findMin(RGBColor a, RGBColor b) {
		if(grey(a).getRed()<=grey(b).getRed())
			return a;
		else
			return b;
	}

	public static RGBColor average(RGBColor... colors) {
		RGBColor sum = sum(colors);
		double r = sum.getRed() / colors.length;
		double g = sum.getGreen() / colors.length;
		double b = sum.getBlue()/colors.length;
		return new RGBColor(r, g, b);
	}

	public static RGBColor sum(RGBColor... colors) {
		double r, g, b;
		r = g = b = 0;
		for (RGBColor color : colors) {
			r += color.getRed();
			g += color.getGreen();
			b += color.getBlue();
		}
		return new RGBColor(r, g, b);
	}

	public static RGBColor clamp(RGBColor...colors) {
		colors[0].clamp();
		System.out.println(colors[0].getRed());
		return colors[0];
	}

	public static RGBColor wrap(RGBColor...colors) {
		colors[0].wrap();
		return colors[0];
	}
}
