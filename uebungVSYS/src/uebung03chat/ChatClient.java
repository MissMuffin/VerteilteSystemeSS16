package uebung03chat;

public interface ChatClient {
	
	public String getName() throws java.rmi.RemoteException;
	public void print(String msg) throws java.rmi.RemoteException;

}
