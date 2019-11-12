package clueGame;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * Board Class
 * 
 * @author Mark Baldwin
 * @author Cyndi Rader
 *
 */
public class Board {
	// File names for the config files
	private String boardConfigFile;
	private String roomConfigFile;

	// Dimensions of the board, determined from config file
	private int numRows;
	private int numColumns;
	// 2D list of cells
	private BoardCell[][] board;
	// Map from character to room, e.g.,C -> Conservatory. Loaded from room config.
	private Map<Character, String> legend = new HashMap<Character, String>();
	// Data structure containing the targets
	private Set<BoardCell> targets = null;
	// Data structure used during path calculation
	// Used an instance var for efficiency (since recursive call)
	private Set<BoardCell> visited;
	private ArrayList<String> playerConfigFiles =new ArrayList<String>();
	private ArrayList<Player> players= new ArrayList<Player>();
	private ArrayList<String> roomNames;

	// variable used for singleton pattern
	private static Board theInstance = new Board();

	/**
	 * Constructor - constructor is private to ensure only one can be created
	 */
	private Board() {
	}

	/**
	 * this method returns the only Board
	 * 
	 * @return - the game board
	 */
	public static Board getInstance() {
		return theInstance;
	}

	/**
	 * set the configuration files
	 * 
	 * @param boardConfig
	 * @param roomConfig
	 */
	public void setConfigFiles(String boardConfig, String roomConfig) {
		boardConfigFile = boardConfig;
		roomConfigFile = roomConfig;
	}
	public void setPlayerConfigFiles(String playerConfig) {
		playerConfigFiles.add(playerConfig);
	}
	/**
	 * Initialize the game
	 */
	public void initialize() {
		// load our data
		loadConfigFiles();
		// Calculate the adjacency lists one time, so they are ready for calcTargets
		// This must be called _after_ the board is loaded!
		loadPlayerConfigFiles();
		calcAdjacencies();
	}

