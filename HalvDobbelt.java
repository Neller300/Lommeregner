import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class HalvDobbelt extends Application {
	
	@Override
	public void start(Stage stage) { 
		TextField input = new TextField();
		TextField output = new TextField();
		Button halvKnap = new Button(" Halv ");
		Button dobbeltKnap = new Button(" Dobbelt ");
		
		HBox layout = new HBox();
		layout.setPadding(new Insets (8, 8, 8, 8));
		layout.setSpacing(8);
		
		layout.getChildren().add(input);
		layout.getChildren().add(halvKnap);
		layout.getChildren().add(dobbeltKnap);
		layout.getChildren().add(output);
		output.setEditable(false);
		
		
		halvKnap.setOnAction(new EventHandler<ActionEvent>() { 
			@Override
			public void handle(ActionEvent e) {
				
				int tal = Integer.parseInt(input.getText());
				output.setText(" " + (tal / 2));
				
		}
	});
		dobbeltKnap.setOnAction(new EventHandler<ActionEvent>() { 
			@Override
			public void handle(ActionEvent e) {
				
				int tal = Integer.parseInt(input.getText());
				output.setText(" " + (tal * 2));
		
		}
	});
		
		Scene scene = new Scene(layout);
		stage.setScene(scene);
		stage.setTitle("HalvDobbelt");
		stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);

	}

}
