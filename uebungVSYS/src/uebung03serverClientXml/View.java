package uebung03serverClientXml;

import java.awt.Checkbox;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import util.Logger;

public class View {

	private static Logger logger = Logger.getInstance();
	public String decision;

	public View(String decision) throws IOException{
		this.decision = decision;
		this.checkDecision(decision);
	}

	private void checkDecision(String decision) throws IOException{

		if(decision.equalsIgnoreCase("s")){
			this.getStudentData();
		}
		if (decision.equalsIgnoreCase("p")) {
			this.getProfessorData();
		}else{

			logger.info("Your input was incorrect. Please enter 's' for student's details and "
					+ "'p' for professor's details. Then hit 'enter'." );
			InputStreamReader isr = new InputStreamReader(System.in);
			BufferedReader br = new BufferedReader(isr);
			this.decision = br.readLine();
			checkDecision(this.decision);
		}
	}

	private void getProfessorData() {
		try{
			Professor p = new Professor();
			Address a = new Address();

			InputStreamReader isr = new InputStreamReader(System.in);
			BufferedReader br = new BufferedReader(isr);
			logger.info("Please enter the first name: ");
			p.setName(br.readLine());
			logger.info("Please enter the last name: ");
			p.setSurname(br.readLine());
			logger.info("Please enter street name: ");
			a.setStreet(br.readLine());
			logger.info("Please enter house number: ");
			a.setHouseNumber(Integer.parseInt(br.readLine()));
			logger.info("Please enter postal code: ");
			a.setPostCode(Integer.parseInt(br.readLine()));
			logger.info("Please enter the city: ");
			a.setCity(br.readLine());
			logger.info("Please enter personnel number: ");
			p.setPersonnelNumber(Integer.parseInt(br.readLine()));
			logger.info("Please enter faculty: ");
			p.setFaculty(br.readLine());
			p.setAddress(a);
			
			HandlerProfessor handlerProfessor = new HandlerProfessor();
			handlerProfessor.marshal(p, Paths.XML_PROFESSOR);
			
		}catch(IOException e){
			e.printStackTrace();
		}
	}

	private void getStudentData() {
		try{
			Student s = new Student();
			Address a = new Address();
			InputStreamReader isr = new InputStreamReader(System.in);
			BufferedReader br = new BufferedReader(isr);

			logger.info("Please enter the first name: ");
			s.setName(br.readLine());
			logger.info("Please enter the last name: ");
			s.setSurname(br.readLine());
			logger.info("Please enter street name: ");
			a.setStreet(br.readLine());
			logger.info("Please enter house number: ");
			a.setHouseNumber(Integer.parseInt(br.readLine()));
			logger.info("Please enter postal code: ");
			a.setPostCode(Integer.parseInt(br.readLine()));
			logger.info("Please enter the city: ");
			a.setCity(br.readLine());
			logger.info("Please enter student number: ");
			s.setStudentNumber(Integer.parseInt(br.readLine()));
			logger.info("Please enter your program: ");
			s.setProgram(br.readLine());
			logger.info("Please enter the semester: ");
			s.setSemester(Integer.parseInt(br.readLine()));
			s.setAddress(a);
		
			HandlerStudent handlerStudent = new HandlerStudent();
			handlerStudent.marshal(s, Paths.XML_STUDENT);

		}catch(IOException e){
			e.printStackTrace();
		}
	}
}
