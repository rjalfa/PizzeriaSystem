package com.iiitd.ap.lab9;

import java.util.ArrayList;

import com.iiitd.ap.lab9.model.Customer;
import com.iiitd.ap.lab9.model.Order;

public final class Database {
	static private ArrayList<Customer> users = new ArrayList<>();
	static private ArrayList<Order> orders = new ArrayList<>();
	
	public static void addOrder(Order order)
	{
		orders.add(order);
	}
	
	public static void addUser(Customer user)
	{
		users.add(user);
	}
	
	public static Order newOrder()
	{
		Order order = new Order();
		order.setId(orders.size()+1);
		return order;
	}
	
	public static Order createOrder(Order order)
	{
		
		return order;
	}
	
}
