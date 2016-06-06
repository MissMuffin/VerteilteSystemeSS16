package uebung03serverClientXml;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {
	public static void main(String[] args) {
		try{
			int serverPort = 7896;
			ServerSocket listenSocket = new ServerSocket(serverPort);
			while(true){
				Socket clientSocket = listenSocket.accept();
				Connection c = new Connection(clientSocket);
			}
		}catch (IOException e){
			System.out.println("IO: "+ e.getMessage());
		}
	}
}

class Connection extends Thread{

	DataInputStream in;
	DataOutputStream out;
	Socket clientSocket;

	public Connection(Socket aClientSocket){
		try{
			clientSocket = aClientSocket;
			in = new DataInputStream(clientSocket.getInputStream());
			out = new DataOutputStream(clientSocket.getOutputStream());
			this.start(); 
		}catch(IOException e){
			System.out.println("Connection: "+ e.getMessage());
		}
	}

	public void run() {
		try{
			
			String data = in.readUTF();
			out.writeUTF(data);
			clientSocket.close();
		}catch(EOFException e){
			System.out.println("EOF: " + e.getMessage());
		}catch (IOException e) {
			System.out.println("IO: " + e.getMessage());
		}
	}

}
