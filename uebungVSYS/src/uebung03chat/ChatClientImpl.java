package uebung03chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import util.Logger;


/**
 * Implements the chat client interface. 
 * 
 * @author Bianca Ploch & Saba Kues
 *
 */
public class ChatClientImpl extends UnicastRemoteObject implements ChatClient, Runnable {

	private String name;
	private ChatServer chatServer;
	
	/**
	 * Constructor 
	 * 
	 * @param n is the name of the client
	 * @param cS is the reference to the remote server object
	 * @throws RemoteException
	 */
	public ChatClientImpl(String n, ChatServer cS) throws RemoteException {
		super();
		name = n;
		chatServer=cS;
	}

	/**
	 * Returns the client's name
	 * 
	 * @return client name
	 * @throws RemoteException
	 */
	@Override
	public String getName() throws RemoteException {
		return name;
	}


	/**
	 * Prints a message to the console.
	 * 
	 * @param msg to be printed
	 * @throws RemoteException
	 */
	@Override
	public void print(String msg) throws RemoteException {
		System.out.println(msg);
	}

	/**
	 * Sets the client's name
	 * @param name to be set
	 */
	public void setName(String name){
		this.name=name;
	}
	
	/**
	 * Takes the client's input and sends it to the server.
	 * @throws IOException
	 */
	@Override
	public void run() {

		while(true){
			try{
				InputStreamReader isReader = new InputStreamReader(System.in);
				BufferedReader bReader = new BufferedReader(isReader);
				String input = bReader.readLine();
				chatServer.sendMessage(this.getName(), input);
				
			}catch(IOException e){
				Logger.getInstance().error(e.getMessage());
			}
		}
	}
}
