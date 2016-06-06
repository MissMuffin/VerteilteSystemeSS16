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
	 * @param args no arguments required
	 */
	public static void main(String[] args){

		try{
			ChatServerImpl chatServer = new ChatServerImpl();
			try {
				Naming.bind("ChatServer", chatServer);
			} catch (AlreadyBoundException e) {
				Naming.rebind("ChatServer", chatServer);
			}
			Logger.getInstance().info("ChatServer running");

		}catch(RemoteException | MalformedURLException e){
			Logger.getInstance().error(e.getMessage());
		}
	}

}
