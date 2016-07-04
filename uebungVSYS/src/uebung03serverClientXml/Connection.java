package uebung03serverClientXml;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import javax.xml.bind.JAXBException;
import javax.xml.bind.ValidationException;

import util.Logger;

public class Connection {

	private Socket clientSocket;	
	private StudentHandler studentHandler = new StudentHandler();
	private ProfessorHandler professorHandler= new ProfessorHandler();
	private Logger logger = Logger.getInstance();
	

	public Connection(Socket clientSocket){
		this.clientSocket = clientSocket;
	}

	public void run() {
		
		        
        try {
        	
        	DataInputStream inStream = new DataInputStream(clientSocket.getInputStream());
			DataOutputStream outStream = new DataOutputStream(clientSocket.getOutputStream());
			
			String type = receiveType(inStream, outStream);
			
			receiveFile(type, inStream);
        	
        	try {
	        	if (type.equals(Strings.STUDENT)) {
        			Student s = studentHandler.unmarshal(Paths.STUDENT_XML_SERVER);
        			System.out.println(s.toString());	
				}else {
					Professor p = professorHandler.unmarshal(Paths.PROFESSOR_XML_SERVER);
					System.out.println(p.toString());
				}
	        	
	        	System.out.println("Sending feedback");
	        	outStream.writeUTF("received");
	        	
	        	Server.cancel();
	        	
        	} catch (ValidationException e) {
	        	System.out.println("Sending feedback: XML was malformed");
	        	outStream.writeUTF("XML was malformed: " + e.getMessage()); 
				
			} catch (JAXBException e) {
				logger.error(Strings.JAXB_EXCEPTION);
				
			} finally {
	        	inStream.close();
	        	outStream.close();
	        	clientSocket.close();
			}				
			
		} catch (FileNotFoundException e) {
			logger.error(Strings.FILE_NOT_FOUND_EXCEPTION);
			
		} catch (IOException e) {
			logger.error(Strings.IO_EXCEPTION);
		} finally {
			
		}
	}
	
	private String receiveType(DataInputStream inStream, DataOutputStream outStream) {
		String type = "";
		try {
			type = inStream.readUTF();
			System.out.println("Type: " + type);
			outStream.writeUTF("ok");
			
		} catch (IOException e) {
			logger.error(Strings.IO_EXCEPTION);
		}
		return type;
	}
	
	private void receiveFile(String type, DataInputStream inStream) throws IOException {
		OutputStream out = null;
		byte[] bytes = new byte[1000];
        int count;
        
        try {
			if (type.equals(Strings.STUDENT)) {
				out = new FileOutputStream(Paths.STUDENT_XML_SERVER.toString());				
			} else {
				out = new FileOutputStream(Paths.PROFESSOR_XML_SERVER.toString());								
			}
	    	
	    	while ((count = inStream.read(bytes)) > 0) {
	    		out.write(bytes, 0, count);
	    	}	    	
        } finally {
			try {
				if(out != null) out.close();
			} catch (IOException e) {
				logger.error(Strings.IO_EXCEPTION);
			}
		}
	}
}
