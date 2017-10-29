package graphPackage;

import java.util.ArrayList;
import java.util.List;
import java.util.function.DoubleToLongFunction;

import calculatorPackage.InputHandler;
import calculatorPackage.InputNode;
import calculatorPackage.calculatorTools.nodeType;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.OverrunStyle;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class GraphWindowHandler
{
	public static int currentYmax=20;
	public static int currentYmin=-20;
	public static int currentXmax=20;
	public static int currentXmin=-20;
	
	public int currentSetYmax=10;
	public int currentSetYmin=-10;
	public int currentSetXmax=10;
	public int currentSetXmin=-10;
	
	public Double canvasSize = 580d;
	
	public double panStartX=0d;
	public double panStartY=0d;
	
	public GraphGUI theGraphGUI;
	
	public GraphicsContext gc;
	public GraphicsContext hgc;
	
	public static InputHandler theInputHandler;
	
	public graphDraw theGDraw= new graphDraw();
	
	public void openGraphWindow(InputHandler handler)
	{
		theInputHandler=handler;
		Stage stage = new Stage();
        stage.setTitle("Graph Tool");
        theGraphGUI=new GraphGUI();
        Group root=theGraphGUI.createGUI(canvasSize);
        stage.setScene(new Scene(root, 795, 795));
        
        gc = theGraphGUI.graphCanvas.getGraphicsContext2D();
		hgc = theGraphGUI.axisDescCanvas.getGraphicsContext2D();
		
        
        theGraphGUI.graphCanvas.addEventHandler(MouseEvent.MOUSE_DRAGGED, 
     	       new EventHandler<MouseEvent>() {
     	           @Override
     	           public void handle(MouseEvent e) {
     	        	   
     	               dragPaning(e.getScreenX(), e.getScreenY());
     	           }
     	       });
        
        theGraphGUI.graphCanvas.addEventHandler(MouseEvent.MOUSE_PRESSED, 
      	       new EventHandler<MouseEvent>() {
      	           @Override
      	           public void handle(MouseEvent e) { 
      	               startPaning(e.getScreenX(), e.getScreenY());
      	           }
      	       });
        
        theGraphGUI.graphCanvas.addEventHandler(MouseEvent.MOUSE_RELEASED, 
       	       new EventHandler<MouseEvent>() {
       	           @Override
       	           public void handle(MouseEvent e) { 
       	               endPaningDrag();
       	           }
       	       });
        
        theGraphGUI.graphCanvas.addEventHandler(ScrollEvent.SCROLL, 
        	       new EventHandler<ScrollEvent>() {
        	           @Override
        	           public void handle(ScrollEvent e) { 
        	        	  
        	              zoomScroll(e.getDeltaY());
        	           }
        	       });
        
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                System.out.println("closing");
                //tell calculator
                handler.graphClosed();
            }
        });
        
        
        updateGraphWindowCoordinates();
        
        stage.show();
	}
	
	public void updateGraphWindowCoordinates()
	{
		gc.clearRect(0, 0, canvasSize, canvasSize);
		hgc.clearRect(0, 0, theGraphGUI.axisDescCanvas.getHeight(), theGraphGUI.axisDescCanvas.getWidth());
		
		createGridLines(gc, hgc);
        createCenterAxis(gc);
	}
	
	public void createCenterAxis(GraphicsContext gc)
	{
		gc.setLineWidth(2);
		gc.setStroke(Color.BLACK);
		
		if(currentYmax>0 && currentYmin<0)
		{
			//draw xAxis
			
			Point2D yBottom=getPixelFromCordinate(currentXmin, 0);
			Point2D yTop=getPixelFromCordinate(currentXmax, 0);
			gc.strokeLine(yBottom.getX(), yBottom.getY(), yTop.getX(), yTop.getY());
			
		}
		
	
		if(currentXmax>0 && currentXmin<0)
		{
			Point2D xBottom=getPixelFromCordinate(0, currentYmin);
			Point2D xTop=getPixelFromCordinate(0, currentYmax);
			gc.strokeLine(xBottom.getX(), xBottom.getY(), xTop.getX(), xTop.getY());
			
		}
		
		
	}
	
	public Point2D getPixelFromCordinate(double xVal, double yVal)
	{
		//get absolute lenght between xmax and xmin
		double xmaxmin=(double)Math.abs(currentXmax-currentXmin);
		double ymaxmin=(double)Math.abs(currentYmax-currentYmin);
		
		//get current x distance between xmin and xmax
		double xDiff=(xVal-currentXmin)/xmaxmin;
		double yDiff=(yVal-currentYmin)/ymaxmin;
		
		//scale to canvas
		return new Point2D(xDiff*canvasSize, canvasSize-(yDiff*canvasSize));
	}
	
	public void createGridLines(GraphicsContext gc, GraphicsContext hgc)
	{
		double logX=Math.log10((double)Math.abs(currentXmax-currentXmin));
		int currentLogStateX=(int)logX;
		if(logX-(double)currentLogStateX>0.7)
		{
			currentLogStateX=currentLogStateX+1;
		}
		else
		{
			currentLogStateX=currentLogStateX;
		}
		
		int powerX=(int)Math.pow(10, (double)currentLogStateX);
		powerX=powerX/10;
		
		double logY=Math.log10((double)Math.abs(currentYmax-currentYmin));
		int currentLogStateY=(int)logY;
		if(logY-(double)currentLogStateY>0.7)
		{
			currentLogStateY=currentLogStateY+1;
		}
		else
		{
			currentLogStateY=currentLogStateY;
		}
		
		int powerY=(int)Math.pow(10, (double)currentLogStateY);
		powerY=powerY/10;
		
		
		System.out.println("power:"+powerY+" log"+logY+" "+currentLogStateY);
		gc.setLineWidth(1);
		gc.setStroke(Color.LIGHTGRAY);
		hgc.setFont(new Font("Arial", 12));
		/*boolean notDone=true;
		boolean gxpNotDone=true;
		boolean gxnNotDone=true;
		boolean gypNotDone=true;
		boolean gynNotDone=true;*/
		
		int minPosX = (currentXmin/powerX);
		int maxPosX = (currentXmax/powerX);
		int minPosY = (currentYmin/powerY);
		int maxPosY = (currentYmax/powerY);
		
		int numberOfXLines=maxPosX-minPosX;
		int numberOfYLines=maxPosY-minPosY;
		int xAxisLabelPlacement=1;
		if(numberOfXLines<=10)
		{
			xAxisLabelPlacement=1;
		}
		else if(numberOfXLines<=50)
		{
			xAxisLabelPlacement=5;
		}
		
		int yAxisLabelPlacement=1;
		if(numberOfYLines<=10)
		{
			yAxisLabelPlacement=1;
		}
		else if(numberOfYLines<=50)
		{
			yAxisLabelPlacement=5;
		}
		
		
		for(int xLine = minPosX; xLine<=maxPosX; xLine++)
		{
			int gridXPos=xLine*powerX;
			
			if(xLine%10==0)
			{
				gc.setStroke(Color.GRAY);
				Point2D xBottom=getPixelFromCordinate((double)gridXPos, currentYmin);
				Point2D xTop=getPixelFromCordinate((double)gridXPos, currentYmax);
				gc.strokeLine(xBottom.getX(), xBottom.getY(), xTop.getX(), xTop.getY());
				gc.setStroke(Color.LIGHTGRAY);
			}
			else
			{
				Point2D xBottom=getPixelFromCordinate((double)gridXPos, currentYmin);
				Point2D xTop=getPixelFromCordinate((double)gridXPos, currentYmax);
				gc.strokeLine(xBottom.getX(), xBottom.getY(), xTop.getX(), xTop.getY());
			}
			
			if(xLine%xAxisLabelPlacement==0)
			{
				//add label
				Point2D xBottom=getPixelFromCordinate((double)gridXPos, currentYmin);
				hgc.setTextAlign(TextAlignment.CENTER);
				hgc.strokeText(""+gridXPos, xBottom.getX()+30, xBottom.getY()+46, 20);
				hgc.strokeText(""+gridXPos, xBottom.getX()+30, 20, 20);
			}
		}
		
		for(int yLine = minPosY; yLine<=maxPosY; yLine++)
		{
			int gridYPos=yLine*powerY;
			
			if(yLine%10==0)
			{
				gc.setStroke(Color.GRAY);
				Point2D xBottom=getPixelFromCordinate(currentXmin, (double)gridYPos);
				Point2D xTop=getPixelFromCordinate(currentXmax, (double)gridYPos);
				gc.strokeLine(xBottom.getX(), xBottom.getY(), xTop.getX(), xTop.getY());
				gc.setStroke(Color.LIGHTGRAY);
			}
			else
			{
				Point2D xBottom=getPixelFromCordinate(currentXmin, (double)gridYPos);
				Point2D xTop=getPixelFromCordinate(currentXmax, (double)gridYPos);
				gc.strokeLine(xBottom.getX(), xBottom.getY(), xTop.getX(), xTop.getY());
			}
			
			if(yLine%yAxisLabelPlacement==0)
			{
				//add label
				Point2D xBottom=getPixelFromCordinate(currentXmax, (double)gridYPos);
				hgc.setTextAlign(TextAlignment.LEFT);
				
				hgc.strokeText(""+gridYPos, xBottom.getX()+36, xBottom.getY()+34, 20);
				hgc.setTextAlign(TextAlignment.RIGHT);
				hgc.strokeText(""+gridYPos, 20, xBottom.getY()+34, 20);
			}
		}
		
		/*int pos=1;
		while(notDone)
		{
			//positive
			int gridPPos=pos*powerX;
			int gridNPos=0-gridPPos;
			if(gridPPos>currentXmin&&gridPPos<currentXmax)
			{
				if(pos%10==0)
				{
					gc.setStroke(Color.GRAY);
					Point2D xBottom=getPixelFromCordinate((double)gridPPos, currentYmin);
					Point2D xTop=getPixelFromCordinate((double)gridPPos, currentYmax);
					gc.strokeLine(xBottom.getX(), xBottom.getY(), xTop.getX(), xTop.getY());
					gc.setStroke(Color.LIGHTGRAY);
				}
				else
				{
					Point2D xBottom=getPixelFromCordinate((double)gridPPos, currentYmin);
					Point2D xTop=getPixelFromCordinate((double)gridPPos, currentYmax);
					gc.strokeLine(xBottom.getX(), xBottom.getY(), xTop.getX(), xTop.getY());
				}
				//add it to
			}
			else
			{
				//done
				gxpNotDone=false;
			}
			
			if(gridNPos>currentXmin&&gridNPos<currentXmax)
			{
				if(pos%10==0)
				{
					gc.setStroke(Color.GRAY);
					Point2D xBottom=getPixelFromCordinate((double)gridNPos, currentYmin);
					Point2D xTop=getPixelFromCordinate((double)gridNPos, currentYmax);
					gc.strokeLine(xBottom.getX(), xBottom.getY(), xTop.getX(), xTop.getY());
					gc.setStroke(Color.LIGHTGRAY);
				}
				else
				{
					Point2D xBottom=getPixelFromCordinate((double)gridNPos, currentYmin);
					Point2D xTop=getPixelFromCordinate((double)gridNPos, currentYmax);
					gc.strokeLine(xBottom.getX(), xBottom.getY(), xTop.getX(), xTop.getY());
				}
				//add it to
			}
			else
			{
				//done
				gxnNotDone=false;
			}
			
			
			int gridYPPos=pos*powerY;
			int gridYNPos=0-gridYPPos;
			if(gridYPPos>currentYmin&&gridYPPos<currentYmax)
			{
				if(pos%10==0)
				{
					gc.setStroke(Color.GRAY);
					Point2D xBottom=getPixelFromCordinate(currentXmin, (double)gridYPPos);
					Point2D xTop=getPixelFromCordinate(currentXmax, (double)gridYPPos);
					gc.strokeLine(xBottom.getX(), xBottom.getY(), xTop.getX(), xTop.getY());
					gc.setStroke(Color.LIGHTGRAY);
				}
				else
				{
					Point2D xBottom=getPixelFromCordinate(currentXmin, (double)gridYPPos);
					Point2D xTop=getPixelFromCordinate(currentXmax, (double)gridYPPos);
					gc.strokeLine(xBottom.getX(), xBottom.getY(), xTop.getX(), xTop.getY());
				}
				//add it to
			}
			else
			{
				//done
				gypNotDone=false;
			}
			
			if(gridYNPos>currentXmin&&gridYNPos<currentXmax)
			{
				if(pos%10==0)
				{
					gc.setStroke(Color.GRAY);
					Point2D xBottom=getPixelFromCordinate(currentXmin, (double)gridYNPos);
					Point2D xTop=getPixelFromCordinate(currentXmax, (double)gridYNPos);
					gc.strokeLine(xBottom.getX(), xBottom.getY(), xTop.getX(), xTop.getY());
					gc.setStroke(Color.LIGHTGRAY);
				}
				else
				{
					Point2D xBottom=getPixelFromCordinate(currentXmin, (double)gridYNPos);
					Point2D xTop=getPixelFromCordinate(currentXmax, (double)gridYNPos);
					gc.strokeLine(xBottom.getX(), xBottom.getY(), xTop.getX(), xTop.getY());
				}
				//add it to
			}
			else
			{
				//done
				gynNotDone=false;
			}
			
			
			pos++;
			
			if(!gxnNotDone&&!gxpNotDone&&!gynNotDone&&!gypNotDone)
			{
				notDone=false;
			}
		}*/
	}
	
	public void startPaning(double mouseX, double mouseY)
	{
		System.out.println("startedDragging");
		panStartX=mouseX;
		panStartY=mouseY;
		currentSetXmin=currentXmin;
		currentSetXmax=currentXmax;
		currentSetYmin=currentYmin;
		currentSetYmax=currentYmax;
	}
	
	public void dragPaning(double mouseX, double mouseY)
	{
		System.out.println("Dragging");
		
		//update window parameters
		int powerX=(int)Math.pow(10, Math.log10(Math.abs(currentSetXmax-currentSetXmin)));
		double panSpeed=0.003d*powerX;
		double xChange=(mouseX-panStartX)*panSpeed;
		double yChange=(mouseY-panStartY)*panSpeed;
		
		currentXmax=currentSetXmax-(int)xChange;
		currentXmin=currentSetXmin-(int)xChange;
		currentYmax=currentSetYmax+(int)yChange;
		currentYmin=currentSetYmin+(int)yChange;
		
		//update graphics
		updateGraphWindowCoordinates();
	}
	
	public void endPaningDrag()
	{
		currentSetXmin=currentXmin;
		currentSetXmax=currentXmax;
		currentSetYmin=currentYmin;
		currentSetYmax=currentYmax;
		updateGraphWindowCoordinates();
		System.out.println("stopDragging");
		
		//test expression
		/*List<InputNode> testList=new ArrayList<InputNode>();
		
		InputNode two=new InputNode("2", nodeType.NUMBER, 0);
		testList.add(two);
		InputNode mul=new InputNode("*", nodeType.OPERATOR, 0);
		testList.add(mul);
		InputNode x=new InputNode("x", nodeType.NUMBER, 0);
		testList.add(x);
		InputNode ianden=new InputNode("\u00B2", nodeType.OPERATOR, -1);
		testList.add(ianden);
		
		graphDraw.drawGraph(testList, currentXmin, currentXmax, this, x);*/
		theGDraw.updateGraphs();
		
	}
	
	public void zoomScroll(double scroll)
	{
		double zoomFactor=0.001;
		if(scroll>0)
		{
			zoomFactor=1.4d;
		}
		else 
		{
			zoomFactor=0.6d;
		}
		
		currentSetXmin=currentXmin;
		currentSetXmax=currentXmax;
		currentSetYmin=currentYmin;
		currentSetYmax=currentYmax;
		//take the distance between min max and scale it by zoom factor
		
		double oldDistanceX=currentXmax-currentXmin;
		double oldDistanceY=currentYmax-currentYmin;
		double newDistanceX=oldDistanceX*zoomFactor;
		double newDistanceY=oldDistanceY*zoomFactor;
		System.out.println("old x distance"+oldDistanceX+" new distance"+newDistanceX);
		
		//take old distance and substract new distance, and divide result by two
		double diffX=(oldDistanceX-newDistanceX)/2;
		double diffY=(oldDistanceY-newDistanceY)/2;
		//either add or substract amount from current min max sizes.
		currentXmax=currentXmax+(int)diffX;
		currentXmin=currentXmin-(int)diffX;
		currentYmax=currentYmax+(int)diffY;
		currentYmin=currentYmin-(int)diffY;
		
		updateGraphWindowCoordinates();
		theGDraw.updateGraphs();
		
/*List<InputNode> testList=new ArrayList<InputNode>();
		
		InputNode two=new InputNode("2", nodeType.NUMBER, 0);
		testList.add(two);
		InputNode mul=new InputNode("*", nodeType.OPERATOR, 0);
		testList.add(mul);
		InputNode x=new InputNode("x", nodeType.NUMBER, 0);
		testList.add(x);
		InputNode ianden=new InputNode("\u00B2", nodeType.OPERATOR, -1);
		testList.add(ianden);
		
		graphDraw.drawGraph(testList, currentXmin, currentXmax, this, x);*/
	}
}
