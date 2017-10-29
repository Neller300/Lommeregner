package calculatorPackage;

import calculatorPackage.calculatorTools.nodeType;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class ExtButton extends Button
{

	public String input;
	public String ShowButtonText;
	public int direction;

	private InputHandler theHandler;
	private nodeType itsType;

	public ExtButton(String input, String showButText, nodeType type, int newDirection, InputHandler theInputHandler)
	{
		//set member Data
		super(showButText);
		theHandler = theInputHandler;
		this.input = input;
		ShowButtonText = showButText;
		direction = newDirection;
		itsType = type;
		
		//set button design and style
		super.setPrefSize(100, 90);
		super.setStyle("-fx-font: 25 Helvitica; -fx-font-weight: bold;");
		super.setDefaultButton(isPressed());
		DropShadow shadow = new DropShadow();
		shadow.setOffsetX(1);
		shadow.setOffsetY(1);
		shadow.setRadius(3);
		super.setEffect(shadow);

		//set button clicked Function
		super.setOnMouseClicked((new EventHandler<MouseEvent>()
		{
			public void handle(MouseEvent event)
			{
				activate();
			}
		}));
	}

	public void activate()
	{
		theHandler.handleInput(itsType, input, direction);
	}
}
