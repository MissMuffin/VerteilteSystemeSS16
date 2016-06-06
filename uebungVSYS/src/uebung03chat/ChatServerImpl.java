package uebung03chat;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Implements the chat server interface.
 * 
 * @author Bianca Ploch & Saba Kues
 *
 */
public class ChatServerImpl extends UnicastRemoteObject implements ChatServer {

	private ArrayList<ChatClient> allClients;
	
	/**
	 * Constructor. Instantiates the Array list of clients.
	 * @throws RemoteException
	 */
	public ChatServerImpl() throws RemoteException {
		allClients = new ArrayList<ChatClient>();
	}
	
	/* 
	 * @see uebung03chat.ChatServer#addClient(uebung03chat.ChatClient)
	 */
	public synchronized boolean addClient(ChatClient objRef) throws RemoteException {
		String name = objRef.getName();
		for( Iterator<ChatClient> iter = allClients.iterator(); iter.hasNext();) {
			ChatClient cc = iter.next();
			try {
				if(cc.getName().equals(name)) {
					return false;
				}
			} catch (RemoteException e) {
				iter.remove();
			}
		}
		allClients.add(objRef);
		return true;
	}
	
	/* (non-Javadoc)
	 * @see uebung03chat.ChatServer#removeClient(uebung03chat.ChatClient)
	 */
	public synchronized void removeClient(ChatClient objRef) throws RemoteException {
		allClients.remove(objRef);
	}
	
	/* (non-Javadoc)
	 * @see uebung03chat.ChatServer#sendMessage(java.lang.String, java.lang.String)
	 */
	public synchronized void sendMessage(String name, String msg) throws RemoteException {
		for(Iterator<ChatClient> iter = allClients.iterator(); iter.hasNext();) {
			ChatClient cc = iter.next();
			try {
				cc.print(name + ": " + msg);
			} catch (RemoteException e) {
				iter.remove();
			}
		}
	}

}
