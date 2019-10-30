package tests;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runners.JUnit4;

import clueGame.Board;
import clueGame.BoardCell;
import clueGame.Player;

public class PlayerTests {
	
	private static Player player;
	private static Board board;
	
	@BeforeClass 
	public static void setUp() {
		player = Player.getInstance();
		player.setConfigFile("CTest_Player.txt");
		player.initialize();
		
		board = Board.getInstance();
		board.setConfigFiles("CTest_ClueLayout.csv", "CTest_ClueLegend.txt");
		board.initialize();
	}
	@Test
	public void testName() {
		assertEquals("Colonel Mines Mustard", player.getName());
	}
	@Test
	public void testHuman() {
		assertEquals(false, player.isHuman());
	}
	@Test
	public void testStartLocation() {
		BoardCell cell = player.getStartLocation();
		assertEquals(cell.getInitial(), player.getRoomInitial());
	}
}
