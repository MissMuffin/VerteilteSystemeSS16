package util;

//TODO No documentation
public class Logger {

	private static Logger instance;


	private Logger() {
	}

	public static Logger getInstance() {
		if (Logger.instance == null)
			Logger.instance = new Logger();
		return instance;
	}

	public void info(String to_log) {
		log("Info", to_log);
	}

	public void error(String to_log) {
		log("Error", to_log);
	}

	private void log(String level, String to_log) {
		//Hier koennen Sie entscheiden an welchen Ort die Logs geschrieben werden sollen
		//z.B. auf stdout, in eine Datei oder beides..
		System.out.println(level + ": " + to_log);
		//Bei Programmen die ueber eine laengeren Zeitraum laufen waeren Ausgaben im folgendem Format sicherlich sinnvoll:
		//ERROR [07.10.2015-15:30:15] [SeverExample.java] Socket creation failed! Address already in use.
	}
}