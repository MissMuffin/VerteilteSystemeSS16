package uebung03chat;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;


public class ChatClientMain {
	
	public static void main(String[] args) throws MalformedURLException, NotBoundException, RemoteException {
		
		if(args.length != 2){
			System.out.println("Please enter first the name of the Server and then your nickname");
			return;
		}
				
		try{
			String servername= args[0];
			String serverUrl = "rmi://" + servername + "/ChatServer";
			ChatServer chatServer = (ChatServer)Naming.lookup(serverUrl);
			
			String name = args[1];
			ChatClientImpl chatClient = new ChatClientImpl(name, chatServer);
			
			boolean added = chatServer.addClient(chatClient);
			if(!added){
				System.out.println("This name is already taken, please chose another nickname.");
			}else{
				new Thread(chatClient).start();
				System.out.println(name + ", you have been added to the chat succesfully.");
			}
					
		}catch(RemoteException e){
			e.printStackTrace();
//			Logger.getInstance().error("[ChatClientMain] error when creating and adding new Client: " + e);
			e.printStackTrace();
		}
	}

}
