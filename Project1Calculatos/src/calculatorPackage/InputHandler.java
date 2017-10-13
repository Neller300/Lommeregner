package calculatorPackage;

import java.util.*;

import calculatorPackage.calculatorTools.nodeType;

public class InputHandler
{
	List<InputNode> trackedInput = new ArrayList<InputNode>();

	public void handleInput(nodeType type, String input, int direction)
	{

		System.out.println("buttonPressed "+direction+" "+input+" "+type.toString());
		// Branching
		// if number
		if (type == nodeType.NUMBER)
		{
			if (input == ".")
			{

				// if(currentnode.input.contains(".")
				if (trackedInput.isEmpty() || trackedInput.get(trackedInput.size() - 1).itsType == nodeType.OPERATOR)
				{
					// create new node and use input "0."
					InputNode number = new InputNode("0.", type, direction);
					trackedInput.add(number);
				}
				else if (trackedInput.get(trackedInput.size() - 1).input.contains("."))
				{
					// return;
					return;
				}

				// if(trackedinput.isempty ||trackedinput.isoperator)
				// else
				else
				{
					// append to nodes input
					trackedInput.get(trackedInput.size() - 1).addInput(input, true);
				}
			}
			// if(trackedinput.empty || trackedinput.isoperator
			else if (trackedInput.isEmpty() || trackedInput.get(trackedInput.size() - 1).itsType == nodeType.OPERATOR)
			{
				// create node, add to last
				InputNode number = new InputNode(input, type, direction);
				trackedInput.add(number);

			}
			// else
			else
			{
				// append to nodes input
				trackedInput.get(trackedInput.size() - 1).addInput(input, true);
			}

			
		}

		// if operator
		if (type == nodeType.OPERATOR)
		{
			// if(trackedinput.isEmpty
			if (trackedInput.isEmpty() && direction == 1)
			{
				// create node add to list
				InputNode operator = new InputNode(input, type, direction);
				trackedInput.add(operator);
			}

			// if(trackedinput.isEmpty
			// if(trackedInput.isEmpty())
			// {
			// // create node add to list
			// InputNode operator = new InputNode(input, type);
			// trackedInput.add(operator);
			// }

			// // if(currentnode.isNumber)
			// if (trackedInput.get(trackedInput.size() - 1).itsType == nodeType.OPERATOR) {
			// // create node and add to list
			// InputNode number = new InputNode(input, type);
			// trackedInput.add(number);
			// }

			// if(currentnode.isOperator)
			else if (!trackedInput.isEmpty() && trackedInput.get(trackedInput.size() - 1).itsType == nodeType.OPERATOR)
			{

				// if(currentnode.direction==l(-1) || direction==m(0))
				if (trackedInput.get(trackedInput.size() - 1).getOperatorDirection() == -1 && direction == 0)
				{
					System.out.println("-1 and 0 called");
					// create node and add to list
					InputNode operator = new InputNode(input, type, direction);
					trackedInput.add(operator);
				}
				// if(currentnode.direction==m(0) || direction==r(1))
				if (trackedInput.get(trackedInput.size() - 1).getOperatorDirection() == 0 && direction == 1)
				{
					//System.out.println(""+trackedInput.get(trackedInput.size() - 1).getOperatorDirection());
					//System.out.println(""+trackedInput.get(trackedInput.size() - 1).itsType.toString() );
					//System.out.println("0 and 1 called");
					// create node and add to list
					InputNode operator = new InputNode(input, type, direction);
					trackedInput.add(operator);
				}
			}
			else if(!trackedInput.isEmpty() && trackedInput.get(trackedInput.size() - 1).itsType == nodeType.NUMBER)
			{
				if(direction!=1)
				{
					InputNode operator = new InputNode(input, type, direction);
					trackedInput.add(operator);
				}
			}
			else
			{
				return;
			}
		}
		System.out.println("derp");
		DisplayHandler.updateScreen(trackedInput);
	}

	// void changeSignPressed()
	public void changeSignPressed(nodeType type, String input)
	{
		// if(trackedinput.isempty || currentnode.isOperator)
		if (trackedInput.isEmpty() || trackedInput.get(trackedInput.size() - 1).itsType == nodeType.OPERATOR)
		{
			// create node and add to list
			//InputNode number = new InputNode(input, type, direction);
			//trackedInput.add(number);
		}
		// if(currentnode.isNumber)
		if (trackedInput.get(trackedInput.size() - 1).itsType == nodeType.NUMBER)
		{
			// prepend "-" to current input
			trackedInput.get(trackedInput.size() - 1).addInput("-", false);
		}
	}

	// void clear()
	public void clear()
	{
		// Clear the input nodes
		trackedInput.clear();
		// Update display with empty string
		// updateScreen();
	}

	public void delete()
	{
		// check if trackedInput is empty
		if (trackedInput.isEmpty())
		{
			// if empty, return
			return;
		}

		// if(currentnodeinput<=1)
		if (trackedInput.get(trackedInput.size() - 1).input.length() <= 1)
		{
			// remove node from trackedlist
			trackedInput.remove(trackedInput.size() - 1);

		}
		// else removeCharAt(input.length-1)
		else
		{
			String tempString = trackedInput.get(trackedInput.size() - 1).input;
			tempString = tempString.substring(0, tempString.length() - 1);
			trackedInput.get(trackedInput.size() - 1).input = tempString;
		}
		// void enterPressed()
		/*
		 * public void enterPressed() { // if(trackedinput.isEmpty)
		 * if(trackedInput.isEmpty()) { // return; return; } // else else {
		 * //list<inputnodes> templist = new Arraylist<inputnode>();
		 * 
		 * } // list<inputnodes> templist = new Arraylist<inputnode>(); // foreach
		 * oldnode in trackedlist // inputnode newcopy = oldnode.copy(); // add newcopy
		 * to template // display.show(calcresultfinal(calcParenthesis(templist)));
		 * 
		 * }
		 */
	}
	
	public void calcParenthesis(List<InputNode> expression)
	{
		//solve parenthesis
		
		
	}
}
