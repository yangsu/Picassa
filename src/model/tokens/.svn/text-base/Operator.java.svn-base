package model.tokens;

import model.util.PicassaException;

/**
 * This class specifies how an operator gets the number of operands it needs.
 * Since all values are know, they are stored in the properties file
 * 
 * @author Yang
 * 
 */
public class Operator extends OperationToken {

	public Operator(String t) {
		super(t);
	}

	public int getNum() {
		String paramLoc = "";
		for (String key : patternList) {
			if (token.matches(key)) {
				try {
					paramLoc = key + "Param";
					return Integer.parseInt(resources.getString(paramLoc));
				} catch (Exception e) {
					throw PicassaException.badResourceFile(paramLoc);
				}
			}
		}
		throw PicassaException.badResourceFile(paramLoc);

	}

}
