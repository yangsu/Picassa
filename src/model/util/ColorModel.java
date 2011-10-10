package model.util;



/**
 * Simple utility class that provides functions to convert between color spaces.
 * 
 * Details and constants derived from:
 *   http://www.answers.com/topic/yuv
 * 
 * @author Robert C. Duvall
 */
public class ColorModel
{
	/**
	 * Convert color from RGB to YUV color space.
	 */
	public static RGBColor rgb2ycrcb (RGBColor... colors)
	{
		if(colors.length==1) {
			return new RGBColor(
					colors[0].getRed() *  0.2989 + colors[0].getGreen() *  0.5866 + colors[0].getBlue() *  0.1145,
					colors[0].getRed() * -0.1687 + colors[0].getGreen() * -0.3312 + colors[0].getBlue() *  0.5,
					colors[0].getRed() *  0.5000 + colors[0].getGreen() * -0.4183 + colors[0].getBlue() * -0.0816);
		}
		else
			throw PicassaException.badFunction("rgb2ycrcb");
	}

	/**
	 * Convert color from YUV to RGB color space.
	 */
	public static RGBColor ycrcb2rgb (RGBColor... colors)
	{
		if(colors.length==1) {
			return new RGBColor(
					colors[0].getRed() + colors[0].getBlue() *  1.4022,
					colors[0].getRed() + colors[0].getGreen() * -0.3456 + colors[0].getBlue() * -0.7145,
					colors[0].getRed() + colors[0].getGreen() *  1.7710);
		}
		else
			throw PicassaException.badFunction("rgb2ycrcb");
		
	}
}
