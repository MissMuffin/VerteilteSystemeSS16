package uebung04;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;

/**
 * Superclass to both Student and Professor. Defines common fields such as name, surname
 *  and address.
 * @author Bianca Ploch
 */
public abstract class Human implements Serializable {
	
	/**
	 * Method for reading the serialized file from hard drive.
	 * Implemented by sub classes.
	 * 
	 * @param path The path of the serialized file
	 * @return The object of super type Human from the serialized file
	 */
	public abstract Human read(Path path);

	private String name;
	private String surname;
	private Address address;
	
	
	/**
	 * Getter for name
	 * @return The name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Setter for name
	 * @param name The name
	 */
	@XmlElement
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Getter for surname
	 * @return The surname
	 */
	public String getSurname() {
		return surname;
	}
	
	/**
	 * Setter for surname
	 * @param surname The surname
	 */
	@XmlElement
	public void setSurname(String surname) {
		this.surname = surname;
	}

	/**
	 * Getter for address
	 * @return The address
	 */
	public Address getAddress() {
		return address;
	}

	/**
	 * Setter for address
	 * @param address The address
	 */
	@XmlElement
	public void setAddress(Address address) {
		this.address = address;
	}
	
	/**
	 * Method for saving object to hard drive as .SER file
	 * @param path The path where the file is to be saved
	 */
	public void write(Path path) {
		try{			   
			FileOutputStream fout = new FileOutputStream(path.toString());
			ObjectOutputStream oos = new ObjectOutputStream(fout);   
			oos.writeObject(this);
			oos.close();
			System.out.println(getClass().getName() + ": finished saving SER");
			   
		   }catch(IOException e){
			   Logger.getInstance().error(Strings.IO_EXCEPTION);
		   }
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "\n [" + "name=" + name 
				+ "\n surname=" + surname 
				+ "\n \n" + address.toString() + "] \n";
	}	
}
