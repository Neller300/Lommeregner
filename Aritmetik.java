package Aritmetik;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Aritmetik extends Application {

	@Override
	public void start(Stage stage) {

		Label label1 = new Label("1. argument:");
		Label label2 = new Label("2. argument:");
		Label label3 = new Label("=");
		TextField input1 = new TextField();
		TextField input2 = new TextField();
		Button plusKnap = new Button(" + ");
		Button minusKnap = new Button(" - ");
		Button gangeKnap = new Button(" * ");
		Button dividerKnap = new Button(" / ");
		TextField output = new TextField();
		output.setEditable(false);
		
		HBox layout = new HBox();
		layout.setPadding(new Insets(8, 8, 8, 8)); 
		layout.setSpacing(8);
		
		layout.getChildren().add(label1);
		layout.getChildren().add(input1);
		layout.getChildren().add(label2);
		layout.getChildren().add(input2);
		layout.getChildren().add(plusKnap);
		layout.getChildren().add(minusKnap);
		layout.getChildren().add(gangeKnap);
		layout.getChildren().add(dividerKnap);
		layout.getChildren().add(label3);
		layout.getChildren().add(output);
		
		
		plusKnap.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				
				int første = Integer.parseInt(input1.getText());
				int anden = Integer.parseInt(input2.getText());
				
				output.setText("" + (første + anden));
				
			}
		});
		minusKnap.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
						
				int første = Integer.parseInt(input1.getText());
				int anden = Integer.parseInt(input2.getText());
						
				output.setText("" + (første - anden));
				
			}
		});
		
		gangeKnap.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
								
				int første = Integer.parseInt(input1.getText());
				int anden = Integer.parseInt(input2.getText());
								
				output.setText("" + (første * anden));
		
			}
		});
				
		dividerKnap.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
								
				int første = Integer.parseInt(input1.getText());
				int anden = Integer.parseInt(input2.getText());
								
				output.setText("" + (første / anden));
		
				
				
			}
		});
		
		Scene scene = new Scene(layout);
		stage.setScene(scene);
		stage.setTitle("Aritmetik");
		stage.show();

	}
	public static void main(String[] args) {
		launch(args);
}
}
		