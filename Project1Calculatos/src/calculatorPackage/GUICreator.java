package calculatorPackage;

import calculatorPackage.calculatorTools.nodeType;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class GUICreator
{
	public Text text;

	public Scene theScene;
	
	public DisplayHandler theDisplay;

	public GUICreator(InputHandler tHandler)
	{

		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(15, 15, 15, 15));

		StackPane stack = new StackPane();
		stack.getChildren().addAll(new Rectangle(430, 165, Color.BLACK));
		text = new Text();
		text.setFill(Color.WHITE);
		text.setStyle("-fx-font: 25 Consolas; -fx-font-weight: bold;");
		HBox textHolder = new HBox();
		textHolder.setAlignment(Pos.TOP_LEFT);
		
	
		textHolder.getChildren().add(text);
		stack.getChildren().add(textHolder);
		grid.add(stack, 0, 0, 4, 1);

		DisplayHandler.theGUICreator=this;
		
		
		theScene = new Scene(grid, 460, 795);

		ExtButton nul = new ExtButton("0", "0", nodeType.NUMBER, 0, tHandler);
		grid.add(nul, 1, 6);

		ExtButton et = new ExtButton("1", "1", nodeType.NUMBER, 0, tHandler);
		grid.add(et, 0, 5);

		ExtButton to = new ExtButton("2", "2", nodeType.NUMBER, 0, tHandler);
		grid.add(to, 1, 5);

		ExtButton tre = new ExtButton("3", "3", nodeType.NUMBER, 0, tHandler);
		grid.add(tre, 2, 5);

		ExtButton fire = new ExtButton("4", "4", nodeType.NUMBER, 0, tHandler);
		grid.add(fire, 0, 4);

		ExtButton fem = new ExtButton("5", "5", nodeType.NUMBER, 0, tHandler);
		grid.add(fem, 1, 4);

		ExtButton seks = new ExtButton("6", "6", nodeType.NUMBER, 0, tHandler);
		grid.add(seks, 2, 4);

		ExtButton syv = new ExtButton("7", "7", nodeType.NUMBER, 0, tHandler);
		grid.add(syv, 0, 3);

		ExtButton otte = new ExtButton("8", "8", nodeType.NUMBER, 0, tHandler);
		grid.add(otte, 1, 3);

		ExtButton ni = new ExtButton("9", "9", nodeType.NUMBER, 0, tHandler);
		grid.add(ni, 2, 3);

		ExtButton punktum = new ExtButton(".", ".", nodeType.NUMBER, 0, tHandler);
		grid.add(punktum, 2, 6);

		ExtButton ligmed = new ExtButton("=", "=", nodeType.UTILITY, 0, tHandler);
		grid.add(ligmed, 3, 6);

		ExtButton plus = new ExtButton("+", "+", nodeType.OPERATOR, 0, tHandler);
		grid.add(plus, 3, 5);

		ExtButton minus = new ExtButton("-", "-", nodeType.OPERATOR, 0, tHandler);
		grid.add(minus, 3, 4);

		ExtButton gange = new ExtButton("*", "*", nodeType.OPERATOR, 0, tHandler);
		grid.add(gange, 3, 3);

		ExtButton divider = new ExtButton("/", "/", nodeType.OPERATOR, 0, tHandler);
		grid.add(divider, 3, 2);

		ExtButton parentesStart = new ExtButton("(", "(", nodeType.UTILITY, 0, tHandler);
		grid.add(parentesStart, 2, 1);

		ExtButton parentesSlut = new ExtButton(")", ")", nodeType.UTILITY, 0, tHandler);
		grid.add(parentesSlut, 3, 1);

		ExtButton kvadratrod = new ExtButton("\u221A", "\u221A", nodeType.OPERATOR, 1, tHandler);
		grid.add(kvadratrod, 2, 2);

		ExtButton IAnden = new ExtButton("\u00B2", "x\u00B2", nodeType.OPERATOR, -1, tHandler);
		grid.add(IAnden, 1, 1);

		ExtButton Clear = new ExtButton("CE", "CE", nodeType.UTILITY, 0, tHandler);
		grid.add(Clear, 0, 2);

		ExtButton delete = new ExtButton("\u232B", "\u232B", nodeType.UTILITY, 0, tHandler);
		grid.add(delete, 1, 2);

		ExtButton ChangeSign = new ExtButton("(-) +-", "(-) +-", nodeType.NUMBER, 0, tHandler);
		grid.add(ChangeSign, 0, 6);

		ExtButton Percent = new ExtButton("%", "%", nodeType.UTILITY, 0, tHandler);
		grid.add(Percent, 0, 1);

	}

	public Scene getScene()
	{
		return theScene;
	}
}
