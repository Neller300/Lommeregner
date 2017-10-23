package calculatorPackage;

import java.util.*;

import calculatorPackage.calculatorTools.displayType;
import calculatorPackage.calculatorTools.nodeType;
import graphPackage.GraphWindowHandler;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.ConstraintsBase;

public class InputHandler
{
	List<InputNode> trackedInput = new ArrayList<InputNode>();
	
	public List<InputNode> calculatorInput = new ArrayList<InputNode>();
	
	public List<InputNode> graph1Input = new ArrayList<InputNode>();
	public List<InputNode> graph2Input = new ArrayList<InputNode>();
	public List<InputNode> graph3Input = new ArrayList<InputNode>();
	
	public GraphWindowHandler graph = new GraphWindowHandler();
	public static boolean graphMode=false;
	
	public displayType currentDisplayMode=displayType.CALCULATOR;

	public void handleInput(nodeType type, String input, int direction)
	{
		InputNode lastNode=null;
		if(!trackedInput.isEmpty())
		{
			lastNode=trackedInput.get(trackedInput.size() - 1);
		}
		
		if (type == nodeType.NUMBER)
		{
			//check if x
			if(input == "X")
			{
				if (lastNode!=null && lastNode.itsType == nodeType.NUMBER)
				{
					// append to nodes input
					trackedInput.add(new InputNode("*", nodeType.OPERATOR, 0));
					trackedInput.add(new InputNode("X", type, direction));
				}
				else 
				{
					trackedInput.add(new InputNode("X", type, direction));
				}
			}
			
			else if (input == ".")
			{
				// if(currentnode.input.contains(".")
				if (lastNode==null || lastNode.itsType == nodeType.OPERATOR || lastNode.itsType == nodeType.PARENTHESIS)
				{
					// create new node and use input "0."
					trackedInput.add(new InputNode("0.", type, direction));
				}
				else if (lastNode.input.contains("."))
				{
					return;
				}
				else
				{
					// append to nodes input
					lastNode.addInput(input, true);
				}
			}
			// if just a number, check if lastnode is already a number and append to it, or create a new
			else if (lastNode!=null && lastNode.itsType == nodeType.NUMBER)
			{
				// append to nodes input
				lastNode.addInput(input, true);
			}
			else
			{
				// create node, add to last
				trackedInput.add(new InputNode(input, type, direction));
			}
			DisplayHandler.updateScreen(trackedInput, currentDisplayMode);
		}

		// if operator
		else if (type == nodeType.OPERATOR)
		{
			// if first in list and operator direction is to the right
			if (lastNode==null && direction == 1)
			{
				trackedInput.add(new InputNode(input, type, direction));
			}
			// if lastnode is operator, check compatibility with new operator
			else if (lastNode!=null && lastNode.itsType == nodeType.OPERATOR)
			{
				// if last operator is left sided and new is both ex. squared and plus
				if (lastNode.getOperatorDirection() == -1 && direction == 0)
				{
					trackedInput.add(new InputNode(input, type, direction));
				}
				// if last operator is both and new is right sided ex. plus and squareroot
				else if (lastNode.getOperatorDirection() == 0 && direction == 1)
				{
					trackedInput.add(new InputNode(input, type, direction));
				}
			}
			//if number and operator is no right sided(ex. squareRoot), then add operator
			else if (lastNode!=null && lastNode.itsType == nodeType.NUMBER && direction!=1)
			{
					trackedInput.add(new InputNode(input, type, direction));
			}
			else if (lastNode!=null && lastNode.itsType == nodeType.PARENTHESIS)
			{
					trackedInput.add(new InputNode(input, type, direction));
			}
			DisplayHandler.updateScreen(trackedInput, currentDisplayMode);
		}
		
		else if(type==nodeType.UTILITY)
		{
			callUtility(input);
		}
	}
	
	//switch to find the utility function wanted
	public void callUtility(String utilityCalled)
	{
		switch (utilityCalled)
		{
		case "=":
			enter();
			break;

		case "CE":
			clear();
			break;

		case "\u232B":
			delete();
			break;
			
		case "(":
		{
			InputNode leftParenthesis = new InputNode("(", nodeType.PARENTHESIS, 0);
			trackedInput.add(leftParenthesis);
			DisplayHandler.updateScreen(trackedInput, currentDisplayMode);
			break;
		}
			
		case ")":
		{
			InputNode rightParenthesis = new InputNode(")", nodeType.PARENTHESIS, 0);
			trackedInput.add(rightParenthesis);
			DisplayHandler.updateScreen(trackedInput, currentDisplayMode);
			break;
		}
		
		case "GRAPH" :
		{
			//open graph window
			startGraph();
			break;
		}

		default:
			System.out.println("Utility button not implemented!");
			break;
		}
	}

