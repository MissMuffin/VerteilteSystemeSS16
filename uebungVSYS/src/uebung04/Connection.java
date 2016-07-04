package uebung04;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

import javax.xml.bind.JAXBException;
import javax.xml.bind.ValidationException;

/**
 * Class that handles the server side logic: communicates with client,
 * handles XML validation events and saves correct data to hard drive as .SER file.
 * Communication with client adheres to following protocol: first the Human type of the data to be sent is 
 * transmitted as String by the client, the server sends an acknowledgement, the data is transfered from the client
 * and saved by the server as an XML file. The server then unmarshalls the XML and validates it; is the validation successful
 * the object is saved as .SER file on hard drive. If validation fails the server sends an error message to the client who then 
 * reopens the communication and initiates a new communication with new user input.
 * @author Bianca Ploch
 */
public class Connection {

	private Socket clientSocket;	
	private StudentHandler studentHandler = new StudentHandler();
	private ProfessorHandler professorHandler= new ProfessorHandler();
	private Logger logger = Logger.getInstance();
	

	/**
	 * Constructor
	 * @param clientSocket The client to communicate with
	 */
	public Connection(Socket clientSocket){
		this.clientSocket = clientSocket;
	}

	/**
	 * Runs communication according to protocol. Runs {@link #receiveType(DataInputStream, DataOutputStream)} 
	 * and {@link #receiveFile(String, DataInputStream)}, then validates received XML against schema. On validation success
	 * the object from the unmarshalling of the XML is saved to hard drive as .SER and the server shutdown method {@link Server#cancel()} 
	 * is called. If validation fails an error message is 
	 * sent to the client and the server continues running.
	 */
	public void run() {
		        
        try {        	
        	DataInputStream inStream = new DataInputStream(clientSocket.getInputStream());
			DataOutputStream outStream = new DataOutputStream(clientSocket.getOutputStream());
			
			String type = receiveType(inStream, outStream);
			
			receiveFile(type, inStream);
        	
        	try {
	        	if (type.equals(Strings.STUDENT)) {
        			Student s = studentHandler.unmarshal(Path.STUDENT_XML_SERVER);
        			System.out.println(s.toString());
        			s.write(Path.STUDENT_SER_SERVER);
				}else {
					Professor p = professorHandler.unmarshal(Path.PROFESSOR_XML_SERVER);
					System.out.println(p.toString());
					p.write(Path.PROFESSOR_SER_SERVER);
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
	
	/**
	 * Receives the type of the data that will follow from the client.
	 * @param inStream In stream from client
	 * @param outStream Out stream to client
	 * @return
	 */
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
	
	/**
	 * Receives the XML file from client and saves it to hard drive
	 * @param type Type of the data: either Student or Professor
	 * @param inStream In stream from client
	 * @throws IOException 
	 */
	private void receiveFile(String type, DataInputStream inStream) throws IOException {
		OutputStream out = null;
		byte[] bytes = new byte[1000];
        int count;
        
        try {
			if (type.equals(Strings.STUDENT)) {
				out = new FileOutputStream(Path.STUDENT_XML_SERVER.toString());				
			} else {
				out = new FileOutputStream(Path.PROFESSOR_XML_SERVER.toString());								
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
