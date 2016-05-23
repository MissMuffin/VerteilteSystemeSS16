package uebung03chat;

import java.rmi.Remote;

public interface ChatClient extends Remote {
	
	public String getName() throws java.rmi.RemoteException;
	public void print(String msg) throws java.rmi.RemoteException;

}
