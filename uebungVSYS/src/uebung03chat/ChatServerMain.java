package uebung03chat;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;

import uebung1.parkhaus.Logger;

public class ChatServerMain {

	public static void main(String[] args) throws AlreadyBoundException, MalformedURLException {
		try{
			ChatServerImpl chatServer = new ChatServerImpl();
			Naming.bind("ChatServer", chatServer);
			System.out.println("ChatServer running");

		}catch(RemoteException re){
//			Logger.getInstance().error("[ChatServerMain] RemoteException when starting the Server:" + re);
		}
	}

}
