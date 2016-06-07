package uebung03serverClientXml;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import util.Logger;

public class Client {
	
	private static Logger logger = Logger.getInstance();
	public static void main(String args[]) {
		try{
			int serverPort = 7896;			
			Socket socket = new Socket("localhost", serverPort);
			
			logger.info("Please decide whether you will enter professor's [p] or"
					+ "student's [s] details: ");
			
			InputStreamReader isr = new InputStreamReader(System.in);
			BufferedReader br = new BufferedReader(isr);
			
			View view = new View(br.readLine());
			File file;
			if(view.decision.equalsIgnoreCase("s")){
				file = new File(Paths.XML_STUDENT);
			}else{
				file = new File(Paths.XML_PROFESSOR);
			}
	        // Get the size of the file
	        long length = file.length();
	        byte[] bytes = new byte[(int)length];
	        InputStream in = new FileInputStream(file);
	        OutputStream out = socket.getOutputStream();
	
	        int count;
	        while ((count = in.read(bytes)) > 0) {
	            out.write(bytes, 0, count);
	        }
	
	        out.close();
	        in.close();
	        socket.close();
			
			
//			DataInputStream in = new DataInputStream(socket.getInputStream());
//			DataOutputStream out = new DataOutputStream(socket.getOutputStream());
//			out.writeUTF("testest");
//			String data = in.readUTF();
//			System.out.println("Received: " + data);
//			socket.close();
			
		}catch(UnknownHostException e){
			System.out.println("Sock: " + e.getMessage());
		}catch(EOFException e){
			System.out.println("EOF: " + e.getMessage());
		}catch(IOException e){
			System.out.println("IO: " + e.getMessage());
		}
		
	}
		
}
