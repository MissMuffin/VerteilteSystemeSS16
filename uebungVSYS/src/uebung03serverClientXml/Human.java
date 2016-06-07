package uebung03serverClientXml;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;

public abstract class Human implements Serializable {
	
	public abstract Human read(Paths path);

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
	
	public void write(Paths path) {
		try{			   
			FileOutputStream fout = new FileOutputStream(path.toString());
			ObjectOutputStream oos = new ObjectOutputStream(fout);   
			oos.writeObject(this);
			oos.close();
			System.out.println(getClass().getName() + " :done");
			   
		   }catch(Exception e){
			   e.printStackTrace();
		   }
	}
	
	@Override
	public String toString() {
		return "\n [" + "name=" + name 
				+ "\n surname=" + surname 
				+ "\n \n" + address.toString() + "] \n";
	}	
}
