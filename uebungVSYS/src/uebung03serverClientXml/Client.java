package uebung03serverClientXml;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	
	public static void main(String args[]) {
		try{
			int serverPort = 7896;			
			Socket socket = new Socket("localhost", serverPort);
			
			File file = new File(Paths.XML_STUDENT);
	        // Get the size of the file
	        long length = file.length();
	        byte[] bytes = new byte[16 * 1024];
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
