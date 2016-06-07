package uebung03serverClientXml;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import util.Logger;


public class Server {
		
	private static  boolean cancel = false;
	private static Logger logger = Logger.getInstance();
	
	public static void main(String[] args) {	
		
		try{
			int serverPort = 7896;
			ServerSocket listenSocket = new ServerSocket(serverPort);
			logger.info("Server up, running and listening." );
			
			while(!cancel){
				Socket clientSocket = listenSocket.accept();
				Connection c = new Connection(clientSocket);
				System.out.println("accepted connection with client");
			}
		}catch (IOException e){
			System.out.println("IO: "+ e.getMessage());
		}
	}
	
	public void cancel() {
		this.cancel = true;
	}
}

class Connection extends Thread{

	Socket clientSocket;	
	InputStream in;
	OutputStream out;
	HandlerStudent handlerStudent = new HandlerStudent();
	

	public Connection(Socket clientSocket){
		try{
			this.clientSocket = clientSocket;
			
			in = clientSocket.getInputStream();
			out = new FileOutputStream(Paths.XML_STUDENT_SERVER);
			this.start(); 
			
		}catch(IOException e){
			System.out.println("Connection: "+ e.getMessage());
		}
	}

	public void run() {
		
		byte[] bytes = new byte[1000];
        int count;        
        try {
        	while ((count = in.read(bytes)) > 0) {
        		out.write(bytes, 0, count);
        	}
        	
        	//TODO validierung & feedback to client
        	Student s = handlerStudent.unmarshal(Paths.XML_STUDENT_SERVER);
    		System.out.println(s.toString());
        	
        	out.close();
        	in.close();
        	clientSocket.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
