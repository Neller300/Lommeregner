package calculatorPackage;

import calculatorPackage.calculatorTools.nodeType;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class ExtButton extends Button{

	public String input;
	public String ShowButtonText;
	public int direction;
	
	private InputHandler theHandler;
	private nodeType itsType;
	
	public ExtButton(String input, String showbuttext, nodeType type, int newDirection, InputHandler theInputHandler) 
	{
	
	super(showbuttext);
	theHandler=theInputHandler;
	this.input = input;
	ShowButtonText = showbuttext;
	direction=newDirection;
	itsType=type;
	
	
		super.setPrefSize(100,90);
		super.setStyle("-fx-font: 25 Helvitica; -fx-font-weight: bold;");
		super.setDefaultButton(isPressed());
		
		DropShadow shadow = new DropShadow();
		shadow.setOffsetX(10);
		shadow.setOffsetY(10);
	
		
		super.setOnMouseClicked((new EventHandler<MouseEvent>() { 
			 public void handle(MouseEvent event) { 
				 	theInputHandler.handleInput(type, input, direction);
		         } 
		      }));
}
	
	public void activate()
	{
		theHandler.handleInput(itsType, input, direction);
	}
}

