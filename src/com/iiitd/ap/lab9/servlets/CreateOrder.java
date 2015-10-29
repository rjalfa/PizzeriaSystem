package com.iiitd.ap.lab9.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.iiitd.ap.lab9.model.Order;

/**
 * Servlet implementation class NewOrder
 */
@WebServlet("/CreateOrder")
public class CreateOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final String[] pizzas = {"mg","pep","fh","dd"};
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateOrder() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Order order = new Order();
		Order order = (Order) request.getSession().getAttribute("order");
		order = createOrder(order,request,response);
		request.getSession().setAttribute("order",order);
		doGet(request, response);
	}
	
	protected Order createOrder(Order order,HttpServletRequest req,HttpServletResponse response) throws IOException
	{
		HashMap<String,String[]> order_details = new HashMap<>();
		String[] temp = new String[2];
		for(String pizza : this.pizzas)
		{
			if(req.getParameter(pizza)!=null && Integer.parseInt(req.getParameter(pizza)) > 0)
			{
				temp[0] = req.getParameter(pizza + "-size");
				temp[1] = req.getParameter(pizza);
				order_details.put(pizza,temp); // {pizza_name : [size,quantity]}
				response.getWriter().println(order_details.get(pizza)[0]);
			}
		}
		//order_details is the hash map with the order details extracted from the params
		return order;
	}
	
	

}
