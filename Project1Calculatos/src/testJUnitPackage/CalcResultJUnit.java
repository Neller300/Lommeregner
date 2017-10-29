package testJUnitPackage;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import calculatorPackage.CalcResult;
import calculatorPackage.InputHandler;
import calculatorPackage.InputNode;
import calculatorPackage.calculatorTools.nodeType;

public class CalcResultJUnit
{
	@Test
	public void testSingleRight()
	{
		InputHandler theHandler = new InputHandler();

		List<InputNode> testExpression = new ArrayList<InputNode>();
		// create nodes
		InputNode rootSign = new InputNode("\u221A", nodeType.OPERATOR, 1);
		testExpression.add(rootSign);

		InputNode number = new InputNode("9", nodeType.NUMBER, 0);
		testExpression.add(number);

		String result = CalcResult.CalcFinalResult(theHandler.prepareListForCalc(testExpression));
		System.out.println(result);
		assertEquals(result, "3");
	}
	
	@Test
	public void testSingleLeft()
	{
		InputHandler theHandler = new InputHandler();

		List<InputNode> testExpression = new ArrayList<InputNode>();
		// create nodes
		InputNode number = new InputNode("2", nodeType.NUMBER, 0);
		testExpression.add(number);
		
		InputNode squaredSign = new InputNode("\u00B2", nodeType.OPERATOR, -1);
		testExpression.add(squaredSign);

		String result = CalcResult.CalcFinalResult(theHandler.prepareListForCalc(testExpression));
		System.out.println(result);
		assertEquals(result, "4");
	}
	
	@Test
	public void testSingleMiddle()
	{
		InputHandler theHandler = new InputHandler();

		List<InputNode> testExpression = new ArrayList<InputNode>();
		// create nodes
		InputNode number1 = new InputNode("2", nodeType.NUMBER, 0);
		testExpression.add(number1);
		
		InputNode plusSign = new InputNode("+", nodeType.OPERATOR, 0);
		testExpression.add(plusSign);

		InputNode number2 = new InputNode("2", nodeType.NUMBER, 0);
		testExpression.add(number2);
		
		String result = CalcResult.CalcFinalResult(theHandler.prepareListForCalc(testExpression));
		System.out.println(result);
		assertEquals(result, "4");
	}
	
	@Test
	public void testSimpleParenthesis()
	{
		InputHandler theHandler = new InputHandler();

		List<InputNode> testExpression = new ArrayList<InputNode>();
		// create nodes
		InputNode number1 = new InputNode("2", nodeType.NUMBER, 0);
		testExpression.add(number1);
		
		InputNode mulSign = new InputNode("*", nodeType.OPERATOR, 0);
		testExpression.add(mulSign);

		InputNode leftParenthesis = new InputNode("(", nodeType.PARENTHESIS, 0);
		testExpression.add(leftParenthesis);
		
		InputNode number2 = new InputNode("3", nodeType.NUMBER, 0);
		testExpression.add(number2);
		
		InputNode plusSign = new InputNode("+", nodeType.OPERATOR, 0);
		testExpression.add(plusSign);
		
		InputNode number3 = new InputNode("3", nodeType.NUMBER, 0);
		testExpression.add(number3);
		
		InputNode rightParenthesis = new InputNode(")", nodeType.PARENTHESIS, 0);
		testExpression.add(rightParenthesis);
		
		String result = CalcResult.CalcFinalResult(theHandler.prepareListForCalc(testExpression));
		System.out.println(result);
		assertEquals(result, "12");
	}
	
	@Test
	public void testLeftAndRight()
	{
		InputHandler theHandler = new InputHandler();

		List<InputNode> testExpression = new ArrayList<InputNode>();
		// create nodes
		InputNode number1 = new InputNode("2", nodeType.NUMBER, 0);
		testExpression.add(number1);
		
		InputNode squaredSign = new InputNode("\u00B2", nodeType.OPERATOR, -1);
		testExpression.add(squaredSign);

		InputNode mulSign = new InputNode("*", nodeType.OPERATOR, 0);
		testExpression.add(mulSign);
		
		InputNode rootSign = new InputNode("\u221A", nodeType.OPERATOR, 1);
		testExpression.add(rootSign);
		
		InputNode number3 = new InputNode("9", nodeType.NUMBER, 0);
		testExpression.add(number3);
		
		String result = CalcResult.CalcFinalResult(theHandler.prepareListForCalc(testExpression));
		System.out.println(result);
		assertEquals(result, "12");
	}
	
