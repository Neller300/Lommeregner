package calculatorPackage;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class CalculatorMain extends Application
{
	public static void main(String[] args)
	{
		launch(args);
	}

	@Override
	public void start(Stage primaryStage)
	{
		primaryStage.setTitle("Pro-1-Calculator");

		// create input Handler
		InputHandler theIHandler = new InputHandler();

		// create the visuals
		GUICreator GUI = new GUICreator(theIHandler);
		primaryStage.setScene(GUI.getScene());

		try
		{
			Image anotherImage = new Image("file:src/images.png");
			primaryStage.getIcons().add(anotherImage);
		}
		catch (Exception e)
		{
			System.out.println(e);
		}

		primaryStage.show();
	}
}
