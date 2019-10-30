package clueGame;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Player {
	// File names for the config files
	private String playerConfigFile;
	
	public static Player getInstance() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setConfigFile(String playerConfig) {
		playerConfigFile = playerConfig;
		
		try{
			loadConfigFile();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	private void loadConfigFile() throws FileNotFoundException, BadConfigFormatException{
		FileReader in = new FileReader(playerConfigFile);
		Scanner playerConfig = new Scanner(in);
	}

	public void initialize() {
		// TODO Auto-generated method stub
		
	}

	public BoardCell getStartLocation() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object getRoomInitial() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object getName() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object isHuman() {
		// TODO Auto-generated method stub
		return null;
	}

}
