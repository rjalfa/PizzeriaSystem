package com.iiitd.ap.lab9.model;

public class Address implements Comparable<Address> {

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
