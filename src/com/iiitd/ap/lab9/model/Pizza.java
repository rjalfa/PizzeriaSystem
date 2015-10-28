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
	private int size;
	
	public Pizza(String toppings, int size)
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

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
}
