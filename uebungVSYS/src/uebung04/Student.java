package uebung04;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Serializable class for Student for XML generation
 * @author Bianca Ploch
 */
@XmlRootElement(name="Student")
public class Student extends Human {

	private int studentNumber;
	private String program;
	private int semester;
		
		
	/**
	 * Empty constructor
	 */
	public Student() {}
	
	/**
	 * Getter for student number
	 * @return The student number
	 */
	public int getStudentNumber() {
		return studentNumber;
	}
	
	/**
	 * Setter for student number
	 * @param studentNumber The student number
	 */
	@XmlElement
	public void setStudentNumber(int studentNumber) {
		this.studentNumber = studentNumber;
	}
	
	/**
	 * Getter for program
	 * @return The program
	 */
	public String getProgram() {
		return program;
	}
	
	/**
	 * Setter for program
	 * @param program The program
	 */
	@XmlElement
	public void setProgram(String program) {
		this.program = program;
	}
	
	/**
	 * Getter for semester
	 * @return The semester
	 */
	public int getSemester() {
		return semester;
	}
	
	/**
	 * Setter for semester
	 * @param semester The semester
	 */
	@XmlElement
	public void setSemester(int semester) {
		this.semester = semester;
	}

	/* (non-Javadoc)
	 * @see uebung04.Human#toString()
	 */
	@Override
	public String toString() {
		return "Student"
				+ "\n [" + "studentNumber=" + studentNumber 
				+ "\n  program=" + program 
				+ "\n  semester=" + semester
				+ "\n" + super.toString() + "] \n";
	}

	/* (non-Javadoc)
	 * @see uebung04.Human#read(uebung04.Path)
	 */
	@Override
	public Human read(Path path) {
		Human result = null;
		 try {
		     ObjectInputStream oi = new ObjectInputStream(new FileInputStream(Path.STUDENT_SER_SERVER.toString()));
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
