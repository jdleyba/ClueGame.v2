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
	

<<<<<<< HEAD
	public Player() {
=======
	private Player() {
		
>>>>>>> 8a12aa6499b011b4bbe484c4b4b68dcec05d95c4
	}
	
	public void setConfigFile(String playerConfig) {
		playerConfigFile = playerConfig;
	}
	
	public void initialize() {
		try{
			loadConfigFile();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	private void loadConfigFile() throws FileNotFoundException, BadConfigFormatException{
		FileReader in = new FileReader(playerConfigFile);
		Scanner playerConfig = new Scanner(in);
		
		playerName = playerConfig.nextLine();
		String color = playerConfig.nextLine();
		playerColor = convertColor(color);
		String humanBool = playerConfig.nextLine();
		String roomName = playerConfig.nextLine();
		String startPos = playerConfig.nextLine();
		String[] toInt = startPos.split(",");
		startRow = Integer.parseInt(toInt[0]);
		startColumn = Integer.parseInt(toInt[1]);
		if(humanBool.equals("Human")) {
			isHuman = true;
		}
		else {
			isHuman = false;
		}
		
		roomInitial = roomName.charAt(0);
		
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
		return playerName;
	}

	public Object isHuman() {
		return isHuman;
	}

	public Object getColor() {
		return playerColor;
	}

}
