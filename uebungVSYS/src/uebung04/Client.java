package uebung04;

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

/**
 * Class that implements client logic: validating user input and communication with server.
 * Contains main method for starting client.
 * For description of the communication protocol see {@link Connection}
 * @author Bianca Ploch
 */
public class Client {
	
	private static final int SERVER_PORT = 7896;
	
	private static InputStreamReader reader = new InputStreamReader(System.in);
	private static BufferedReader buffer = new BufferedReader(reader);
	private static Socket socket;
	private static StudentHandler studentHandler = new StudentHandler(); 
	private static ProfessorHandler professorHandler = new ProfessorHandler();
	private static Logger logger = Logger.getInstance();
	private static boolean isInputFinished = false;
	
	/**
	 * Main method that queries user for input type: student, professor or bad.
	 * The first to will lead to calling {@link Client#startStudentInput()} and {@link #startProfessorInput()}
	 * and starting the input process for the creation of a Student or a Professor object, which is then 
	 * marshalled and sent to the server in accordance with the protocol. 
	 * If bad is input by the user a malformed XML file of type student is sent to the server
	 * to the server to showcase server behavior: the server will notify the client of
	 * malformed XML and the client will start the user input process again by calling {@link #startStudentInput()}.
	 * @param args No arguments used
	 */
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
				startStudentInput();
				break;
				
			case Strings.PROFESSOR:				
				startProfessorInput();
				break;
			case Strings.BAD:
				sendFile(Path.BAD_STUDENT_XML, Strings.STUDENT);
				if (!isInputFinished) startStudentInput();
		}
	}
	
	/**
	 * Starts the input for the creation of a Student object and 
	 * the sending of the mashalled XML file to the server. 
	 * Continues to loop until validation on serverside was successful.
	 */
	private static void startStudentInput() {
		while (!isInputFinished) {
			Student s = handleStudent();
			log("Saving to XML...");
			studentHandler.marshal(s, Path.STUDENT_XML_CLIENT);
			log("Saved.");
			sendFile(Path.STUDENT_XML_CLIENT, Strings.STUDENT);
		}
	}
	
	/**
	 * Starts the input for the creation of a Professor object and 
	 * the sending of the mashalled XML file to the server. 
	 * Continues to loop until validation on serverside was successful.
	 */
	private static void startProfessorInput() {
		while (!isInputFinished) {
			Professor p = handleProfessor();
			log("Saving to XML...");
			professorHandler.marshal(p, Path.PROFESSOR_XML_CLIENT);
			log("Saved.");
			sendFile(Path.PROFESSOR_XML_CLIENT, Strings.PROFESSOR);
		}
	}
		
	/**
	 * Executes communication protocol with server.
	 * Sends information about type, Student or Professor and sends 
	 * marshalled XML file to server. Then it waits for feedback from the server 
	 * whether validation was successful or not. In case validation was successful,
	 * a success message will be printed and the client will shut down. 
	 * In case of an unsuccessful validation an error message it printed for the user
	 * and the communication streams closed. While validation is unsuccessful this
	 * method will continue to be run by {@link #startProfessorInput()} and {@link #startProfessorInput()}.
	 * @param path
	 * @param type
	 */
	private static void sendFile(Path path, String type) {
		
		log("Sending to server...");
		
		//create file
		File file = new File(path.toString());
		DataInputStream inStream = null;
		DataOutputStream outStream = null;
		InputStream in = null;

		//open client socket
		try {
			socket = new Socket("localhost", SERVER_PORT);
			
			//informing server about human type
			inStream = new DataInputStream(socket.getInputStream());
			outStream = new DataOutputStream(socket.getOutputStream());
			outStream.writeUTF(type);
			
			String acknowledgement = "";
			while(acknowledgement.equals("")) {
				acknowledgement = inStream.readUTF();
			}
			
			in = new FileInputStream(file);
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
			if (data.split(" ")[0].equals("XML")) {
				log("Server received incomplete data. Please enter information for new " + type + " again.");
			} else {
				System.out.println(data);
				isInputFinished = true;
			}
			
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
				if (in != null) in.close();
				if (inStream != null) inStream.close();
				
			} catch (IOException e2) {
				logger.error(Strings.IO_EXCEPTION);
			}
		}
	}
	
	/**
	 * Validates the user input for int, continues to request
	 * new input if input can't be pared to int.
	 * @return The number the user input
	 */
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
	
	/**
	 * Validates user input for non-empty String, continues to request new
	 * user input if input is determined to be empty String.
	 * @return The String the user input
	 */
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
	
	/**
	 * Handles the input of Student related information by the user.
	 * Uses validation methods {@link #validateIntegerInput()}and 
	 * {@link #validateNonEmptyStringInput()} for validating input.
	 * @return A Student object with complete and validated fields
	 */
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
	
	/**
	 * Handles the input of Professor related information by the user.
	 * Uses validation methods {@link #validateIntegerInput()}, 
	 * {@link #validateLocalDateInput()} and 
	 * {@link #validateNonEmptyStringInput()} for validating input.
	 * @return A Professor object with complete and validated fields
	 */
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
	
	/**
	 * Validates user input for LocalDate, continues to request new
	 * user input if input does not parse to a valid LocalDate..
	 * @return The LocalDate input by the user
	 */
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
	
	/**
	 * Reads the next line the user enters on the console.
	 * @return The String the user input
	 */
	private static String readLine() {
		try {
			return buffer.readLine();
		} catch (IOException e1) {
			logger.error(Strings.IO_EXCEPTION);
		}
		return null;
	}
	
	/**
	 * Prints message to console. Neither {@link Logger#error(String)} nor
	 *  {@link Logger#info(String)} fits, therefore own method is used.
	 * @param message The message to be printed on the console
	 */
	private static void log(String message) {
		System.out.println(message);
	}
}
