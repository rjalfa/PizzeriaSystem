package com.iiitd.ap.lab9.model;

/**
 * 
 * @author Rounaq jhunjhunu Wala | 2014089
 * @author Shrey Bagroy	| 2014099
 *
 */
public class Pizza
{
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
