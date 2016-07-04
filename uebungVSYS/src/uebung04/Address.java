package uebung04;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Serializable class for Address for XML generation
 * @author Bianca Ploch
 */
@XmlType(propOrder = {"street", "houseNumber", "postCode", "city"})
public class Address implements Serializable {
	
	private String street;
	private int houseNumber;
	private int postCode;
	private String city;
	
	
	/**
	 * Empty constructor
	 */
	public Address() {}
	
	/**
	 * Getter for street
	 * @return The street
	 */
	public String getStreet() {
		return street;
	}
	
	/**
	 * Setter for street
	 * @param street The street
	 */
	@XmlElement
	public void setStreet(String street) {
		this.street = street;
	}
	
	/**
	 * Getter for house number
	 * @return The house number
	 */
	public int getHouseNumber() {
		return houseNumber;
	}
	
	/**
	 * Setter for house number
	 * @param houseNumber The house number
	 */
	@XmlElement
	public void setHouseNumber(int houseNumber) {
		this.houseNumber = houseNumber;
	}
	
	/**
	 * Getter for post code
	 * @return The post code
	 */
	public int getPostCode() {
		return postCode;
	}
	
	/**
	 * Setter for post code
	 * @param postCode The post code
	 */
	@XmlElement
	public void setPostCode(int postCode) {
		this.postCode = postCode;
	}
	
	/**
	 * Getter for city
	 * @return The city name
	 */
	public String getCity() {
		return city;
	}
	
	/**
	 * Setter for city
	 * @param city The city name
	 */
	@XmlElement
	public void setCity(String city) {
		this.city = city;
	}

	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "\n [" + "street=" + street 
				+ "\n houseNumber=" + houseNumber 
				+ "\n postCode=" + postCode 
				+ "\n city=" + city + "] \n";
	}
}
