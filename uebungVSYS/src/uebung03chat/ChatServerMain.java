package uebung03chat;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;

import util.Logger;


/**
 * Main-method that starts the server and binds the server name to rmiregistry.
 * @author Bianca Ploch & Saba Kues
 *
 */
public class ChatServerMain {

	/**
	 * Main-method that starts the server and binds the server name to rmiregistry.
	 * The server objects is of type {@link ChatServerImpl} and thus impements the interface
	 * {@link ChatServer}.
	 * After successful binding a message that the server is running is printed to console.
	 * 
	 * @see ChatServerImpl
	 * @see ChatServer
	 * 
	 * @param args no arguments required
	 */
	public static void main(String[] args){

		try {
			ChatServerImpl chatServer = new ChatServerImpl();
			try {
				Naming.bind("ChatServer", chatServer);
			} catch (AlreadyBoundException e) {
				Naming.rebind("ChatServer", chatServer);
			}
			Logger.getInstance().info("ChatServer running");

		} catch (MalformedURLException e){
			Logger.getInstance().error("Server url does not adhere to proper formatting");
			
		} catch (RemoteException e) {
			Logger.getInstance().error("Registry could not be contacted for server name look up");
		}
	}

}
