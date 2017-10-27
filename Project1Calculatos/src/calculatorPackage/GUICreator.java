package calculatorPackage;

import java.util.HashMap;

import calculatorPackage.calculatorTools.nodeType;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
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
		Rectangle leBox = new Rectangle(430, 165, Color.BLACK);
		leBox.setArcHeight(8);
		leBox.setArcWidth(8);
		stack.getChildren().addAll(leBox);
		
		text = new Text();
		text.setFill(Color.WHITE);
		text.setStyle("-fx-font: 25 Consolas; -fx-font-weight: bold;");
		text.setWrappingWidth(430);
		
		
		HBox textHolder = new HBox();
		textHolder.setAlignment(Pos.TOP_LEFT);
		textHolder.getChildren().add(text);
		stack.getChildren().add(textHolder);
		grid.add(stack, 0, 0, 4, 1);

		DisplayHandler.theGUICreator=this;
		
		theScene = new Scene(grid, 460, 840);
		
	
		ExtButton leftParenthesis = new ExtButton("(", "(", nodeType.UTILITY, 0, tHandler);
		grid.add(leftParenthesis, 0, 1);
		
		ExtButton Clear = new ExtButton("CE", "CE", nodeType.UTILITY, 0, tHandler);
		grid.add(Clear, 0, 2);
		
		ExtButton Seven = new ExtButton("7", "7", nodeType.NUMBER, 0, tHandler);
		grid.add(Seven, 0, 3);
		
		ExtButton Four = new ExtButton("4", "4", nodeType.NUMBER, 0, tHandler);
		grid.add(Four, 0, 4);
		
		ExtButton One = new ExtButton("1", "1", nodeType.NUMBER, 0, tHandler);
		grid.add(One, 0, 5);
		
		ExtButton ChangeSign = new ExtButton("-", "±", nodeType.NUMBER, 0, tHandler);
		grid.add(ChangeSign, 0, 6);
		
		ExtButton xButton = new ExtButton("X", "X", nodeType.NUMBER, 0, tHandler);
		grid.add(xButton, 0, 7);
		
		ExtButton openGraph = new ExtButton("GRAPH", "GRAPH", nodeType.UTILITY, 0, tHandler);
		grid.add(openGraph, 0, 8);
		openGraph.setStyle("-fx-font: 20 Helvitica; -fx-font-weight: bold;");

		ExtButton rightParenthesis = new ExtButton(")", ")", nodeType.UTILITY, 0, tHandler);
		grid.add(rightParenthesis, 1, 1);
		
		ExtButton Delete = new ExtButton("\u232B", "\u232B", nodeType.UTILITY, 0, tHandler);
		grid.add(Delete, 1, 2);

		ExtButton Eight = new ExtButton("8", "8", nodeType.NUMBER, 0, tHandler);
		grid.add(Eight, 1, 3);
		
		ExtButton Five = new ExtButton("5", "5", nodeType.NUMBER, 0, tHandler);
		grid.add(Five, 1, 4);

		ExtButton Two = new ExtButton("2", "2", nodeType.NUMBER, 0, tHandler);
		grid.add(Two, 1, 5);

		ExtButton zero = new ExtButton("0", "0", nodeType.NUMBER, 0, tHandler);
		grid.add(zero, 1, 6);
		
		ExtButton sinus = new ExtButton("Sin", "Sin", nodeType.OPERATOR, 1, tHandler);
		grid.add(sinus, 1, 7);
		
		ExtButton InvSin = new ExtButton("Sin⁻¹", "Sin⁻¹", nodeType.OPERATOR, 1, tHandler);
		grid.add(InvSin, 1, 8);

		ExtButton Square = new ExtButton("\u00B2", "x²", nodeType.OPERATOR, -1, tHandler);
		grid.add(Square, 2, 1);

		ExtButton sqrt = new ExtButton("\u221A", "\u221A", nodeType.OPERATOR, 1, tHandler);
		grid.add(sqrt, 2, 2);

		ExtButton Nine = new ExtButton("9", "9", nodeType.NUMBER, 0, tHandler);
		grid.add(Nine, 2, 3);

		ExtButton Six = new ExtButton("6", "6", nodeType.NUMBER, 0, tHandler);
		grid.add(Six, 2, 4);
		
		ExtButton Three = new ExtButton("3", "3", nodeType.NUMBER, 0, tHandler);
		grid.add(Three, 2, 5);

		ExtButton Period = new ExtButton(".", ".", nodeType.NUMBER, 0, tHandler);
		grid.add(Period, 2, 6);
		
		ExtButton cos = new ExtButton("Cos", "Cos", nodeType.OPERATOR, 1, tHandler);
		grid.add(cos, 2, 7);
		
		ExtButton InvCos = new ExtButton("Cos⁻¹", "Cos⁻¹", nodeType.OPERATOR, 1, tHandler);
		grid.add(InvCos, 2, 8);
		
		ExtButton Exponent = new ExtButton("^", "^", nodeType.OPERATOR, 0, tHandler);
		grid.add(Exponent, 3, 1);

		ExtButton Divide = new ExtButton("/", "/", nodeType.OPERATOR, 0, tHandler);
		grid.add(Divide, 3, 2);

		ExtButton Multiply = new ExtButton("*", "*", nodeType.OPERATOR, 0, tHandler);
		grid.add(Multiply, 3, 3);

		ExtButton Subtract = new ExtButton("―", "-", nodeType.OPERATOR, 0, tHandler);
		grid.add(Subtract, 3, 4);

		ExtButton Add = new ExtButton("+", "+", nodeType.OPERATOR, 0, tHandler);
		grid.add(Add, 3, 5);

		ExtButton Equal = new ExtButton("=", "=", nodeType.UTILITY, 0, tHandler);
		grid.add(Equal, 3, 6);
		
		ExtButton tan = new ExtButton("Tan", "Tan", nodeType.OPERATOR, 1, tHandler);
		grid.add(tan, 3, 7);
		
		ExtButton InvTan = new ExtButton("Tan⁻¹", "Tan⁻¹", nodeType.OPERATOR, 1, tHandler);
		grid.add(InvTan, 3, 8);

		
		theScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				
				HashMap<KeyCode, ExtButton> map = new HashMap<KeyCode, ExtButton>();
				map.put(KeyCode.DIGIT0, zero);
				map.put(KeyCode.NUMPAD0, zero);
				map.put(KeyCode.DIGIT1, One);
				map.put(KeyCode.NUMPAD1, One);
				map.put(KeyCode.DIGIT2, Two);
				map.put(KeyCode.NUMPAD2, Two);
				map.put(KeyCode.DIGIT3, Three);
				map.put(KeyCode.NUMPAD3, Three);
				map.put(KeyCode.DIGIT4, Four);
				map.put(KeyCode.NUMPAD4, Four);
				map.put(KeyCode.DIGIT5, Five);
				map.put(KeyCode.NUMPAD5, Five);
				map.put(KeyCode.DIGIT6, Six);
				map.put(KeyCode.NUMPAD6, Six);
				map.put(KeyCode.DIGIT7, Seven);
				map.put(KeyCode.NUMPAD7, Seven);
				map.put(KeyCode.DIGIT8, Eight);
				map.put(KeyCode.NUMPAD8, Eight);
				map.put(KeyCode.DIGIT9, Nine);
				map.put(KeyCode.NUMPAD9, Nine);
				map.put(KeyCode.PLUS, Add);
				map.put(KeyCode.ADD, Add);
				map.put(KeyCode.MINUS, Subtract);
				map.put(KeyCode.SUBTRACT, Subtract);
				map.put(KeyCode.MULTIPLY, Multiply);
				map.put(KeyCode.QUOTE, Multiply);
				map.put(KeyCode.DIVIDE, Divide);
				map.put(KeyCode.ENTER, Equal);
				map.put(KeyCode.BACK_SPACE, Delete);
				map.put(KeyCode.ESCAPE, Clear);
				map.put(KeyCode.PERIOD, Period);
				map.put(KeyCode.DECIMAL, Period);
				map.put(KeyCode.I, leftParenthesis);
				map.put(KeyCode.O, rightParenthesis);
				
				KeyCode keyCode = event.getCode();
				
				System.out.println("key pressed: " + keyCode);
				
				if (map.containsKey(keyCode)) {
					System.out.println("pressed key from hashmap");
					ExtButton button = map.get(keyCode);
					
					button.activate();
					button.requestFocus();
				}
			}});
	}

	public Scene getScene()
	{
		return theScene;
	}
}
