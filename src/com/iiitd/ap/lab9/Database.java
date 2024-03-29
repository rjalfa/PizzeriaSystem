package com.iiitd.ap.lab9;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;
import java.util.concurrent.locks.ReentrantLock;

import com.iiitd.ap.lab9.model.Address;
import com.iiitd.ap.lab9.model.Customer;
import com.iiitd.ap.lab9.model.Order;
import com.iiitd.ap.lab9.model.Pizza;
/**
 * 
 * @author Rounaq jhunjhunu Wala | 2014089
 * @author Shrey Bagroy	| 2014099
 *
 */

public final class Database {
	static private Vector<Customer> users = new Vector<>();
	static private Vector<Order> orders = new Vector<>();
	static private final ReentrantLock lock = new ReentrantLock();
	
	static
	{
		Order order = null;
		ObjectInputStream inStream = null;
		orders.clear();
		File f = new File("./data");
		if(!f.exists()) f.mkdir();
		File[] dataFolder = (new File("./data")).listFiles();
		try
		{
			for(int i=0;i<dataFolder.length;i++)
			{
				inStream = new ObjectInputStream(new BufferedInputStream(new FileInputStream("./data/"+dataFolder[i].getName())));
				try{
					while (true)
					{	
						order = (Order)inStream.readObject();
						orders.add(order);
					}
				}
				catch(EOFException e){
					inStream.close();
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		System.out.println("[MESSAGE] File Prefetch complete. Status OK!!");
	}
	
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
		lock.lock();
		try
		{
			ObjectOutputStream outStream = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("./data/ORDER" + order.getId()+".dat")));
			outStream.writeObject(order);
			outStream.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			lock.unlock();
		}
		return order;
	}
	
	public static Order createOrder(Order order,ArrayList<String[]> order_details)
	{
		System.out.printf("[MESSAGE] Order ID %s update members\n",order.getId());
		HashMap<Pizza,Integer> H = order.getOrders();
		for(String[] key : order_details) H.put(new Pizza(key[0],key[1]),Integer.parseInt(key[2]));
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
		System.out.printf("[MESSAGE] Order ID %s update state request\n",id);
		Order order = null;
		for(Order o : orders) if(o.getId().equals(id)) {order = o;break;}
		if(order == null) return;
		if(order.getStatus() == 6) return;
		order.setStatus(order.getStatus() + 1);
		try
		{
			ObjectOutputStream outStream = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("./data/ORDER" + order.getId()+".dat")));
			outStream.writeObject(order);
			outStream.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
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
