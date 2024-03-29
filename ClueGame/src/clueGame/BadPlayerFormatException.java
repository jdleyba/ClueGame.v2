package clueGame;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Exception for bad format of data files
 * 
 * @author Mark Baldwin
 * @author Cyndi Rader
 *
 */
public class BadPlayerFormatException extends Exception {
	public static String logFile = "ClueLog.txt";
	private BufferedWriter logFileWriter;

	public BadPlayerFormatException() {
		super("A bad player configuration exception has occurred");
	}

	public BadPlayerFormatException(String message) {
		super(message);
		File logFileObj = new File(logFile);
		try {
			FileWriter fileWriter = new FileWriter(logFileObj, true);
			logFileWriter = new BufferedWriter(fileWriter);
			if (logFileWriter != null) {
				logFileWriter.write(message + "\n");
				logFileWriter.flush();
			}
		} catch (IOException e) {
			System.out.println("Error writing to log file: " + logFile);
			System.out.println(e.getMessage());
		}
	}

}
