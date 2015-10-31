package com.iiitd.ap.lab9.model;

import java.io.Serializable;
/**
 * 
 * @author Rounaq jhunjhunu Wala | 2014089
 * @author Shrey Bagroy	| 2014099
 *
 */
public class Customer implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -9054236499911166969L;
	private int id;
	private String name;
	private Address address;
	private String mobileNo;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
}
