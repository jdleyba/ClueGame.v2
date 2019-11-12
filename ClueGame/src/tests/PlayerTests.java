package tests;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runners.JUnit4;

import java.awt.Color;
import java.lang.reflect.Field;
import java.util.ArrayList;

import clueGame.Board;
import clueGame.BoardCell;
import clueGame.Player;
import clueGame.computerPlayer;
import clueGame.humanPlayer;

public class PlayerTests {
	
	//This initialize a test player on the board were going to be using 
	private static Player playerHuman = new humanPlayer();
	private static Player playerComputer = new computerPlayer();
	private static ArrayList<Player> players;
	private static Board board;
	
	//The before takes in the files for setting up the board and players, which also checks to make sure the files are valid
	@BeforeClass 
	public static void setUp() {		
		board = Board.getInstance();
		board.setConfigFiles("ClueLayout.csv", "ClueLegend.txt");
		board.setPlayerConfigFiles("CTest_PlayerComputer.txt");
		board.setPlayerConfigFiles("CTest_PlayerHuman.txt");
		board.initialize();
		
		players = board.getPlayers();
		playerComputer = players.get(0);
		playerHuman = players.get(1);
	}
	@Test //test to make sure getName is loading the name properly
	public void testName() {
		assertEquals("Mr Green Center", playerHuman.getName());
		assertEquals("Colonel Mines Mustard", playerComputer.getName());
	}
	@Test //tests to make you can differentiate between computer and human
	public void testHuman() {
		assertEquals(true, playerHuman.isHuman());
		assertEquals(false, playerComputer.isHuman());
	}
	@Test //makes sure the strings are converted to color objects
	public void testColor() {
		Color green = convertColor("green");
		assertEquals(green, playerHuman.getColor());
		Color yellow = convertColor("yellow");
		assertEquals(yellow, playerComputer.getColor());
	}
	@Test //makes sure the character is starting in the right room
	public void testStartLocation() {
		BoardCell cellH = board.getCellAt(playerHuman.getStartRow(),playerHuman.getStartColumn());
		assertEquals(cellH.getInitial(), playerHuman.getRoomInitial());
		
		BoardCell cellC = board.getCellAt(playerComputer.getStartRow(),playerComputer.getStartColumn());
		assertEquals(cellC.getInitial(), playerComputer.getRoomInitial());
	}
	//converts string to color object
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
