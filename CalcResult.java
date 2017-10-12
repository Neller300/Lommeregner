package calculatorPackage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import calculatorPackage.calculatorTools.nodeType;
import operatorPackage.*;

public class CalcResult {


	public static final Map<String, OperatorMaster> operatorDic;
	static {
		operatorDic = new HashMap<String, OperatorMaster>();
		operatorDic.put("+", new OP_addition());
		operatorDic.put("-", new OP_subtraction());
		operatorDic.put("/", new OP_multiply());
		operatorDic.put("*", new OP_division());
		operatorDic.put("\u00B2", new OP_squared());
		operatorDic.put("\u221A", new OP_sqrRoot());
	}
	// String CalcResult (List<InputNode> expression)
	String CalcFinalResult (List<InputNode> expression) {
		
		// While (expression > 1)
		while(expression.size() > 1) {
			
			int highestPriority = 0;
			InputNode bestCandidate=null;
			
			// foreach (InputNode aNode in expression)
			for(InputNode aNode : expression) 
			{
				// if(is an operator)
				if(aNode.itsType == nodeType.OPERATOR) 
				{
					// Finder højest prioritet
					if(aNode.nodePriority > highestPriority) 
					{
						bestCandidate = aNode;
						highestPriority = aNode.nodePriority;
					}
				}
			}
						// brug hash til at finde rigtig operator
			
						Double leftVal = 0d; 
						Double rightVal = 0d;
						
						// check naboer. if left or right nabo is an operator or null inset "0"
						if(bestCandidate.left == null || bestCandidate.left.itsType == nodeType.OPERATOR) {
						}
						else {
							leftVal = Double.parseDouble(bestCandidate.left.input);
						}
						
						if(bestCandidate.right == null || bestCandidate.right.itsType == nodeType.OPERATOR) {
						}
						else {
							rightVal = Double.parseDouble(bestCandidate.right.input);
						
						// string tempVal = operator.calc(left, right). toString
						String tempVal = operatorDic.get(bestCandidate.input).calc(leftVal, rightVal).toString();
						// aNode.input = tempVal
						bestCandidate.input = tempVal;
						// aNode.isoperator = false
						bestCandidate.itsType = nodeType.NUMBER;
							
					}
				
						boolean keepLeft=true;
						boolean keepRight=true;
				
				// Look left
				// if (aNode.left != null)
				if(bestCandidate.left != null) {
					// (aNode.left.isoperator == false)
					if (bestCandidate.left.itsType == nodeType.OPERATOR)
					{
						//nothing
					}
					else
					{
						keepLeft=false;
					}
				// if (aNode.left.left != null)
				if(bestCandidate.left.left != null) {
					// (aNode.left.left.right = aNode)
					bestCandidate.left.left.right = bestCandidate;
				}
			}	
				// Look right
				// if (aNode.right != null)
				if(bestCandidate.right != null) {
					// (aNode.right.isoperator == false)
					if(	bestCandidate.right.itsType == nodeType.OPERATOR)
					{
						
					}
					else
					{
						keepRight=false;
					}
				// if (aNode.right.right != null)
				if(bestCandidate.right.right != null) {
					// (aNode.right.right.left = aNode)
					bestCandidate.right.right.left = bestCandidate;
				}
			}
				// Remove aNode (left, right)
				if(keepRight==false)
				{
					expression.remove(bestCandidate.right);
				}
				
				if(keepLeft==false)
				{
					expression.remove(bestCandidate.left);
				}
				
				
		}		//return the last node in the list
				return expression.get(0).input;	
	}
}
