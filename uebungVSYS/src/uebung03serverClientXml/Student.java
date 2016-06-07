package uebung03serverClientXml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Student")
public class Student extends Human {

	private int studentNumber;
	private String program;
	private int semester;
		
		
	public Student() {}
	
	public int getStudentNumber() {
		return studentNumber;
	}
	
	@XmlElement
	public void setStudentNumber(int studentNumber) {
		this.studentNumber = studentNumber;
	}
	
	public String getProgram() {
		return program;
	}
	
	@XmlElement
	public void setProgram(String program) {
		this.program = program;
	}
	
	public int getSemester() {
		return semester;
	}
	
	@XmlElement
	public void setSemester(int semester) {
		this.semester = semester;
	}

	@Override
	public String toString() {
		return "Student"
				+ "\n [" + "studentNumber=" + studentNumber 
				+ "\n  program=" + program 
				+ "\n  semester=" + semester
				+ "\n" + super.toString() + "] \n";
	}

	@Override
	public Human read(Paths path) {
		Human result = null;
		 try {
		     ObjectInputStream oi = new ObjectInputStream(new FileInputStream(Paths.STUDENT_SER_SERVER.toString()));
		     result = (Human) oi.readObject();
		     oi.close();
		 } catch (FileNotFoundException e) {
		     e.printStackTrace();
		 } catch (IOException e) {
		     e.printStackTrace();
		 } catch (ClassNotFoundException e) {
		     e.printStackTrace();
		 }
		 return result;
	}	
	
	
}
