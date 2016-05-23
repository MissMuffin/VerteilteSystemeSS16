package uebung03chat;

import java.rmi.*;

/**
 * Interface for the chat server, includes methods for adding and 
 * removing client's and sending messages to all known clients.
 * 
 * @author Bianca Ploch & Saba Kues
 *
 */
public interface ChatServer extends Remote {
	
	/**
	 * Adds a client.
	 * 
	 * @param objRef reference to the client to be added
	 * @return true when added successfully
	 * @throws RemoteException
	 */
	public boolean addClient(ChatClient objRef) throws RemoteException;
	
	/**
	 * Removes a client.
	 * @param objRef reference to the client to be removed
	 * @throws RemoteException
	 */
	public void removeClient(ChatClient objRef) throws RemoteException;
	
	/**
	 * Sends a message.
	 * @param name of the client that sends the message
	 * @param msg to be sent to all known clients
	 * @throws RemoteException
	 */
	public void sendMessage(String name, String msg) throws RemoteException;
}