	//changeSignPressed, changes current numbers sign (-+)
	public void changeSignPressed(nodeType type, String input)
	{
		// if(trackedinput.isempty || currentnode.isOperator)
		if (trackedInput.isEmpty() || trackedInput.get(trackedInput.size() - 1).itsType == nodeType.OPERATOR)
		{
			// create node and add to list
			// InputNode number = new InputNode(input, type, direction);
			// trackedInput.add(number);
		}
		// if(currentnode.isNumber)
		if (trackedInput.get(trackedInput.size() - 1).itsType == nodeType.NUMBER)
		{
			// prepend "-" to current input
			trackedInput.get(trackedInput.size() - 1).addInput("-", false);
		}
	}

	//clear, removes all input
	public void clear()
	{
		trackedInput.clear();
		DisplayHandler.updateScreen(trackedInput, currentDisplayMode);
	}
	
	//deletes input, one at a time
	public void delete()
	{
		// check if trackedInput is empty
		if (trackedInput.isEmpty())
		{
			return;
		}
		
		InputNode lastNode=trackedInput.get(trackedInput.size() - 1);
		
		//if lastnode is number, check if input contains more than 1 char, if it does, remove 1 char
		if(lastNode.itsType==nodeType.NUMBER)
		{
			if (lastNode.input.length() <= 1)
			{
				trackedInput.remove(trackedInput.size() - 1);
			}
			else
			{
				lastNode.input = lastNode.input.substring(0, lastNode.input.length()-1);
			}
		}
		else 
		{
			// remove node from trackedlist
			trackedInput.remove(trackedInput.size() - 1);
		}
		
		DisplayHandler.updateScreen(trackedInput, currentDisplayMode);
	}

	public void enter()
	{
		if(graphMode)
		{
			return;
		}
		//check if empty
		if(trackedInput.isEmpty())
		{
			DisplayHandler.updateScreen("Please input expression", currentDisplayMode);
			return;
		}
		DisplayHandler.updateScreen(CalcResult.CalcFinalResult(prepareListForCalc(trackedInput)), currentDisplayMode);
	}
	
	public void startGraph()
	{
		graph.openGraphWindow(this);
		graphMode=true;
		calculatorInput.clear();
		calculatorInput.addAll(trackedInput);
		trackedInput.clear();
		DisplayHandler.updateScreen("Graph Mode Active", currentDisplayMode);

	}
	
	public void graphClosed()
	{
		graphMode=false;
		trackedInput=calculatorInput;
		currentDisplayMode=displayType.CALCULATOR;
		DisplayHandler.updateScreen(trackedInput, currentDisplayMode);
	}
	

	public void setToGraphFieldInput(String callingField, Label fieldLabel)
	{
		if(callingField=="Y1")
		{
			trackedInput=graph1Input;
			currentDisplayMode=displayType.Y1;
			DisplayHandler.updateScreen(trackedInput, currentDisplayMode);
		}
		else if(callingField=="Y2")
		{
			trackedInput=graph2Input;
		currentDisplayMode=displayType.Y2;
		DisplayHandler.updateScreen(trackedInput, currentDisplayMode);
		}
		else if(callingField=="Y3")
		{
			trackedInput=graph3Input;
		currentDisplayMode=displayType.Y3;
		DisplayHandler.updateScreen(trackedInput, currentDisplayMode);
		}
	}
	

	public static List<InputNode> prepareListForCalc(List<InputNode> expression)
	{
		List<InputNode> templist = new ArrayList<InputNode>();
		int leftParenthesis=0;
		int rightParenthesis=0;
		
		
		templist.add(new InputNode("(", nodeType.PARENTHESIS, 0));
		
		for (InputNode oldNode : expression)
		{
			templist.add(new InputNode(oldNode));
		}
		
		templist.add(new InputNode(")", nodeType.PARENTHESIS, 0));
		
		for (int counter = 0; counter < templist.size(); counter++)
		{
			InputNode currentNode = templist.get(counter);
			if(currentNode.input=="(")
			{
				leftParenthesis++;
			}
			else if(currentNode.input==")")
			{
				rightParenthesis++;
			}
			
			if (counter < 1)
			{
				currentNode.left = null;
				if (counter > templist.size() - 2)
				{
					currentNode.right = null;
				}
				else
				{
					currentNode.right = templist.get(counter + 1);
				}
			}
			else if (counter > templist.size() - 2)
			{
				currentNode.right = null;
				currentNode.left = templist.get(counter - 1);
			}
			else
			{
				currentNode.left = templist.get(counter - 1);
				currentNode.right = templist.get(counter + 1);
			}
		}
		//check if parenthesis are correct
		if(leftParenthesis>rightParenthesis)
		{
			//forgot right parenthesis
		}
		else if(rightParenthesis>leftParenthesis)
		{
			//forgot left parenthesis
		}
		
		return templist;
	}
	
	
}
