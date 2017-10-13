package calculatorPackage;

import java.util.List;

public class DisplayHandler
{
	
	// Declare a Text call it textdisplayed
	public static GUICreator theGUICreator;
	  // Declare method: UpdateScreen(List<inputnodes>)
	      // for each loop prints input nodes
	public static void updateScreen(List<InputNode> inputNodeList)
	{
		
		//go through each node
		String temp="";
		for(InputNode node : inputNodeList)
		{
			//System.out.println(node.input+" type"+node.itsType+" dir"+node.getOperatorDirection());
			temp=temp+node.input;
		}
		updateScreen(temp);
	}
	  
	
	  // Declare method: UpdateScreen(Result)
	     // return result as a string. 
	       // if result is valid
	          // print result
	       // else if result isn't valid
	          // print "invalid result"
	
	
	public static void updateScreen(String result)
	{
		
		theGUICreator.text.setText(result);
	}
	
}
