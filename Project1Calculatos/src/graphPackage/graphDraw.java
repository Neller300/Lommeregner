package graphPackage;

import java.beans.Expression;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import calculatorPackage.CalcResult;
import calculatorPackage.InputHandler;
import calculatorPackage.InputNode;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

public class graphDraw
{
	graphObject Y1;
	graphObject Y2;
	graphObject Y3;
	
	public void updateGraphs()
	{
		
		
		if(Y1!=null)
		{
			drawGraph(Y1.graphExpression, (double)GraphWindowHandler.currentXmin, (double)GraphWindowHandler.currentXmax, GraphWindowHandler.theInputHandler.graph, Y1.xNodes, Color.DARKRED);
		}
		if(Y2!=null)
		{
			drawGraph(Y2.graphExpression, (double)GraphWindowHandler.currentXmin, (double)GraphWindowHandler.currentXmax, GraphWindowHandler.theInputHandler.graph, Y2.xNodes, Color.DARKBLUE );
		}
		if(Y3!=null)
		{
			drawGraph(Y3.graphExpression, (double)GraphWindowHandler.currentXmin, (double)GraphWindowHandler.currentXmax, GraphWindowHandler.theInputHandler.graph, Y3.xNodes, Color.DARKGREEN);
		}
		
	}
	
	public static void drawGraph(List<InputNode> expression, double start, double end, GraphWindowHandler theHandler, List<InputNode> xNodes, Color lineColor)
	{
		//first, find x 
		double deltaX=(end-start)/150;
		
		
		for (InputNode xNode : xNodes)
		{
			xNode.input=""+start;
		}
		
		double startY=Double.parseDouble(CalcResult.CalcFinalResult(InputHandler.prepareListForCalc(expression)));
		Point2D startPoint = theHandler.getPixelFromCordinate(start, startY);
		
		System.out.println("drawing");
		
		for(double counter =start+deltaX; counter<end; counter=counter+deltaX)
		{
			//find endpoint
			for (InputNode xNode : xNodes)
			{
				xNode.input=""+counter;
			}
			double yVal=Double.parseDouble(CalcResult.CalcFinalResult(InputHandler.prepareListForCalc(expression)));
			Point2D endPoint = theHandler.getPixelFromCordinate(counter, yVal);
			//draw line
			theHandler.gc.setLineWidth(2);
			
			theHandler.gc.setStroke(lineColor);
			theHandler.gc.strokeLine(startPoint.getX(), startPoint.getY(), endPoint.getX(), endPoint.getY());
			
			//set old startpoint to current endpoint
			startPoint=endPoint;
		}
		
		//reset xvalues
		for (InputNode xNode : xNodes)
		{
			xNode.input="X";
		}
	}
	
	public void createNewGraph(String callingExpressionSlot, List<InputNode> expression)
	{
		List<InputNode> xNodes=new ArrayList<InputNode>();
		
		for (InputNode node : expression)
		{
			if(node.input=="X")
			{
				xNodes.add(node);
			}
		}
		if(callingExpressionSlot=="Y1")
		{
			
			Y1= new graphObject(expression, xNodes);
		}
		else if(callingExpressionSlot=="Y2")
		{
			
			Y2 = new graphObject(expression, xNodes);
		}
		else if(callingExpressionSlot=="Y3")
		{
			
			Y3 = new graphObject(expression, xNodes);
		}
		
		GraphWindowHandler.theInputHandler.graph.updateGraphWindowCoordinates();
		updateGraphs();
	}
	
	public void removeGraph(String callingExpressionSlot)
	{
		updateGraphs();
	}
	
}
