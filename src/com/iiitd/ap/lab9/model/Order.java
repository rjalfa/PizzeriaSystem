package com.iiitd.ap.lab9.model;

import java.util.HashMap;

public class Order {
	private int id;
	private HashMap<Pizza,Integer> orders = new HashMap<>();
	private Customer customer = new Customer();
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
}