	/**
	 * Methods to load the configuration files
	 */
	private void loadConfigFiles() {
		try {
			// Rooms are needed for guesses, detective notes, and the board display
			loadRoomConfig();
			// board must then load its own config file and calculate adjacencies
			loadBoardConfig();
		} catch (Exception e) {
			// Handles various configuration file issues
			System.out.println(e.getMessage());
		}
	}
	public void loadPlayerConfigFiles() {
		Iterator<String> itr = playerConfigFiles.iterator();
		
		while(itr.hasNext()) {
			try {
				loadPlayerConfig(itr.next());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void loadPlayerConfig(String playerFile) throws FileNotFoundException, BadPlayerFormatException{
		FileReader in = new FileReader(playerFile);
		Scanner playerConfig = new Scanner(in);
		Player player = new Player();
		
		player.setPlayerName(playerConfig.nextLine());
		String color = playerConfig.nextLine();
		player.setPlayerColor(convertColor(color));
		String humanBool = playerConfig.nextLine();
		String roomName = playerConfig.nextLine();
		String startPos = playerConfig.nextLine();
		String[] toInt = startPos.split(",");
		player.setStartRow(Integer.parseInt(toInt[0]));
		player.setStartColumn(Integer.parseInt(toInt[1]));
		if(humanBool.equals("Human")) {
			player.setHuman(true);
		}
		else {
			player.setHuman(false);
		}
		player.setRoomInitial(roomName.charAt(0));
		
		getPlayers().add(player);
		playerConfig.close();
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
	/**
	 * Loads the room configuration data
	 * 
	 * @throws FileNotFoundException
	 * @throws BadConfigFormatException
	 */
	public void loadRoomConfig() throws FileNotFoundException, BadConfigFormatException {
		FileReader is = new FileReader(roomConfigFile);
		Scanner roomConfig = new Scanner(is);

		// set up the room names
		roomNames = new ArrayList<String>();

		// process the data
		while (roomConfig.hasNextLine()) {
			String line = roomConfig.nextLine();
			String[] tokens = line.split(",");
			if (tokens.length != 3) {
				roomConfig.close();
				throw new BadConfigFormatException("Room file format incorrect " + line);
			}

			// Room must be stored in the legend
			Character key = new Character(tokens[0].charAt(0));
			String roomName = tokens[1].trim();
			legend.put(key, roomName);

			// Rooms also represent cards
			String roomType = tokens[2].trim();
			if (!(roomType.equals("Card") || roomType.equals("Other"))) {
				roomConfig.close();
				throw new BadConfigFormatException("Room file format incorrect " + line);
			}
		}
		roomConfig.close();
	}

	/**
	 * Loads the board configuration data
	 * 
	 * @throws FileNotFoundException
	 * @throws BadConfigFormatException
	 */
	public void loadBoardConfig() throws FileNotFoundException, BadConfigFormatException {
		ArrayList<String> dataLines = new ArrayList<String>(); // holds the data for reprocessing

		// open up the board config file
		FileReader is = new FileReader(boardConfigFile);
		Scanner boardConfig = new Scanner(is);

		// load the data into dataLines as strings
		int row = 0;
		while (boardConfig.hasNextLine()) {
			String line = boardConfig.nextLine();
			dataLines.add(line);

			// Use the first row to set the number of columns
			if (row == 0) {
				String[] tokens = line.split(",");
				numColumns = tokens.length;
			}
			// Update the number of rows
			row++;
		}

		// close the data file
		boardConfig.close();

		// we now have rows and columns, initialize the board array
		numRows = row;
		board = new BoardCell[numRows][numColumns];

		// now add cells to the board
		row = 0;
		for (String line : dataLines) {
			String[] tokens = line.split(",");
			// For all other rows, ensure same number of columns
			if (numColumns != tokens.length) {
				boardConfig.close();
				throw new BadConfigFormatException("Rows do not all have the same number of columns");
			}
			for (int col = 0; col < numColumns; col++) {
				// Ensure it's a valid room
				Character key = tokens[col].charAt(0);
				String room = legend.get(key);
				if (room == null) {
					boardConfig.close();
					throw new BadConfigFormatException("Room not defined " + tokens[col]);
				} else {
					board[row][col] = new BoardCell(row, col, tokens[col].toUpperCase());
				}
			}
			row++;
		}

	}

	/**
	 * Method to calculate adjacencies. Called once at beginning of program.
	 */
	public void calcAdjacencies() {
		// Calculate the adjacency list for each location
		for (int row = 0; row < numRows; row++) {
			for (int col = 0; col < numColumns; col++) {
				addAdjacencies(row, col);
			}
		}
	}

	/**
	 * Calculate the adjacencies for a single cell
	 * 
	 * @param row
	 * @param col
	 */
	private void addAdjacencies(int row, int col) {
		Set<BoardCell> neighbors = new HashSet<BoardCell>();
		// Only need to calculate adjacency lists for walkway and doorway
		// Can't walk around in rooms
		BoardCell cell = board[row][col];
		if (cell.isDoorway()) {
			checkDoorway(row, col, cell.getDoorDirection(), neighbors);
		} else if (cell.isWalkway()) {
			// Check four neighbors, no diagonals
			// check square below, can only enter if doorway is DOWN
			checkNeighbor(row - 1, col, DoorDirection.DOWN, neighbors);
			// check square above, can only enter if doorway is UP
			checkNeighbor(row + 1, col, DoorDirection.UP, neighbors);
			// check square to left, can only enter if doorway is RIGHT
			checkNeighbor(row, col - 1, DoorDirection.RIGHT, neighbors);
			// check square to right, can only enter if doorway is LEFT
			checkNeighbor(row, col + 1, DoorDirection.LEFT, neighbors);
		} else {
			// if not a walkway or doorway, just put empty list
		}
		// put it in the cell
		cell.setAdjacencies(neighbors);
	}

	/**
	 * For a doorway in any given direction, only one neighbor should be added to
	 * the adjacency list. For example, if the doorway direction is down, only the
	 * cell below (row+1) should be added.
	 * 
	 * @param row       - row of location
	 * @param col       - col of location
	 * @param direction - door direction
	 * @param neighbors - neighbors to cell
	 */
	private void checkDoorway(int row, int col, DoorDirection direction, Set<BoardCell> neighbors) {
		if (direction == DoorDirection.DOWN && row + 1 < numRows && board[row + 1][col].isWalkway()) {
			neighbors.add(board[row + 1][col]);
		} else if (direction == DoorDirection.UP && row - 1 >= 0 && board[row - 1][col].isWalkway()) {
			neighbors.add(board[row - 1][col]);
		} else if (direction == DoorDirection.LEFT && col - 1 >= 0 && board[row][col - 1].isWalkway()) {
			neighbors.add(board[row][col - 1]);
		} else if (direction == DoorDirection.RIGHT && col + 1 < numColumns && board[row][col + 1].isWalkway()) {
			neighbors.add(board[row][col + 1]);
		}
	}

	/**
	 * For a walkway
	 * 
	 * @param row       - row of location
	 * @param col       - col of location
	 * @param direction - if the adjacent cell is a door, it's direction to qualify
	 * @param neighbors - neighbors set to populate
	 */
	private void checkNeighbor(int row, int col, DoorDirection direction, Set<BoardCell> neighbors) {
		// First ensure a valid index
		if (row < 0 || col < 0 || row >= numRows || col >= numColumns) {
			return;
		}

		// get the cell
		BoardCell cell = board[row][col];
		// If this piece is a walkway and neighbor is walkway, add it
		if (cell.isWalkway()) {
			neighbors.add(cell);
		// If this piece is a walkway and neighbor is door, ensure direction
		} else if (cell.isDoorway()) {
			DoorDirection dir = cell.getDoorDirection();
			if (dir == direction) {
				neighbors.add(cell);
			}
		}
	}

	/**
	 * Methods to calculate the set of targets
	 * 
	 * @param row        - initial row
	 * @param col        - initial colum
	 * @param pathLength - length of path
	 */
	public void calcTargets(int row, int col, int pathLength) {
		BoardCell startNode = board[row][col];
		// Reset the data structures
		targets = new HashSet<BoardCell>();
		visited = new HashSet<BoardCell>();
		// Initialize a search from this start node
		visited.add(startNode);
		// start the recursive procedure
		findAllTargets(startNode, pathLength);
	}

	public void findAllTargets(BoardCell thisCell, int numSteps) {
		Set<BoardCell> adjList = thisCell.getAdjacencies();
		for (BoardCell adjCell : adjList) {
			// can't retrace steps, if cell already on path move on
			if (visited.contains(adjCell))
				continue;
			visited.add(adjCell);
			// can always enter a door
			if (adjCell.isDoorway())
				targets.add(adjCell);
			// if we've used the entire roll of the die, this is a target
			else if (numSteps == 1)
				targets.add(adjCell);
			else
				findAllTargets(adjCell, numSteps - 1);
			visited.remove(adjCell);
		}
	}

	/**
	 * room name needed to display on board and also for computer player suggestions
	 * 
	 * @param initial
	 * @return name of room
	 */
	public String getRoomName(char initial) {
		return legend.get(initial);
	}

	// players needs access to the targets
	public Set<BoardCell> getTargets() {
		return targets;
	}

	// Getters used just for testing
	public Map<Character, String> getLegend() {
		return legend;
	}

	public int getNumRows() {
		return numRows;
	}

	public int getNumColumns() {
		return numColumns;
	}

	public BoardCell getCellAt(int row, int col) {
		return board[row][col];
	}

	public Set<BoardCell> getAdjList(int row, int col) {
		BoardCell cell = board[row][col];
		return cell.getAdjacencies();
	}

	// main just for testing
	public static void main(String[] args) {
		// Board is singleton, get the only instance
		Board board = Board.getInstance();
		// set the file names to use my config files
		board.setConfigFiles("data/ClueLayout.csv", "data/ClueLegend.txt");
		// Initialize will load BOTH config files
		board.initialize();

	}

	public ArrayList<Player> getPlayers() {
		return players;
	}

	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}
}
