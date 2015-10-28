package com.iiitd.ap.lab9.model;

import java.util.ArrayList;

public class Order {
	private int id;
	private ArrayList<Pizza> orders;
	private Customer customer;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public ArrayList<Pizza> getOrders() {
		return orders;
	}
	public void setOrders(ArrayList<Pizza> orders) {
		this.orders = orders;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
}
