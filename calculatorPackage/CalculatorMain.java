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
			//Group abRoot = new Group();
			
			
			// create input Handler
			InputHandler theIHandler = new InputHandler();

			// create the visuals
			GUICreator GUI = new GUICreator(theIHandler);
			primaryStage.setScene(GUI.getScene());
			primaryStage.show();
			
			Image anotherImage = new Image("file:images.png");
					primaryStage.getIcons().add(anotherImage);
			
			//primaryStage.setScene(scene);
			//scene.getStylesheets().add(mainStarter.class.getResource("styleSheetTest.css").toExternalForm());
			//primaryStage.show();
			
			
		}}


