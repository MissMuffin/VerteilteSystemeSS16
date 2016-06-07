package uebung03serverClientXml;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.print.CancelablePrintJob;


public class Server {
		
	public static boolean cancel = false;
	
	public static void main(String[] args) {	
		
		try{
			int serverPort = 7896;
			ServerSocket listenSocket = new ServerSocket(serverPort);
			System.out.println("Server now running");
			while(!cancel){
				Socket clientSocket = listenSocket.accept();
				System.out.println("accepted connection with client");
				Connection c = new Connection(clientSocket);
			}
			listenSocket.close();
		}catch (IOException e){
			System.out.println("IO: "+ e.getMessage());
		}
	}
	
	public static void cancel() {
		cancel = true;
	}
}

class Connection extends Thread{

	Socket clientSocket;	
	InputStream in;
	OutputStream out;
	StudentHandler studentHandler = new StudentHandler();
	ProfessorHandler professorHandler= new ProfessorHandler();
	

	public Connection(Socket clientSocket){
		this.clientSocket = clientSocket;
		this.start();
	}

	public void run() {
		
		byte[] bytes = new byte[1000];
        int count;        
        try {
        	
        	DataInputStream inStream = new DataInputStream(clientSocket.getInputStream());
			DataOutputStream outStream = new DataOutputStream(clientSocket.getOutputStream());
			String type = inStream.readUTF();
			System.out.println("Type: " + type);
			outStream.writeUTF("ok");
			
			in = clientSocket.getInputStream();
			if (type.equals("student")) {
				out = new FileOutputStream(Paths.STUDENT_XML_SERVER.toString());				
			} else {
				out = new FileOutputStream(Paths.PROFESSOR_XML_SERVER.toString());								
			}
        	
        	while ((count = in.read(bytes)) > 0) {
        		out.write(bytes, 0, count);
        	}
        	
        	if (type.equals("student")) {
        		Student s = studentHandler.unmarshal(Paths.STUDENT_XML_SERVER);
        		System.out.println(s.toString());
			}else {
				Professor p = professorHandler.unmarshal(Paths.PROFESSOR_XML_SERVER);
				System.out.println(p.toString());
			}
        	
        	outStream = new DataOutputStream(clientSocket.getOutputStream());
        	System.out.println("Sending feedback");
        	outStream.writeUTF("received");        	
        	
        	out.close();
        	in.close();
        	inStream.close();
        	outStream.close();
        	clientSocket.close();
        	
        	Server.cancel = true;// seems to only work on second client run through?
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
