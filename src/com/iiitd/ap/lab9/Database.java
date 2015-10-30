package com.iiitd.ap.lab9;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import com.iiitd.ap.lab9.model.Address;
import com.iiitd.ap.lab9.model.Customer;
import com.iiitd.ap.lab9.model.Order;
import com.iiitd.ap.lab9.model.Pizza;

public final class Database {
	static private Vector<Customer> users = new Vector<>();
	static private Vector<Order> orders = new Vector<>();
	
	public static void addUser(Customer user)
	{
		users.add(user);
	}
	
	public static Order newOrder()
	{
		System.out.println("[MESSAGE] New Order object Requested.");
		Order order = new Order();
		order.setId(""+System.currentTimeMillis()%1000000000);
		order.setStatus(1);
		return order;
	}
	
	public static Order finaliseOrder(Order order,String[] s)
	{
		System.out.printf("[MESSAGE] Order ID %s sent for Commit\n",order.getId());
		Customer u = new Customer();
		u.setName(s[0]);
		u.setMobileNo(s[1]);
		Address a = new Address(s[2],s[3],s[4]);
		u.setAddress(a);
		order.setCustomer(u);
		orders.add(order);
		return order;
	}
	
	public static Order createOrder(Order order,HashMap<String,String[]> h)
	{
		System.out.printf("[MESSAGE] Order ID %s update members\n",order.getId());
		HashMap<Pizza,Integer> H = order.getOrders();
		for(String key : h.keySet())
		{
			String[] a = h.get(key);
			H.put(new Pizza(a[2],a[0]),Integer.parseInt(a[1]));
		}
		return order;
	}
	
	public static Order trackOrder(String l)
	{
		for(Order o : orders) if(o.getId().equals(l)) return o;
		return null;
	}
	
	public static Customer queryUser(String phone)
	{
		for(Customer i : users)
		{
			if(i.getMobileNo().equals(phone)) return i;
		}
		return null;
	}
	
	public static void updateOrderState(String id)
	{
		Order order = null;
		for(Order o : orders) if(o.getId().equals(id)) {order = o;break;}
		if(order == null) return;
		if(order.getStatus() == 6) return;
		order.setStatus(order.getStatus() + 1);
	}
	
	public static Vector<Order> getOrders()
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
