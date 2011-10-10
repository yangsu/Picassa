package model.expressions;

import java.util.HashMap;
/**
 * A class to keep track of all the variables
 * @author Yang
 *
 */
public class Variables {
	public static HashMap<String, Expression> variables = new HashMap<String, Expression>();
	
	public static void set(String var, Expression ex) {
		variables.put(var, ex);
	}
	
	public static Expression get(String var) {
		return variables.get(var);
	}
	
	public static boolean contains(String key) {
		return variables.containsKey(key);
	}
}
