package uebung03serverClientXml;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.print.CancelablePrintJob;

import util.Logger;


public class Server {
		
	private static boolean cancel = false;
	private static final int SERVER_PORT = 7896;
	private static Logger logger = Logger.getInstance();
	
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
	
	public static void cancel() {
		cancel = true;
	}
}
