package graphPackage;

import java.util.HashMap;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GraphGUI
{
	public Canvas graphCanvas;
	public Canvas axisDescCanvas;
	
	public static HashMap<String, Label> functionMenuDic=new HashMap<String, Label>();
	
	public Group createGUI(double canvasSize)
	{
		Group root=new Group();
        BorderPane bPane = new BorderPane();
        root.getChildren().add(bPane);
        StackPane graphWindow=new StackPane();
        
        bPane.setLeft(graphWindow);
        bPane.setMargin(graphWindow, new Insets(0, 0, 0, 10));
        
        Rectangle background= new Rectangle(580, 580, Color.BEIGE);
        graphCanvas = new Canvas(canvasSize, canvasSize);
        axisDescCanvas = new Canvas(canvasSize+60, canvasSize+60);
        
        
        
        background.setStroke(Color.GRAY);
        background.setArcHeight(5);
        background.setArcWidth(5);
        
        graphWindow.getChildren().add(background);
        graphWindow.getChildren().add(axisDescCanvas);
        graphWindow.getChildren().add(graphCanvas);
        
        VBox functionMenuHolder= new VBox(10);
        bPane.setBottom(functionMenuHolder);
        bPane.setMargin(functionMenuHolder, new Insets(10, 10, 10, 10));
        
        //create a function menu
        HBox functionMenu1 = createFunctionMenu("Y1");
        functionMenuHolder.getChildren().add(functionMenu1);
        
        HBox functionMenu2 = createFunctionMenu("Y2");
        functionMenuHolder.getChildren().add(functionMenu2);
        
        HBox functionMenu3 = createFunctionMenu("Y3");
        functionMenuHolder.getChildren().add(functionMenu3);
        
        
        //right part
        GridPane windowControlGrid = new GridPane();
        bPane.setRight(windowControlGrid);
        bPane.setMargin(windowControlGrid, new Insets(10, 10, 10, 10));
        
        windowControlGrid.setHgap(6);
        windowControlGrid.setVgap(12);
        
        Label headerLabel= new Label("Window Controls");
        
        windowControlGrid.add(headerLabel, 0, 0, 2, 1);
        windowControlGrid.setHalignment(headerLabel, HPos.CENTER);
        
        Label yMaxLabel=new Label("Ymax : ");
        windowControlGrid.add(yMaxLabel, 0, 1);
        
        Label yMinLabel=new Label("Ymin : ");
        windowControlGrid.add(yMinLabel, 0, 2);
        
        Label xMaxLabel=new Label("Xmax : ");
        windowControlGrid.add(xMaxLabel, 0, 3);
        
        Label xMinLabel=new Label("Xmin : ");
        windowControlGrid.add(xMinLabel, 0, 4);
        
        Label calcRes=new Label("Resolution : ");
        windowControlGrid.add(calcRes, 0, 5);
        
        TextField yMaxField = new TextField();
        windowControlGrid.add(yMaxField, 1, 1);
        yMaxField.setPrefSize(60, 22);
        
        TextField yMinField = new TextField();
        windowControlGrid.add(yMinField, 1, 2);
        yMinField.setPrefSize(60, 22);
        
        TextField xMaxField = new TextField();
        windowControlGrid.add(xMaxField, 1, 3);
        xMaxField.setPrefSize(60, 22);
        
        TextField xMinField = new TextField();
        windowControlGrid.add(xMinField, 1, 4);
        xMinField.setPrefSize(60, 22);
        
        TextField calcResField = new TextField();
        windowControlGrid.add(calcResField, 1, 5);
        calcResField.setPrefSize(60, 22);
        
        return root;
	}
	
	public HBox createFunctionMenu(String functionMenuCallSign)
	{
		 HBox functionMenu1 = new HBox(8);
	        functionMenu1.setAlignment(Pos.CENTER_LEFT);
	        Label expressionName = new Label(""+functionMenuCallSign+" =");
	        expressionName.setStyle("-fx-font: 20 Consolas; -fx-font-weight: bold;");
	        functionMenu1.getChildren().add(expressionName);
	        //create inputfieldSpecial
	        StackPane expressionInputSPane=new StackPane();
	        Rectangle expressionBackdrop= new Rectangle(320, 36, Color.ALICEBLUE);
	        expressionBackdrop.setArcHeight(6);
	        expressionBackdrop.setArcWidth(6);
	        expressionBackdrop.setStroke(Color.GREY);
	        Label expressionInputLabel = new Label("click and insert expression");
		       expressionInputLabel.setStyle("-fx-font: 20 Consolas; -fx-font-weight: bold;");
		       expressionInputLabel.setAlignment(Pos.CENTER_LEFT);
	        expressionBackdrop.setOnMouseClicked(new EventHandler<MouseEvent>() 
	        {
	            @Override
	            public void handle(MouseEvent event) 
	            {
	            expressionFieldClicked(functionMenuCallSign, expressionBackdrop, expressionInputLabel);
	            }
	        });
	        
	        //input label
	   
	      // expressionInputLabel.setTextFill(arg0);
	        
	       expressionInputLabel.setOnMouseClicked(new EventHandler<MouseEvent>() 
	        {
	            @Override
	            public void handle(MouseEvent event) 
	            {
	            expressionFieldClicked(functionMenuCallSign, expressionBackdrop, expressionInputLabel);
	            }
	        });
	       
	        expressionInputSPane.getChildren().add(expressionBackdrop);
	        expressionInputSPane.getChildren().add(expressionInputLabel);
	        expressionInputSPane.setAlignment(expressionInputLabel, Pos.CENTER_LEFT);
	        functionMenu1.getChildren().add(expressionInputSPane);
	        
	        Button drawButton=new Button("DRAW");
	        drawButton.setOnAction(new EventHandler<ActionEvent>() {
	            @Override public void handle(ActionEvent e) {
	                drawnButtonClicked(functionMenuCallSign);
	            }
	        });
	        drawButton.setPrefSize(62, 36);
	        drawButton.setStyle("-fx-font: 16 Consolas; -fx-font-weight: bold;");
	        
	        functionMenu1.getChildren().add(drawButton);
	        
	        functionMenuDic.put(functionMenuCallSign, expressionInputLabel);
	        
		return functionMenu1;
	}
	
	public void drawnButtonClicked(String caller)
	{
		//test code
		//graphDraw.drawGraph(testList, currentXmin, currentXmax, this, x);
		if(caller=="Y1")
		{
			GraphWindowHandler.theInputHandler.graph.theGDraw.createNewGraph(caller, GraphWindowHandler.theInputHandler.graph1Input);
		}
		else if(caller=="Y2")
		{
			GraphWindowHandler.theInputHandler.graph.theGDraw.createNewGraph(caller, GraphWindowHandler.theInputHandler.graph2Input);
		}
		else if(caller=="Y3")
		{
			GraphWindowHandler.theInputHandler.graph.theGDraw.createNewGraph(caller, GraphWindowHandler.theInputHandler.graph3Input);
		}
	}
	
	public void expressionFieldClicked(String caller, Rectangle itsRectangle, Label itsLabel)
	{
		
		System.out.println(""+caller);
		
		//set color
		itsRectangle.setStroke(Color.DARKRED);
		
		//set inputhandlers trackedinput to this
		GraphWindowHandler.theInputHandler.setToGraphFieldInput(caller, itsLabel);
		//
	}
}
