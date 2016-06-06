package uebung03serverClientXml;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"street", "houseNumber", "postCode", "city"})
public class Address implements Serializable {
	
	private String street;
	private int houseNumber;
	private int postCode;
	private String city;
	
	
	public Address() {}
	
	public String getStreet() {
		return street;
	}
	
	@XmlElement
	public void setStreet(String street) {
		this.street = street;
	}
	
	public int getHouseNumber() {
		return houseNumber;
	}
	
	@XmlElement
	public void setHouseNumber(int houseNumber) {
		this.houseNumber = houseNumber;
	}
	
	public int getPostCode() {
		return postCode;
	}
	
	@XmlElement
	public void setPostCode(int postCode) {
		this.postCode = postCode;
	}
	
	public String getCity() {
		return city;
	}
	
	@XmlElement
	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "\n [" + "street=" + street 
				+ "\n houseNumber=" + houseNumber 
				+ "\n postCode=" + postCode 
				+ "\n city=" + city + "] \n";
	}
}
