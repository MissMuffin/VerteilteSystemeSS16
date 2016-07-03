package uebung03chat;

import java.rmi.Remote;

/**
 * Interface for the chat client, including getter and setter methods
 * for the client's name and printing messages to the console.
 * 
 * @author Bianca Ploch & Saba Kues
 */
public interface ChatClient extends Remote {
	
	/**
	 * Getter-method for client's name
	 * 
	 * @return name of the client
	 * @throws java.rmi.RemoteException
	 * 
	 */
	public String getName() throws java.rmi.RemoteException;
	
	/**
	 * Prints the message to the Console
	 * 
	 * @param msg to be printed
	 * @throws java.rmi.RemoteException
	 */
	public void print(String msg) throws java.rmi.RemoteException;
	
	
	/**
	 * Setter-method for the client's name
	 * 
	 * @param name to be set
	 * @throws java.rmi.RemoteException
	 */
	public void setName(String name) throws java.rmi.RemoteException;

}
