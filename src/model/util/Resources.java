package model.util;

import java.util.ResourceBundle;

public interface Resources {
	ResourceBundle resources = ResourceBundle.getBundle("operators");
	String functionSeperator = resources.getString("functionSeperator");
	String constantSeperator = resources.getString("constantSeperator");
	public static final String f0 = resources.getString("f0");
	public static final String f1 = resources.getString("f1");
	public static final String f2 = resources.getString("f2");
	public static final String constant = resources.getString("constant");
	public static final String var = resources.getString("var");
	public static final String color = resources.getString("color");
	public static final String unary = resources.getString("unary");
	public static final String binary = resources.getString("binary");
	public static final String assignment = resources.getString("assignment");
	public static final String seperator = resources.getString("seperator");
	public static final String openingGroup = resources
			.getString("openingGroup");
	public static final String closingGroup = resources
			.getString("closingGroup");

	String regex = f0 + "|" + f1 + "|" + f2 + "|" + openingGroup + "|"
			+ closingGroup + "|" + assignment + "|" + seperator + "|"
			+ constant + "|" + unary + "|" + binary + "|" + color + "|" + var;
	String[] patternList = { f0, f1, f2, openingGroup, closingGroup,
			assignment, seperator, constant, unary, binary, color, var };
}