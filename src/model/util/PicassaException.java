package model.util;


/**
 * Represents an exceptional situation specific to this project.
 * 
 * @author Robert C. Duvall
 */
@SuppressWarnings("serial")
public class PicassaException extends RuntimeException
{
    // BUGBUG: should be extendible, i.e., get message text from file 
    public static PicassaException BAD_TOKEN =
        new PicassaException("unrecognized input:");

    public static PicassaException BAD_ARGUMENTS =
        new PicassaException("not enough arguments");

    public static PicassaException BAD_SYNTAX =
        new PicassaException("ill-formatted expression");
    
    public static PicassaException BAD_COLORVALUE =
        new PicassaException("ill-defined color value");
        
//    public static ParserException BAD_FUNCTION =
//        new ParserException("incorrect number of arguments for");
//    
    public static PicassaException BAD_PARENTHESES =
        new PicassaException("parentheses mismatched");
    /**
     * Create exception with given message
     *  
     * @param message explanation of problem
     */
    public PicassaException(String message)
    {
        super(message);
    }
    
    public static PicassaException badFunction(String functionName) {
    	return new PicassaException(functionName + " has an incorrect number of parameters");
    }
    
    public static PicassaException badToken(String token) {
    	return new PicassaException("unrecognized input: "+token);
    }
    
    public static PicassaException badResourceFile(String key) {
    	return new PicassaException(key + " can't be found in the resource file");
    }
}
