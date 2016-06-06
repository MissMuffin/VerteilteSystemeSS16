package uebung03chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import util.Logger;


/**
 * Main-method to start the chat client. 
 * @author Bianca Ploch & Saba Kues
 *
 */
public class ChatClientMain {
	private static Logger logger= Logger.getInstance();
	/**
	 * The main-method checks the arguments, the availability of the client's name
	 * and adds the client to the server. 
	 * 
	 * @param args should contain two arguments, first argument is the server's name, second argument is the client's name
	 * 
	 * @throws MalformedURLException
	 * @throws NotBoundException
	 * @throws RemoteException
	 */
	public static void main(String[] args) throws MalformedURLException, NotBoundException, RemoteException {
		
		if(args.length != 2){
			logger.info("Please enter first the name of the Server and then your nickname");
			return;
		}
				
		try{
			String servername= args[0];
			String serverUrl = "rmi://" + servername + "/ChatServer";
			ChatServer chatServer = (ChatServer)Naming.lookup(serverUrl);
			
			String name = args[1];
			
			
			ChatClientImpl chatClient = new ChatClientImpl(name, chatServer);
			while (!chatServer.addClient(chatClient)) {
				logger.info("This name is already taken, please chose another nickname.");
				InputStreamReader isr = new InputStreamReader(System.in);
				BufferedReader br = new BufferedReader(isr);
				try{
					name = br.readLine();
				}catch(IOException e){
					e.printStackTrace();
				}
				chatClient.setName(name);
			}
			new Thread(chatClient).start();
			logger.info(name + ", you have been added to the chat succesfully.");
	
					
		}catch(RemoteException e){
			logger.error(e.getMessage());
		}
	}

}
