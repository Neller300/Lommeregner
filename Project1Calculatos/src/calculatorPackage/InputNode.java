package calculatorPackage;

import calculatorPackage.calculatorTools.nodeType;

public class InputNode
{
	//Create variables 
	//string input - the input used for calculations
	public String input;
	public int nodePriority;
	public InputNode left;
	public InputNode right;
	public nodeType itsType;
    private int operatorDirection;
	
	
	//int priority - priority used for precedence calculation
	//inputNode left - its left neighbor
	//inputNode Right - its right neighbor
	//boolean isOperator - true if operator
	
	//create constructor taking arguments to fill instance variables
	public InputNode(String newInput, nodeType newType, int newDirection)
	{
		input=newInput;
		itsType=newType;
		setOperatorDirection(newDirection);
	}
	
	//make copyconstructor//maybe not needed
	
	//add method to append input with newinput
	public void addInput(String newInput, boolean append)
	{
		if(append)
		{
			input = input + newInput;
		}
		else
		{
			input = newInput+input;
		}
	}
	
	public int getOperatorDirection()
	{
		return operatorDirection;
	}
	
	public void setOperatorDirection(int newDirection)
	{
		operatorDirection=newDirection;
	}
}
