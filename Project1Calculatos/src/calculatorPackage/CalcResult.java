package calculatorPackage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import calculatorPackage.calculatorTools.nodeType;
import operatorPackage.*;

public class CalcResult
{

	public static final Map<String, OperatorMaster> operatorDic;
	static
	{
		operatorDic = new HashMap<String, OperatorMaster>();
		operatorDic.put("+", new OP_addition());
		operatorDic.put("‒", new OP_subtraction());
		operatorDic.put("/", new OP_division());
		operatorDic.put("·", new OP_multiply());
		operatorDic.put("\u00B2", new OP_squared());
		operatorDic.put("\u221A", new OP_sqrRoot());
		operatorDic.put("Sin", new OP_sinus());
		operatorDic.put("Cos", new OP_cos());
		operatorDic.put("Tan", new OP_tan());
		operatorDic.put("Sin⁻¹", new OP_invSin());
		operatorDic.put("Cos⁻¹", new OP_invCos());
		operatorDic.put("Tan⁻¹", new OP_invTan());
		operatorDic.put("^", new OP_exponent());
	}

	// String CalcResult (List<InputNode> expression)
	public static String CalcFinalResult(List<InputNode> expression)
	{
		//check if expressionlist is empty, if so, bad parenthesis
		if(expression.isEmpty())
		{
			return "Missing Parenthesis";
		}
		
		//to keep precedence, start with leftmost part of expression
		InputNode currentNode = expression.get(0);

		//until a single node remains, keep calculating parts of expression
		while (currentNode.left != null || currentNode.right != null)
		{
			//go through neighbors in the right direction until a closing parenthesis is meet
			if (currentNode.input == ")")
			{
				//find part of expression between parenthesis and calculate that part
				//remember closing parenthesis
				InputNode endParenthesis = currentNode;
				
				//set first node to be included in the expressionpart
				currentNode = currentNode.left;

				// cut of last part of expression
				currentNode.right = null;
				
				//find startNode
				while (currentNode.input != "(")
				{
					currentNode = currentNode.left;
				}

				// cut of first part of expression
				currentNode.right.left = null;

				//call the calcpart function
				String expressionResult = calcPartResult(currentNode.right);
				
				//put result into first number after opening parethesis and make sure its type is number
				currentNode.right.input = expressionResult;
				currentNode.right.itsType= nodeType.NUMBER;

				// find left neighbor of "(" and set neighbors - ie. effectively removing "(" to ")" from the expression and replacing it with the node with the result from calcpart
				if (currentNode.left != null)
				{
					currentNode.left.right = currentNode.right;
				}

				currentNode.right.left = currentNode.left;

				// find right neighbor of ")"
				currentNode.right.right = endParenthesis.right;
				if (endParenthesis.right == null)
				{

				}
				else
				{
					endParenthesis.right.left = currentNode.right;
				}

				currentNode = currentNode.right;
			}

			//if current nodes right is null, end of expression, current node now contains the final result
			if (currentNode.right == null)
			{
				//incase of a whole number, remove .0 resulting from double parse
				if(currentNode.input.length()<=2)
				{
					return currentNode.input;
				}
					
				String result= currentNode.input;
				String temp = result.substring(result.length() - 2);
			
				if(temp.contentEquals(".0"))
				{
					
					result=result.substring(0, result.length() - 2);
				}
				
				return result;
			}
			//go to next neighbor to the right
			currentNode = currentNode.right;
		}
		return "Calculation invalid";
	}

	public static String calcPartResult(InputNode firstNode)
	{
		//since expression nodes are in reverse order, select last to get first
		InputNode startNode = firstNode;
		
		// While startNode has neighbors, reduce expression
		while (!(startNode.left == null && startNode.right == null))
		{
			//find FIRST candidate with the highest priority (ensures precedence in both operator and in left-to-right precedence)
			int highestPriority = 0;
			InputNode bestCandidate = null;
			InputNode currentCandidate = startNode;

			// go through each right neighbor until a null is reached
			while (currentCandidate != null)
			{
				// only check operators
				if (currentCandidate.itsType == nodeType.OPERATOR)
				{
					//get operator priority from hashmap
					int currentPriority = operatorDic.get(currentCandidate.input).getPriority();
					//if higher priority, set as bestcandidate
					if (currentPriority > highestPriority)
					{
						bestCandidate = currentCandidate;
						highestPriority = currentPriority;
					}
				}
				currentCandidate = currentCandidate.right;
			}
			//make sure a bestcandidate was selected
			if (bestCandidate == null)
			{
				System.out.println("WARNING!");
				return "Calculations failed, check expression";
			}
			
			//set startval of left and right numbers for operator
			Double leftVal = 0d;
			Double rightVal = 0d;
			// check neighbors. if left or right neighbor is an operator or null keep "0", else use its input
			if (!(bestCandidate.left == null || bestCandidate.left.itsType == nodeType.OPERATOR))
			{
				leftVal = Double.parseDouble(bestCandidate.left.input);	
			}
			
			if (!(bestCandidate.right == null || bestCandidate.right.itsType == nodeType.OPERATOR))
			{
				rightVal = Double.parseDouble(bestCandidate.right.input);	
			}
			
			// get the value of the operators calculation, and set operator to number with result as its input
			String tempVal = operatorDic.get(bestCandidate.input).calc(leftVal, rightVal).toString();
			bestCandidate.input = tempVal;
			bestCandidate.itsType = nodeType.NUMBER;

			// Look left to reset neighbors
			if (bestCandidate.left != null && bestCandidate.left.itsType != nodeType.OPERATOR)
			{
				// check if bestcandidate.left is startNode, if so make startNode bestcandidate (ensure left-to-right precendence)
				if (bestCandidate.left.equals(startNode))
				{
					startNode = bestCandidate;
				}

				// if operators left numbers left neighbor exists, set relationships
				if (bestCandidate.left.left != null)
				{
					bestCandidate.left.left.right = bestCandidate;
					bestCandidate.left = bestCandidate.left.left;
				}
				else
				{
					bestCandidate.left = null;
				}
			}
			else if (bestCandidate.left == null)
			{
				//incase of Bestcandidate operators left value is null, make sure startnode is set to bestcandidate
				startNode = bestCandidate;
			}
			
			// Look right to reset neighbors
			if (bestCandidate.right != null && bestCandidate.right.itsType != nodeType.OPERATOR)
			{
				// if operator right numbers right neighbor exists, set relationships
				if (bestCandidate.right.right != null)
				{
					bestCandidate.right.right.left = bestCandidate;
					bestCandidate.right = bestCandidate.right.right;
				}
				else
				{
					bestCandidate.right = null;
				}
			}
		}
		return startNode.input;
	}
}
