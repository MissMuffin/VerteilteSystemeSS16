package uebung03serverClientXml;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.time.LocalDate;

import util.Logger;

public class Client {
	
	private static final int SERVER_PORT = 7896;
	
	private static InputStreamReader reader = new InputStreamReader(System.in);
	private static BufferedReader buffer = new BufferedReader(reader);
	private static Socket socket;
	private static StudentHandler studentHandler = new StudentHandler(); 
	private static ProfessorHandler professorHandler = new ProfessorHandler();
	private static Logger logger = Logger.getInstance();
	
	public static void main(String args[]) {
		log("Enter '"
				+ Strings.STUDENT
				+ "' if you want to create a new student "
				+ "\nand '"
				+ Strings.PROFESSOR
				+ "'if you want to create a new professor \nor"
				+ " enter '"
				+ Strings.BAD
				+ "' for sending a bad XML file to the server");		
		
		String input = readLine();
		while (!input.equals(Strings.STUDENT) 
				&& !input.equals(Strings.PROFESSOR)
				&& !input.equals(Strings.BAD)) {
			log("Incorrect input. Please enter '" + Strings.SAXB_EXCEPTION 
					+ "' or '" + Strings.PROFESSOR
					+ "' or '" + Strings.BAD + "'");
			input = readLine();
		}
		
		switch (input) {
			case Strings.STUDENT:				
				Student s = handleStudent();
				log("Saving to XML...");
				studentHandler.marshal(s, Paths.STUDENT_XML_CLIENT);
				log("Saved.");
				sendFile(Paths.STUDENT_XML_CLIENT, Strings.STUDENT);
				break;
				
			case Strings.PROFESSOR:				
				Professor p = handleProfessor();
				log("Saving to XML...");
				professorHandler.marshal(p, Paths.PROFESSOR_XML_CLIENT);
				log("Saved.");
				sendFile(Paths.PROFESSOR_XML_CLIENT, Strings.PROFESSOR);
				break;
			case "bad":
				sendFile(Paths.BAD_STUDENT_XML, Strings.STUDENT);
		}
	}
		
	private static void sendFile(Paths path, String type) {
		
		log("Sending to server...");
		
		//create file
		File file = new File(path.toString());

		//open client socket
		try {
			socket = new Socket("localhost", SERVER_PORT);
			
			//informing server about human type
			DataInputStream inStream = new DataInputStream(socket.getInputStream());
			DataOutputStream outStream = new DataOutputStream(socket.getOutputStream());
			outStream.writeUTF(type);
			
			String acknowledgement = "";
			while(acknowledgement.equals("")) {
				acknowledgement = inStream.readUTF();
			}
			
			InputStream in = new FileInputStream(file);
			OutputStream out = socket.getOutputStream();
			
			// Get the size of the file
			long length = file.length();
			byte[] bytes = new byte[(int)length];
			
			//send file
			int count;
			while ((count = in.read(bytes)) > 0) {
				out.write(bytes, 0, count);
			}
			socket.shutdownOutput();
			
			
			log("Sent.");
			log("Waiting for feedback");
			
			String data = inStream.readUTF();
			System.out.println(data);
			
			in.close();
			inStream.close();
			
		} catch(UnknownHostException e){
			logger.error(Strings.UNKNOWN_HOST_EXCEPTION);
			
		} catch(EOFException e){
			logger.error(Strings.EOF_EXCEPTION);
			
		} catch (FileNotFoundException e) {
			logger.error(Strings.FILE_NOT_FOUND_EXCEPTION);
			
		} catch(IOException e){
			logger.error(Strings.IO_EXCEPTION);
			
		} finally {
			try {
				if (socket != null) socket.close();				
			} catch (IOException e2) {
				logger.error(Strings.IO_EXCEPTION);
			}
		}
	}
	
	private static int validateIntegerInput() {
		int inputInt = 0;
		boolean validatedInput = false;
		while (!validatedInput) {
			try {
				inputInt = Integer.parseInt(readLine());
				validatedInput = true;
			} catch (Exception e) {
				log("Incorrect input. Please enter a number");
				continue;
			}			
		}
		return inputInt;
	}
	
	private static String validateNonEmptyStringInput() {
		String input = "";
		boolean validatedInput = false;
		while (!validatedInput) {
			input = readLine().trim();
			if (input.length() == 0) {
				log("Empty input. Please input something.");
				continue;
			}
			validatedInput = true;
		}
		return input;
	}
	
	private static Student handleStudent() {
		
		Student student = new Student();
		Address address = new Address();
		
		log("Enter name");
		student.setName(validateNonEmptyStringInput());
		
		log("Enter surname");
		student.setSurname(validateNonEmptyStringInput());
		
		log("Enter street name");
		address.setStreet(validateNonEmptyStringInput());
		
		log("Enter house number");
		address.setHouseNumber(validateIntegerInput());			
		
		log("Enter postcode");
		address.setPostCode(validateIntegerInput());
		
		log("Enter city");
		address.setCity(validateNonEmptyStringInput());
		
		log("Enter program");
		student.setProgram(validateNonEmptyStringInput());
		
		log("Enter semester");
		student.setSemester(validateIntegerInput());
		
		log("Enter student number");
		student.setStudentNumber(validateIntegerInput());
		
		student.setAddress(address);
		
		return student;
	}
	
	private static Professor handleProfessor() {
		
		Professor professor = new Professor();
		Address address = new Address();
		
		log("Enter name");
		professor.setName(validateNonEmptyStringInput());
		
		log("Enter surname");
		professor.setSurname(validateNonEmptyStringInput());
		
		log("Enter street name");
		address.setStreet(validateNonEmptyStringInput());
		
		log("Enter house number");
		address.setHouseNumber(validateIntegerInput());
		
		log("Enter postcode");
		address.setPostCode(validateIntegerInput());
		
		log("Enter city");
		address.setCity(validateNonEmptyStringInput());
		
		log("Enter faculty");
		professor.setFaculty(validateNonEmptyStringInput());
		
		log("Enter personnel number");
		professor.setPersonnelNumber(validateIntegerInput());
		
		log("Enter birth date in format: YYYY-MM-DD");
		professor.setBirthdate(validateLocalDateInput());
		
		professor.setAddress(address);
		
		return professor;
	}
	
	private static LocalDate validateLocalDateInput() {
		LocalDate input = null;
		boolean validatedInput = false;
		
		while (!validatedInput) {
			try {input = LocalDate.parse(readLine());
			validatedInput = true;
			} catch (Exception e) {
				log("Incorrect input. Please enter a date in the specified format YYYY-MM-DD");
				continue;
			}
		}
		return input;
	}
	
	private static String readLine() {
		try {
			return buffer.readLine();
		} catch (IOException e1) {
			logger.error(Strings.IO_EXCEPTION);
		}
		return null;
	}
	
	private static void log(String message) {
		System.out.println(message);
	}
}
