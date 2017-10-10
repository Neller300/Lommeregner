import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class MinMaxFrame extends Application {
	
	@Override
	public void start(Stage stage) {
		TextField input1 = new TextField();
		TextField input2 = new TextField();
		Button minKnap = new Button("Min");
		Button maxKnap = new Button("Max");
		TextField output = new TextField();
		
		HBox layout = new HBox();
		layout.setPadding(new Insets (8, 8, 8, 8));
		layout.setSpacing(8);
		
		layout.getChildren().add(input1);
		layout.getChildren().add(input2);
		layout.getChildren().add(minKnap);
		layout.getChildren().add(maxKnap);
		layout.getChildren().add(output);
		output.setEditable(false);

		minKnap.setOnAction(new EventHandler<ActionEvent>() { 
			@Override
			public void handle(ActionEvent e) {
				try {
					output.setText("" + Math.min(getValue(input1), getValue(input2)));
				}
				catch (NumberFormatException ex) {
					output.setText("Fejl et eller andet");
				}
			}
		});
		
		maxKnap.setOnAction(new EventHandler<ActionEvent>() { 
			@Override
			public void handle(ActionEvent e) {
				
				int første = Integer.parseInt(input1.getText());
				int anden = Integer.parseInt(input2.getText());
				
				if (første > anden) 
					output.setText("" + første);
				else 
					output.setText("" + anden);
				
				
		}
	});
		Scene scene = new Scene(layout);
		stage.setScene(scene);
		stage.setTitle("MinMaxFrame");
		stage.show();
		
	}

	private int getValue(TextField felt) {
		return Integer.parseInt(felt.getText());
	}
	
	
	
	
	
	
	public static void main(String[] args) {
		launch(args);

	}

}
