package calculatorPackage;

import org.omg.PortableServer.ThreadPolicyOperations;

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
	
	//create constructor taking arguments to fill instance variables
	public InputNode(String newInput, nodeType newType, int newDirection)
	{
		input=newInput;
		itsType=newType;
		setOperatorDirection(newDirection);
	}
	
	//make copyconstructor
	public InputNode(InputNode oldNode)
	{
		this(oldNode.input, oldNode.itsType, oldNode.operatorDirection);
	}
	
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
	
	@Override
	public boolean equals(Object other)
	{
		if (other == null)
			return false;
		if (other == this)
			return true;
		if (!(other instanceof InputNode))
			return false;
		return false;
	}
}
