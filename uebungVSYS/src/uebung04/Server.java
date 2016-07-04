package uebung04;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


/**
 * Class containing main method to run the server.
 * Listens for client socket and connects to it, then starts {@link Connection#run()}.
 * Server will stop after successful file transfer, validation and serialization.
 * @author Bianca Ploch
 */
public class Server {
		
	/**
	 * Port the server socket run on
	 */
	private static final int SERVER_PORT = 7896;
	private static boolean cancel = false;
	private static Logger logger = Logger.getInstance();
	
	/**
	 * Listens for client socket and runs Connection after client connected
	 * @param args No arguments used
	 */
	public static void main(String[] args) {	
		
		try{
			ServerSocket listenSocket = new ServerSocket(SERVER_PORT);
			System.out.println("Server now running");
			
			while(!cancel){
				Socket clientSocket = listenSocket.accept();
				System.out.println("accepted connection with client");
				Connection c = new Connection(clientSocket);
				c.run();
			}
			listenSocket.close();
		}catch (IOException e){
			logger.error(Strings.IO_EXCEPTION);
		}
	}
	
	/**
	 * Stops the server execution
	 */
	public static void cancel() {
		cancel = true;
	}
}
