package uebung03chat;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;


public class ChatServerMain {


	public static void main(String[] args){

		System.out.println("test");

		try{
			System.out.println("test in try");
			ChatServerImpl chatServer = new ChatServerImpl();
			try {
				Naming.bind("ChatServer", chatServer);
			} catch (AlreadyBoundException e) {
				Naming.rebind("ChatServer", chatServer);
			}
			System.out.println("ChatServer running");
//			chatServer.addClient(new ChatClientImpl("john", chatServer));

		}catch(RemoteException | MalformedURLException e){
			System.out.println("ChatServer exception");
			e.printStackTrace();
		}
	}

}
