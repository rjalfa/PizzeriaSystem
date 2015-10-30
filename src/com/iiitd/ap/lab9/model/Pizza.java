package com.iiitd.ap.lab9.model;

import java.io.Serializable;

/**
 * 
 * @author Rounaq jhunjhunu Wala | 2014089
 * @author Shrey Bagroy	| 2014099
 *
 */
public class Pizza implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5610519011419878317L;
	private String toppings;
	private String size;
	
	public Pizza(String toppings, String size)
	{
		this.toppings = toppings;
		this.size = size;
	}
	
	public String getToppings() {
		return toppings;
	}

	public void setToppings(String toppings) {
		this.toppings = toppings;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}
}
