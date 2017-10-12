package calculatorPackage;

import calculatorPackage.calculatorTools.nodeType;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class ExtButton extends Button{

	public String input;
	public String ShowButtonText;
	public int direction;
	
	public ExtButton(String input, String showbuttext, nodeType type, int newDirection, InputHandler theInputHandler) 
	{
	
	super(showbuttext);
	this.input = input;
	ShowButtonText = showbuttext;
	direction=newDirection;
	
	
		super.setPrefSize(100,90);
		//super.setStyle("-fx-background-color: ; ");
		//super.setFont(helvitica);
		super.setStyle("-fx-font: 25 Helvitica; -fx-font-weight: bold;");
		super.setHover(isScaleShape());
		super.setDefaultButton(isPressed());
		//super.setTextFill(value);
		//super
		
		
		
		
		super.setOnMouseClicked((new EventHandler<MouseEvent>() { 
	         public void handle(MouseEvent event) { 
		            theInputHandler.handleInput();
		         } 
		      }));
}
}

