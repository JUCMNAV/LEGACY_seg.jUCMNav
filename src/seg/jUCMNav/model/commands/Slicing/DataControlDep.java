package seg.jUCMNav.model.commands.Slicing;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * provides necessary functions to calculate data and control flow dependencies for slicing algorithm
 * @author Taha
 *
 */
public class DataControlDep {
	private String expression;
	private  ArrayList <String> criterionVariables=new ArrayList<String>();
	Boolean Relevant=false;
	Stack<String> conditionStack=new Stack<String>();
	Stack<ArrayList<String>> allStatementsStack=new Stack<ArrayList<String>>();
	/**
	 * computes dependencies during traversal according criterion variables    
	 *
	 * @param criterionVariables variables related to slicing criterion
	 */
	public DataControlDep(ArrayList <String> criterionVariables) {
		
		this.criterionVariables=criterionVariables;
		Relevant=false;
		conditionStack.clear();
		allStatementsStack.clear();
		
	}
 
	/**
	 * executes data and control flow analysis for the resp ref's expression only. 
	 * Regarding Orfork's condition , this will be handled in different method
	 *  @return all the variables within the code expression
	 */
	public ArrayList<String> analizeExpression()
	{
		
		if(expression==null || expression.isEmpty())
		{ System.err.println("empty");
			return null;
		}
		PrepareExpression();
		while (!expression.isEmpty())
		{
			if(expression.startsWith("if("))
			{
				analizeCondition();
			}
			
			
			//otherwise the expression starts with a code statement
			else 
			{
			analizeStatement();	
			
			}
		}
		ArrayList<String> statement=new ArrayList<String>();
		ArrayList<String> allVariables=new ArrayList<String>();
		// here, after building the statementstack, dependencies 
		//will be checked with relevant variables
		
		while(!allStatementsStack.empty())
		{
			
			
			statement=allStatementsStack.pop();
			for(String item:statement)
				if(!allVariables.contains(item))
					allVariables.add(item);
			//check if the left side assignment variable of the statement
			// is contained in the relevant variable,if true the statement 
			//is relevant to the slice, and the right side variables+variables of conditions(if any)
			//under which the statement lies are also added to the relevant variables list
			if(criterionVariables!=null && criterionVariables.contains(statement.get(0)))
			{
				Relevant=true;
		// System.out.println("-Statement with variables:"+statement+" is Relevant\n");
		         for(String variable:statement)
		        	 if(criterionVariables!=null && !criterionVariables.contains(variable))
		        		 criterionVariables.add(variable);
		         
			}
			else
				//System.out.println("-Statement with variables:"+statement+" is NOT Relevant\n")
				;
		}
		//System.out.println("varaibles relevant :"+criterionVariables);
		
		// printing to check 
		/* if(Relevant)
			System.out.println("Expression is Relevant to the slice\n------------------------\n");
		else
			System.out.println("NOT relevant to the slice\n---------------------------------\n");*/
		return allVariables;
		
	}
	
	private void analizeStatement()
	{
		String[] result;
		ArrayList<String> conditionVariables=new ArrayList<String>();
		result=Parsing.extractStatement(expression);
		
		String statement=result[0];
		
		//expression after subtracting the code statement
		expression=result[1];
		 ArrayList<String> statementVariables= Parsing.getVariables(statement);
		 //check whether the statement lies under conditions,if true , condition
		 // variables will be added along with statement's variables
		 if(!conditionStack.empty())
		 {
			 for(String condition:conditionStack)
			 {
				 conditionVariables=Parsing.getVariables(condition);
				 for(String variable:conditionVariables)
				 {
					 if(!statementVariables.contains(variable))
						 statementVariables.add(variable);
				 }
			 }
		 }
		 allStatementsStack.push(statementVariables);
		  
	}

