package seg.jUCMNav.model.commands.Slicing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parsing {
	
	/**
	 * This method extracts a code statement from code <em>only</em> when the code starts with a code statement  
	 * @param expression code as string
	 * @return string array with two elements: the extracted statement, and the rest of the code
	 */
	public static String[] extractStatement(String expression)
	{ 
		// 2 means that the split will apply only once
		String[] result=expression.split(";",2);
		//System.out.println("Statement:"+result[0]+"\nRest Of Code:\n"+result[1]);
		
		return result;
	}
	
	/**
	 * Extracts condition from the beginning of a code expression.Only works when Code 
	 * expression starts with <em>"if"</em> or <em>"elseif"</em>
	 * @param expression code as string
	 * @return An array string with two elements: the condition,and the rest of the code remaining after subtracting the condition
	 */
public static ArrayList<String> extractCondition(String expression)

{
	int parenthesesCount=1,i;
	ArrayList<String> result=new ArrayList<String>();
	if(expression.startsWith("if"))
	
		//remove "if" from expression
		expression=expression.substring(2);
	else if(expression.startsWith("elseif"))
		//remove elseif
		expression=expression.substring(6);
		//we start from index 1 , since index 0 contains '('
	for( i=1;parenthesesCount!=0;i++)
	{
		if(expression.charAt(i)=='(')
			parenthesesCount++;
		else if(expression.charAt(i)==')')
			parenthesesCount--;
	}
	/*extract the condition from the code;since i was incremented
	  before exiting the loop, we end at i instead of i+1 */
	result.add(expression.substring(0,i));
	/*extract the rest of the code after omitting the condition,
     since i was incremented before exiting the loop, we start from i instead of i+1*/
	result.add(expression.substring(i));
	return result;
}

/**
 * gets the variables at the right side of assignment code statement
 * @param statement code statement as string
 * @return 
 */


/**
 * extracts variables from a code statement or a predicate<em>'if condition</em>'
 * @param expression code statement or a condition
 * @return a list of all variables within expression (with repeated ones excluded)
 */
public static ArrayList<String> getVariables(String expression)
{
	 Pattern mypattern = Pattern.compile("[a-zA-Z_$][a-zA-Z_$0-9]*");
	 ArrayList<String> Variables=new ArrayList<String>();
	 Matcher mymatcher = mypattern.matcher(expression);
	 while (mymatcher.find()) {
		      //  To avoid repeated variables
		      if(!Variables.contains(mymatcher.group(0)))
               Variables.add( mymatcher.group(0)) ;
		  
		}
	 Variables.removeAll(Collections.singleton("true"));
	 Variables.removeAll(Collections.singleton("false"));
	 return Variables;

}


}
