package clueGame;

import java.util.Iterator;
import java.util.Set;

public class computerPlayer extends Player {
	private char lastVisited;
	private BoardCell targetCell;
	public computerPlayer() {
	}
	public BoardCell pickLocation(Set<BoardCell> targets) {
		
		Iterator<BoardCell> iterator = targets.iterator();
		while(iterator.hasNext()) {
			BoardCell cell = iterator.next();
			if(cell.isRoom()) {
				if(cell.getInitial() != lastVisited) {
					targetCell = cell;
					return targetCell;
				}
			}
		}
		
		Iterator<BoardCell> iterator2 = targets.iterator();
		while(iterator2.hasNext()) {
			BoardCell cell = iterator2.next();
			if(cell.isRoom()) {
				if(cell.getInitial() != lastVisited) {
					targetCell = cell;
					return targetCell;
				}
			}
		}
		return targetCell;
	}
	public void makeAccusation() {

	}
	public void createSuggestion() {
		
	}
	public void updateLastVisited() {
		
	}
	public char getLastVisited() {
		return lastVisited;
	}
	
}
