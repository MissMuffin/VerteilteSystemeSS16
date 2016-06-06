package uebung03serverClientXml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

public class Human implements Serializable {

	private String name;
	private String surname;
	private Address address;
	
	
	public String getName() {
		return name;
	}
	
	@XmlElement
	public void setName(String name) {
		this.name = name;
	}
	
	public String getSurname() {
		return surname;
	}
	
	@XmlElement
	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Address getAddress() {
		return address;
	}

	@XmlElement
	public void setAddress(Address address) {
		this.address = address;
	}
	
	public void write(String path) {
		try{			   
			FileOutputStream fout = new FileOutputStream(path);
			ObjectOutputStream oos = new ObjectOutputStream(fout);   
			oos.writeObject(this);
			oos.close();
			System.out.println(getClass().getName() + " :done");
			   
		   }catch(Exception e){
			   e.printStackTrace();
		   }
	}
	
	public Human read(String path) {
		Human result = null;
		try {
			ObjectInputStream oi = new ObjectInputStream(new FileInputStream(Paths.SER_STUDENT));
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

	@Override
	public String toString() {
		return "\n [" + "name=" + name 
				+ "\n surname=" + surname 
				+ "\n \n" + address.toString() + "] \n";
	}	
}
