package uebung03chat;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ChatClientImpl extends UnicastRemoteObject implements ChatClient {

	private String name;
	 
	public ChatClientImpl (String n) throws RemoteException {
		name = n;
	}
	
	public String getName() throws RemoteException {
		return name;
	}
	
	public void print (String msg) throws RemoteException {
		System.out.println(msg);
	}
}
