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
	 * and adds the client to the server. If method call does not contain two parameters,
	 * the method prints an info and ends.
	 * If parameters are entered correctly a rmi url for the server is built and looked up with the Naming service.
	 * If the look up was successful, a ChatClientImpl object is created with the entered client name, where ChatClientImpl
	 * implements {@link ChatClient}. 
	 * The client is then added to the server: if successful, the chat client gets started on a new thread and an info
	 * containing the client's name is printed to notify of the successful adding. If unsuccessful, an error is printed
	 * and the user is asked to enter a new name for the client. Until newly named client gets successfully added to the
	 * server, the user will be repeatedly asked to input a new name that isn't taken yet.
	 * 
	 * @see ChatClientImpl
	 * @see ChatClient
	 *   
	 * @param args should contain two arguments, first argument is the server's name, second argument is the client's name
	 */
	public static void main(String[] args) {
		
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
				logger.error("This name is already taken, please chose another nickname.");
				InputStreamReader isr = new InputStreamReader(System.in);
				BufferedReader br = new BufferedReader(isr);
				try{
					name = br.readLine();
				}catch(IOException e){
					logger.error("An I/O exception ocurred");
				}
				chatClient.setName(name);
			}
			new Thread(chatClient).start();
			logger.info(name + ", you have been added to the chat succesfully.");
	
					
		}catch (RemoteException e){
			logger.error("Registry could not be contacted for server name look up");
			
		} catch (MalformedURLException e) {
			logger.error("Server url does not adhere to proper formatting");
			
		} catch (NotBoundException e) {
			logger.error("Server name is not currently bound");
		}
	}

}
