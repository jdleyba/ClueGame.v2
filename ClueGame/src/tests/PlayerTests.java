package tests;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runners.JUnit4;

import clueGame.Board;
import clueGame.BoardCell;
import clueGame.Player;

public class PlayerTests {
	
	private static Player playerComputer;
	private static Board board;
	
	@BeforeClass 
	public static void setUp() {
		playerComputer = Player.getInstance();
		playerComputer.setConfigFile("CTest_PlayerComputer.txt");
		playerComputer.initialize();
		
		board = Board.getInstance();
		board.setConfigFiles("CTest_ClueLayout.csv", "CTest_ClueLegend.txt");
		board.initialize();
	}
	@Test
	public void testName() {
		assertEquals("Colonel Mines Mustard", playerComputer.getName());
	}
	@Test
	public void testHuman() {
		assertEquals(false, playerComputer.isHuman());
	}
	@Test
	public void testStartLocation() {
		BoardCell cell = playerComputer.getStartLocation();
		assertEquals(cell.getInitial(), playerComputer.getRoomInitial());
	}
}
