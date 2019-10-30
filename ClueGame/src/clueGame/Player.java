package clueGame;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Player {
	// File names for the config files
	private String playerConfigFile;
	private String playerName;
	private static Player theInstance = new Player();


	private Player() {
	}

	public static Player getInstance(){
			return theInstance;
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
