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
import java.time.LocalDate;

import util.Logger;

public class Client {
	
	private static Logger logger = Logger.getInstance();
	private static final int SERVER_PORT = 7896;
	
	private static boolean cancel = false;
	private static InputStreamReader reader = new InputStreamReader(System.in);
	private static BufferedReader buffer = new BufferedReader(reader);
	private static String input;
	private static Socket socket;
	private static StudentHandler studentHandler = new StudentHandler(); 
	private static ProfessorHandler professorHandler = new ProfessorHandler(); 
	
	public static void main(String args[]) {
		log("Enter 'student' if you want to create a new student "
				+ "and 'professor'if you want to create a new professor");		
		
		switch (readLine()) {
		case "student":
			
			Student s = handleStudent();
			log("Saving to XML...");
			studentHandler.marshal(s, Paths.STUDENT_XML_CLIENT);
			log("Saved.");
			sendFile(Paths.STUDENT_XML_CLIENT);
			break;
			
		case "professor":
			
			Professor p = handleProfessor();
			professorHandler.marshal(p, Paths.PROFESSOR_XML_CLIENT);
			sendFile(Paths.PROFESSOR_XML_CLIENT);
			break;
		}
	}
		
	private static void sendFile(Paths path) {
		
		log("Sending to server...");
		
		//create file
		File file = new File(path.toString());

		//open client socket
		try {
			socket = new Socket("localhost", SERVER_PORT);
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
			
			out.close();
			in.close();
			

			
		} catch(UnknownHostException e){
			System.out.println("Sock: " + e.getMessage());
		} catch(EOFException e){
			System.out.println("EOF: " + e.getMessage());
		} catch(IOException e){
			System.out.println("IO: " + e.getMessage());
		}
		
		log("Sent.");
		handleFeedback();
	}
	
	private static void handleFeedback() {
		while(!cancel) {
			try{
				DataInputStream in = new DataInputStream(socket.getInputStream());
				DataOutputStream out = new DataOutputStream(socket.getOutputStream());
				out.writeUTF("Waiting for feedback");
				String data = in.readUTF();
				System.out.println(data);
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}
	
	private static Student handleStudent() {
		
		Student student = new Student();
		Address address = new Address();
		
		log("Enter name");
		student.setName(readLine());
		
		log("Enter surname");
		student.setSurname(readLine());
		
		log("Enter street name");
		address.setStreet(readLine());
		
		log("Enter house number");
		address.setHouseNumber(Integer.parseInt(readLine()));
		
		log("Enter postcode");
		address.setPostCode(Integer.parseInt(readLine()));
		
		log("Enter city");
		address.setCity(readLine());
		
		log("Enter program");
		student.setProgram(readLine());
		
		log("Enter semester");
		student.setSemester(Integer.parseInt(readLine()));
		
		log("Enter student number");
		student.setStudentNumber(Integer.parseInt(readLine()));
		
		student.setAddress(address);
		
		return student;
	}
	
	private static Professor handleProfessor() {
		
		Professor professor = new Professor();
		Address address = new Address();
		
		log("Enter name");
		professor.setName(readLine());
		
		log("Enter surname");
		professor.setSurname(readLine());
		
		log("Enter street name");
		address.setStreet(readLine());
		
		log("Enter house number");
		address.setHouseNumber(Integer.parseInt(readLine()));
		
		log("Enter postcode");
		address.setPostCode(Integer.parseInt(readLine()));
		
		log("Enter city");
		address.setCity(readLine());
		
		log("Enter faculty");
		professor.setFaculty(readLine());
		
		log("Enter personnel number");
		professor.setPersonnelNumber(Integer.parseInt(readLine()));
		
		log("Enter birth date in format: YYYY-MM-DD");
		professor.setBirthdate(LocalDate.parse(readLine()));
		
		professor.setAddress(address);
		
		return professor;
	}
	
	private static String readLine() {
		try {
			return buffer.readLine();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return null;
	}

	private static void cancel() {
		cancel = true;
	}
	
	private static void log(String message) {
		System.out.println(message);
	}
}
