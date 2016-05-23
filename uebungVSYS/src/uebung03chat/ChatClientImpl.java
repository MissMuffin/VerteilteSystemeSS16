package uebung03chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


public class ChatClientImpl extends UnicastRemoteObject implements ChatClient, Runnable {

	private static final long serialVersionUID = 1L;
	private String name;
	private ChatServer chatServer;
	public ChatClientImpl(String n, ChatServer cS) throws RemoteException {
		super();
		name = n;
		chatServer=cS;
	}

	@Override
	public String getName() throws RemoteException {
		return name;
	}


	@Override
	public void print(String msg) throws RemoteException {
		System.out.println(msg);
	}


	@Override
	public void run() {

		while(true){
			try{
				InputStreamReader isReader = new InputStreamReader(System.in);
				BufferedReader bReader = new BufferedReader(isReader);
				String input = bReader.readLine();
				chatServer.sendMessage(this.getName(), input);
				
			}catch(IOException e){
//				Logger.getInstance().error("[ChatClientImpl] error reading input: " + e);
			}
		}
	}
}
