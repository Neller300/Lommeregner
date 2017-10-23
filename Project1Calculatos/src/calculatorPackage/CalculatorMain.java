package calculatorPackage;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import graphPackage.GraphWindowHandler;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
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
			
			//primaryStage.setScene(scene);
			//scene.getStylesheets().add(mainStarter.class.getResource("styleSheetTest.css").toExternalForm());
			//primaryStage.show();
			
			
	}

}