	@Test
	public void testPrecedence()
	{
		InputHandler theHandler = new InputHandler();

		List<InputNode> testExpression = new ArrayList<InputNode>();
		// create nodes
		InputNode number1 = new InputNode("3", nodeType.NUMBER, 0);
		testExpression.add(number1);
		
		InputNode subSign = new InputNode("-", nodeType.OPERATOR, 0);
		testExpression.add(subSign);
		
		InputNode number2 = new InputNode("2", nodeType.NUMBER, 0);
		testExpression.add(number2);

		InputNode mulSign = new InputNode("*", nodeType.OPERATOR, 0);
		testExpression.add(mulSign);
		
		InputNode number3 = new InputNode("4", nodeType.NUMBER, 0);
		testExpression.add(number3);
		
		String result = CalcResult.CalcFinalResult(theHandler.prepareListForCalc(testExpression));
		System.out.println(result);
		assertEquals(result, "-5");
	}
	
	@Test
	public void testSingleMiddleDecimal()
	{
		InputHandler theHandler = new InputHandler();

		List<InputNode> testExpression = new ArrayList<InputNode>();
		// create nodes
		InputNode number1 = new InputNode("2.5", nodeType.NUMBER, 0);
		testExpression.add(number1);
		
		InputNode divSign = new InputNode("/", nodeType.OPERATOR, 0);
		testExpression.add(divSign);

		InputNode number2 = new InputNode("2", nodeType.NUMBER, 0);
		testExpression.add(number2);
		
		String result = CalcResult.CalcFinalResult(theHandler.prepareListForCalc(testExpression));
		System.out.println(result);
		assertEquals(result, "1.25");
	}
	
	@Test
	public void testAdvancedExpression()
	{
		InputHandler theHandler = new InputHandler();

		List<InputNode> testExpression = new ArrayList<InputNode>();
		// create nodes
		InputNode leftParenthesis1 = new InputNode("(", nodeType.PARENTHESIS, 0);
		testExpression.add(leftParenthesis1);
		
		InputNode leftParenthesis2 = new InputNode("(", nodeType.PARENTHESIS, 0);
		testExpression.add(leftParenthesis2);
		
		InputNode number1 = new InputNode("2", nodeType.NUMBER, 0);
		testExpression.add(number1);
		
		InputNode mulSign = new InputNode("*", nodeType.OPERATOR, 0);
		testExpression.add(mulSign);

		InputNode number2 = new InputNode("3", nodeType.NUMBER, 0);
		testExpression.add(number2);
		
		InputNode rightParenthesis1 = new InputNode(")", nodeType.PARENTHESIS, 0);
		testExpression.add(rightParenthesis1);
		
		InputNode mulSign2 = new InputNode("*", nodeType.OPERATOR, 0);
		testExpression.add(mulSign2);
		
		InputNode leftParenthesis3 = new InputNode("(", nodeType.PARENTHESIS, 0);
		testExpression.add(leftParenthesis3);
		
		InputNode number3 = new InputNode("4", nodeType.NUMBER, 0);
		testExpression.add(number3);
		
		InputNode subSign = new InputNode("-", nodeType.OPERATOR, 0);
		testExpression.add(subSign);
		
		InputNode number4 = new InputNode("0.5", nodeType.NUMBER, 0);
		testExpression.add(number4);
		
		InputNode rightParenthesis2 = new InputNode(")", nodeType.PARENTHESIS, 0);
		testExpression.add(rightParenthesis2);
		
		InputNode rightParenthesis3 = new InputNode(")", nodeType.PARENTHESIS, 0);
		testExpression.add(rightParenthesis3);
		
		String result = CalcResult.CalcFinalResult(theHandler.prepareListForCalc(testExpression));
		System.out.println(result);
		assertEquals(result, "21");
	}

}
