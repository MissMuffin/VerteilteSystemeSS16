package uebung03serverClientXml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.time.LocalDate;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement(name="Professor")
public class Professor extends Human {

	private LocalDate birthdate;
	private int personnelNumber;
	private String faculty;
	
	
	public Professor() {}
	
	public LocalDate getBirthdate() {
		return birthdate;
	}
	
	@XmlElement
	@XmlJavaTypeAdapter(XMLDateAdapter.class)
	public void setBirthdate(LocalDate birthdate) {
		this.birthdate = birthdate;
	}
	
	public int getPersonnelNumber() {
		return personnelNumber;
	}
	
	@XmlElement
	public void setPersonnelNumber(int personnelNumber) {
		this.personnelNumber = personnelNumber;
	}
	
	public String getFaculty() {
		return faculty;
	}
	
	@XmlElement
	public void setFaculty(String faculty) {
		this.faculty = faculty;
	}
	
	@Override
	public String toString() {
		return "Professor"
				+ "\n [birthdate=" + birthdate 
				+ "\n  personnelNumber=" + personnelNumber 
				+ "\n  faculty=" + faculty
				+ "\n" + super.toString() + "] \n";
	}

	@Override
	public Human read(Paths path) {
		 Human result = null;
		 try {
		     ObjectInputStream oi = new ObjectInputStream(new FileInputStream(Paths.PROFESSOR_SER_SERVER.toString()));
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