	/**
	 * Handles Conditions within expression
	 */
	public void analizeCondition()
	{
		ArrayList<String> code=new ArrayList<String>();
		code=Parsing.extractCondition(expression);
		expression=code.get(1);
		//add the condition to condition-stack
		conditionStack.push(code.get(0));
		//if the code starts with '{' , it means the condition has multiple statements
		if(expression.charAt(0)=='{')
		{
			//remove the character '{' from the original code expression
			expression=expression.substring(1);
			//continue until the end of condition, stopping criterion is '}'
			while(expression.charAt(0)!='}')
			{
				//if there is an if condition inside the current condition
				if (!expression.isEmpty() && expression.startsWith("if"))
				{
					analizeCondition();
				}
				//otherwise it starts with a code statement
				else {
					analizeStatement();
				}
			}
			
			//remove the character '}' from the original code expression
			expression=expression.substring(1);
		}
				//then the condition has only one statement
				else 
				{   //has one if
					if (expression.length()>=2 && expression.startsWith("if"))
						analizeCondition();
					//otherwise has one statement
					else
						analizeStatement();
				}
					
					
					//*************
		//if the condition has elseif after 
		if(!expression.isEmpty() && expression.length()>=6 && expression.startsWith("elseif"))
		{
			analizeCondition(); 
		}
		//if it has else
		else if(!expression.isEmpty() && expression.length()>=4 && expression.startsWith("else"))
				{
			
			// remove else from expression
			expression=expression.substring(4);
			//if the code starts with '{' , it means the else has multiple statements
			if(expression.charAt(0)=='{')
			{
				//remove the character '{' from the original code expression
				expression=expression.substring(1);
				//continue until the end of condition, stopping criterion is '}'
				while(expression.charAt(0)!='}')
				{
					//if there is an if condition inside the current condition
					if (!expression.isEmpty() && expression.startsWith("if"))
					
						analizeCondition();
					
					//otherwise it starts with a code statement
					else 
						analizeStatement();
					
				}
				
				//remove the character '}' from the original code expression
				expression=expression.substring(1);
			 
				}
			
			//otherwise the else has either one condition or one statement
			else if(expression.length()>=2 && expression.startsWith("if"))
			
				analizeCondition();
				//otherwise has one statement
				else
					analizeStatement();
				}
			
		//whether there is else ,elseif, or none, the condition is removed at the end 
		conditionStack.pop();
	}
	
	/**
	 * gets the expression string
	 */
	public String getExpression()
	{
		return expression;
	}
	/**
	 * Removes whitespace, and comments from expression.Usaually, this method is called before analyzing the code expression.
	 */
	private void PrepareExpression()
	{ 
		
	expression=expression.replaceAll("(?:/\\*(?:[^*]|(?:\\*+[^*/]))*\\*+/)|(?://.*)", "");
	expression=expression.replaceAll("\\s+","");
		
		
	}
	/**
	 * sets the expression  before analyze it by {@link #analizeExpression()}
	 * @param expression the code expression as a string
	 */
	
	public void setExpression(String expression)
	{
		Relevant=false;
		conditionStack.clear();
		allStatementsStack.clear();
	  this.expression=expression;
	}
	
	/**
	 * Adds the variables within a condition into the '<em> Relevant variables List</em>'.Usually this method 
	 * is called when an <em>Orfork</em>, <em>WaitingPlace</em>, <em>StartPoint</em>,or <em>EndPoint</em> that has a condition, is encountered during backward traversal
	 * @param condition condition as a string
	 */
	public void addConditionVariables(String condition)
	{  
		setExpression(condition);
		PrepareExpression();
		ArrayList<String> varlist=Parsing.getVariables(expression);
      for(String variable:varlist)
      {
    	  if(!criterionVariables.contains(variable))
    	  criterionVariables.add(variable);
      }
     
	}
	/**
	 * gets the variables contained within a condition expression
	 * @param condition condition as a string
	 * @return list of all variables within the condition
	 */
	public ArrayList<String> getConditionVariables(String condition)
	{
		setExpression(condition);
		PrepareExpression();
		ArrayList<String> varlist=Parsing.getVariables(expression);
		return varlist;
	}
	 /**
	  * This method returns all the variables within a code of '<em>respref's </em>'expression
	  * 
	  * @return all variables contained within the code expression
	  */
    public ArrayList<String> getAllVariables()
    { 
    	
    return analizeExpression();
  	  
    }

	public  ArrayList <String> getCriterionVariables() {
		return criterionVariables;
	}

	public  void setCriterionVariables(ArrayList <String> criterionVariables) {
		this.criterionVariables = criterionVariables;
	}
	
	public void updateCriterionVariables(ArrayList<String> criterionVariables)
	{
		for(String var:criterionVariables)
			if(!this.criterionVariables.contains(var))
				this.criterionVariables.add(var);
	}
		
}
