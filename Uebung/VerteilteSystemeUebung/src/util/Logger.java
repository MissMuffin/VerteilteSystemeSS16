package util;

/** 
 * Console logging class for info and error messages
 * 
 * @author Haase
 */

public class Logger {

	/**
	 * Static Logger instance
	 */
	private static Logger instance;


	/**
	 * Private constructor
	 */
	private Logger() {}

	/**
	 * returns static Logger instance
	 * if no instance exists yet the constructor will be called
	 * @return Logger
	 */
	public static Logger getInstance() {
		if (Logger.instance == null)
			Logger.instance = new Logger();
		return instance;
	}

	/**
	 * Logs a info message
	 * @param to_log the message to be logged
	 */
	public void info(String to_log) {
		log("Info", to_log);
	}

	/**
	 * Logs an error message
	 * @param to_log the error message to be logged
	 */
	public void error(String to_log) {
		log("Error", to_log);
	}

	/**
	 * Prints a message and its level to the console as a new line
	 * @param level		the level of the log: error or info
	 * @param to_log	the message to be logged
	 */
	private void log(String level, String to_log) {
		System.out.println(level + ": " + to_log);
	}
}