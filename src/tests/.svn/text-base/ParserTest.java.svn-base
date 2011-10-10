package tests;

import java.awt.Color;

import model.Parser;
import model.util.RGBColor;
import static org.junit.Assert.*;
import org.junit.*;

public class ParserTest
{
    // useful constants
    private static final RGBColor BLACK = new RGBColor(Color.BLACK);
    private static final RGBColor GRAY = new RGBColor(Color.GRAY);
    private static final RGBColor WHITE = new RGBColor(Color.WHITE);
    
    // state
    Parser myParser;

    
    @Before
    public void setup ()
    {
        myParser = new Parser();
    }
    
    @Test
    public void testConstant ()
    {
        RGBColor actual = myParser.makeExpression("-1").evaluate();
        assertTrue(BLACK.equals(actual));
        actual = myParser.makeExpression("1").evaluate();
        assertTrue(WHITE.equals(actual));
        actual = myParser.makeExpression("0").evaluate();
        assertTrue(GRAY.equals(actual));
    }
    
    @SuppressWarnings("deprecation")
	@Test
    public void testColors ()
    {	
    	int num=50;
    	String[] c1=new String[num];
    	String[] c2=new String[num];
    	for(int i=0;i<num;i++) {
    		double r=Math.random()*300-150;
    		double g=Math.random()*300-150;
    		double b=Math.random()*300-150;
    		
		    c1[i]="["+r+","+g+","+b+"]";
		    c2[i]=new RGBColor(r,g,b).toString().replaceAll("\\s+", "");    		
    	}
    	assertEquals(c1,c2);
    }
    @Test
    public void testUnary() {
    	int num=50;
    	for(int i=0;i<num;i++) {
    		double r=Math.random()*300-150;
    		double g=Math.random()*300-150;
    		double b=Math.random()*300-150;
    		String infix="!["+r+","+g+","+b+"]";	
    		 RGBColor result1 = myParser.makeExpression(infix).evaluate();
    		 RGBColor result2=new RGBColor(-r,-g,-b);
    		 assertEquals(result1,result2);
    	}
    }
    @Test
    public void testVariables() {
    	myParser.makeExpression("a=1");
    	myParser.makeExpression("b=2");
    	myParser.makeExpression("c=b/a");
    	myParser.makeExpression("d=c/b");
    	assertEquals(myParser.makeExpression("b").evaluate(),myParser.makeExpression("c").evaluate());
    	assertEquals(myParser.makeExpression("1").evaluate(),myParser.makeExpression("d").evaluate());
    	assertEquals(myParser.makeExpression("1/c").evaluate(),myParser.makeExpression("a/b").evaluate());
    	assertEquals(myParser.makeExpression("0").evaluate(),myParser.makeExpression("b-c").evaluate());
    	
    }
    
    public void testExpressions() {
    	myParser.makeExpression("x").toString();
    	myParser.makeExpression("b=2");
    }
    
}
