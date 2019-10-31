package tests;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runners.JUnit4;

import java.awt.Color;
import java.lang.reflect.Field;

import clueGame.Board;
import clueGame.BoardCell;
import clueGame.Player;

public class HumanTests {
	
	
	private static Player playerHuman;
	private static Board board;
	
	@BeforeClass 
	public static void setUp() {
		playerHuman = Player.getInstance();
		playerHuman.setConfigFile("CTest_PlayerHuman.txt");
		playerHuman.initialize();
		
		board = Board.getInstance();
		board.setConfigFiles("ClueLayout.csv", "ClueLegend.txt");
		board.initialize();
	}
	@Test
	public void testName() {
		assertEquals("Mr Green Center", playerHuman.getName());
	}
	@Test
	public void testHuman() {
		assertEquals(true, playerHuman.isHuman());
	}
	@Test
	public void testColor() {
		Color green = convertColor("green");
		assertEquals(green, playerHuman.getColor());
	}
	@Test
	public void testStartLocation() {
		BoardCell cellH = board.getCellAt(playerHuman.getStartRow(),playerHuman.getStartColumn());
		assertEquals(cellH.getInitial(), playerHuman.getRoomInitial());
	}
	public Color convertColor(String strColor) {
		 Color color;
		 try {
		 // We can use reflection to convert the string to a color
		 Field field = Class.forName("java.awt.Color").getField(strColor.trim());
		 color = (Color)field.get(null);
		 } catch (Exception e) {
		 color = null; // Not defined
		 }
		 return color;
		}
}
