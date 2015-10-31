package com.iiitd.ap.lab9.model;

import java.io.Serializable;
/**
 * 
 * @author Rounaq jhunjhunu Wala | 2014089
 * @author Shrey Bagroy	| 2014099
 *
 */
public class Address implements Comparable<Address>,Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7573318374269963968L;
	private String street;
	private String city;
	private String pIN;
	
	public Address(String a,String b,String c)
	{
		this.street = a;
		this.city = b;
		this.pIN = c;
	}
	
	public String getStreet() {
		return street;
	}


	public void setStreet(String street) {
		this.street = street;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getPIN() {
		return pIN;
	}


	public void setPIN(String pIN) {
		this.pIN = pIN;
	}


	@Override
	public int compareTo(Address b) {
		return (b.street.compareTo(this.street))*(b.city.compareTo(this.city))*(b.pIN.compareTo(this.pIN));
	}
	
}
