package com.iiitd.ap.lab9.model;

import java.io.Serializable;
import java.util.HashMap;

public class Order implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2060839169913042585L;
	private long id;
	private HashMap<Pizza,Integer> orders = new HashMap<>();
	private Customer customer = new Customer();
	private int status;
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public HashMap<Pizza,Integer> getOrders() {
		return orders;
	}
	public void setOrders(HashMap<Pizza,Integer> orders) {
		this.orders = orders;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	public String toString()
	{
		String s = "";
		for(Pizza p : orders.keySet()) s+= p.getToppings()+"("+p.getSize()+"):"+orders.get(p)+";";
		if(s.trim().equals("")) return "Nothing Ordered";
		return s;
	}
}
