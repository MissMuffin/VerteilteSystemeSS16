package uebung04;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.time.LocalDate;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * Serializable class for Professor for XML generation
 * @author Bianca Ploch
 */
@XmlRootElement(name="Professor")
public class Professor extends Human {

	private LocalDate birthdate;
	private int personnelNumber;
	private String faculty;
	
	
	/**
	 * Empty constructor
	 */
	public Professor() {}
	
	/**
	 * Getter for birth date
	 * @return The birth date
	 */
	public LocalDate getBirthdate() {
		return birthdate;
	}
	
	/**
	 * Setter for birth date
	 * @param birthdate The birth date
	 */
	@XmlElement
	@XmlJavaTypeAdapter(XMLDateAdapter.class)
	public void setBirthdate(LocalDate birthdate) {
		this.birthdate = birthdate;
	}
	
	/**
	 * Getter for personnel number
	 * @return The personnel number
	 */
	public int getPersonnelNumber() {
		return personnelNumber;
	}
	
	/**
	 * Setter for personnel number
	 * @param personnelNumber The personnel number
	 */
	@XmlElement
	public void setPersonnelNumber(int personnelNumber) {
		this.personnelNumber = personnelNumber;
	}
	
	/**
	 * Getter for factuly
	 * @return The faculty
	 */
	public String getFaculty() {
		return faculty;
	}
	
	/**
	 * Setter for faculty
	 * @param faculty The faculty
	 */
	@XmlElement
	public void setFaculty(String faculty) {
		this.faculty = faculty;
	}
	
	/* (non-Javadoc)
	 * @see uebung04.Human#toString()
	 */
	@Override
	public String toString() {
		return "Professor"
				+ "\n [birthdate=" + birthdate 
				+ "\n  personnelNumber=" + personnelNumber 
				+ "\n  faculty=" + faculty
				+ "\n" + super.toString() + "] \n";
	}

	@Override
	public Human read(Path path) {
		 Human result = null;
		 try {
		     ObjectInputStream oi = new ObjectInputStream(new FileInputStream(Path.PROFESSOR_SER_SERVER.toString()));
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
