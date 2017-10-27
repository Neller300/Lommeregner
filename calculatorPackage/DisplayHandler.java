package calculatorPackage;

import java.util.List;

import calculatorPackage.calculatorTools.displayType;
import graphPackage.GraphGUI;

public class DisplayHandler
{
	
	// Declare a Text call it textdisplayed
	public static GUICreator theGUICreator;
	  // Declare method: UpdateScreen(List<inputnodes>)
	      // for each loop prints input nodes
	public static void updateScreen(List<InputNode> inputNodeList, displayType wantedDisplay)
	{
		//go through each node
		String temp="";
		for(InputNode node : inputNodeList)
		{
			//System.out.println(node.input+" type"+node.itsType+" dir"+node.getOperatorDirection());
			temp=temp+node.input;
		}
		updateScreen(temp, wantedDisplay);
	}
	
	public static void updateScreen(String result, displayType wantedDisplay)
	{
		if(wantedDisplay==displayType.CALCULATOR)
		{
			theGUICreator.text.setText(result);
		}
		else if(wantedDisplay==displayType.Y1)
		{
			GraphGUI.functionMenuDic.get("Y1").setText(result);
		}
		else if(wantedDisplay==displayType.Y2)
		{
			GraphGUI.functionMenuDic.get("Y2").setText(result);
		}
		else if(wantedDisplay==displayType.Y3)
		{
			GraphGUI.functionMenuDic.get("Y3").setText(result);
		}
	}
}
