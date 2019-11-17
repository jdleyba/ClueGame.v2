package tests;

import java.util.Iterator;
import java.util.Set;

//Doing a static import allows me to write assertEquals rather than
//assertEquals
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

import clueGame.Board;
import clueGame.BoardCell;
import clueGame.Player;
import clueGame.computerPlayer;
import clueGame.humanPlayer;

/**
 * This class tests that adjacencies and targets are calculated correctly.
 * 
 * @author Mark Baldwin
 * @author Cyndi Rader
 *
 */
public class gameActionTests {
	// We make the Board static because we can load it one time and
	// then do all the tests.
	private static Player playerHuman = new humanPlayer();
	private static Player playerComputer = new computerPlayer();
	private static Board board;

	@BeforeClass
	public static void setUp() {
		// Board is singleton, get the only instance
		board = Board.getInstance();
		// set the file names to use my config files
		board.setConfigFiles("CTest_ClueLayout.csv", "CTest_ClueLegend.txt");
		// Initialize will load BOTH config files
		board.initialize();
		
		playerHuman.setConfigFile("CTest_PlayerHuman.txt");
		playerComputer.setConfigFile("CTest_PlayerComputer.txt");

	}
	@Test
	public void selectTargetLocation() {
		//Gets a set of targets to test 
		board.findAllTargets(playerComputer.getCurrentLocation(), 6);
		Set<BoardCell> targets = board.getTargets();
		
		//Tests to make sure the computers chosen cell is in targets
		BoardCell cell = ((computerPlayer) playerComputer).pickLocation(targets);
		assertTrue(targets.contains(cell));
		
		//This test ensures that if there is a room that hasn't been visited the computer picks that cell
		Iterator<BoardCell> iterator = targets.iterator();
		while(iterator.hasNext()) {
			BoardCell cell2 = iterator.next();
			if(cell2.isRoom()) {
				if(cell2.getInitial() != ((computerPlayer) playerComputer).getLastVisited()) {
					assertEquals(cell2.getInitial(), cell.getInitial());
				}
			}
		}
	}
	@Test
	public void testSuggestion() {
		
	}
}
