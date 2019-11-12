package clueGame;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.awt.Color;
import java.lang.reflect.Field;

public class Player {
	// File names for the config files
	private String playerConfigFile;
	private String playerName;
	private Color playerColor;
	private boolean isHuman;
	private char roomInitial;
	private int startRow;
	private int startColumn;
	public BoardCell currentLocation;


	public Player() {}

	public int getStartRow() {
		return startRow;
	}
	public int getStartColumn() {
		return startColumn;
	}

	public Object getRoomInitial() {
		return roomInitial;
	}

	public Object getName() {
		return getPlayerName();
	}

	public Object isHuman() {
		return isHuman;
	}

	public Object getColor() {
		return getPlayerColor();
	}
	public BoardCell getCurrentLocation() {
		return currentLocation;
	}


	public String getPlayerName() {
		return playerName;
	}


	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}


	public Color getPlayerColor() {
		return playerColor;
	}


	public void setPlayerColor(Color playerColor) {
		this.playerColor = playerColor;
	}


	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}


	public void setStartColumn(int startColumn) {
		this.startColumn = startColumn;
	}


	public void setHuman(boolean isHuman) {
		this.isHuman = isHuman;
	}


	public void setRoomInitial(char roomInitial) {
		this.roomInitial = roomInitial;
	}

}
