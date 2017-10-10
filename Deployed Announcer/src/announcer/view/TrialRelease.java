package announcer.view;

import announcer.logic.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class TrialRelease extends Application {

	@Override
	public void start(Stage stage) {
		stage.setTitle("Welcome");
		HBox layout = new HBox();
		layout.setPadding(new Insets(8, 8, 8, 8));
		layout.setSpacing(8);

		Button magic = new Button("Press me!");
		TextField output = new TextField();
		output.setEditable(false);

		layout.getChildren().add(magic);
		layout.getChildren().add(output);

		Announcer announcer = new AnnouncerBest();
		magic.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {

				output.setText(announcer.getMessage());

			}
		});

		Scene scene = new Scene(layout);
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
