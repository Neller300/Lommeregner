package graphPackage;

import java.util.ArrayList;
import java.util.List;

import calculatorPackage.InputNode;

public class graphObject
{
	public List<InputNode> graphExpression= new ArrayList<InputNode>();
	public List<InputNode> xNodes = new ArrayList<InputNode>();
	
	public graphObject(List<InputNode> theExpression, List<InputNode> theXNodes)
	{
		graphExpression=theExpression;
		xNodes=theXNodes;
	}
}
