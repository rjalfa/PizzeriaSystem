package com.iiitd.ap.lab9;
import java.util.ArrayList;
import java.util.HashMap;

import com.iiitd.ap.lab9.model.Address;

import com.iiitd.ap.lab9.model.Customer;
import com.iiitd.ap.lab9.model.Order;
import com.iiitd.ap.lab9.model.Pizza;

public final class Database {
	static private ArrayList<Customer> users = new ArrayList<>();
	static private ArrayList<Order> orders = new ArrayList<>();
	
	public static void addUser(Customer user)
	{
		users.add(user);
	}
	
	public static Order newOrder()
	{
		Order order = new Order();
		order.setId(orders.size()+1);
		order.setStatus(0);
		return order;
	}
	
	public static Order finaliseOrder(Order order,String[] s)
	{
		Customer u = new Customer();
		u.setName(s[0]);
		u.setMobileNo(s[1]);
		Address a = new Address(s[2],s[3],s[4]);
		u.setAddress(a);
		order.setCustomer(u);
		return order;
	}
	
	public static Order createOrder(Order order,HashMap<String,String[]> h)
	{
		HashMap<Pizza,Integer> H = order.getOrders();
		for(String key : h.keySet())
		{
			String[] a = h.get(key);
			H.put(new Pizza(a[2],a[0]),Integer.parseInt(a[1]));
		}
		return order;
	}
	
	public static Order trackOrder(int id)
	{
		if(id > orders.size()) return null;
		return orders.get(id-1);
	}
	
	public static Customer queryUser(String phone)
	{
		for(Customer i : users)
		{
			if(i.getMobileNo().equals(phone)) return i;
		}
		return null;
	}
	
	public static void updateOrderState(int id)
	{
		if(id > orders.size()) return;
		Order order = orders.get(id-1);
		if(order.getStatus() == 6) return;
		order.setStatus(order.getStatus() + 1);
	}
	
	public static ArrayList<Order> getOrders()
	{
		return orders;
	}
	
	public static ArrayList<Order> getCompletedOrders()
	{
		ArrayList<Order> arr = new ArrayList<>();
		for(Order i : orders) if(i.getStatus() == 6) arr.add(i);
		return arr;
	}
	
	public static ArrayList<Order> getIncompleteOrders()
	{
		ArrayList<Order> arr = new ArrayList<>();
		for(Order i : orders) if(i.getStatus() != 6) arr.add(i);
		return arr;
	}
	
	public static String stateVerbose(int x)
	{
		switch(x)
		{
			case 1:return "Order Placed";
			case 2:return "Preparation";
			case 3:return "Bake";
			case 4:return "Quality Check";
			case 5:return "Out for delivery";
			case 6:return "Delivered";
		}
		return "";
	}
}
