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

import com.iiitd.ap.lab9.Database;
import com.iiitd.ap.lab9.model.Order;
import com.iiitd.ap.lab9.model.Pizza;

/**
 * Servlet implementation class NewOrder
 */
@WebServlet("/CreateOrder")
public class CreateOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final String[] pizzas = {"mg","pep","fh","dd"};
    private static HashMap<String,String> pizza_names = new HashMap<>();
    static{
    	pizza_names.put("mg", "Margherita");
    	pizza_names.put("pep", "Pepperoni Pizza");
    	pizza_names.put("fh", "Farmhouse");
    	pizza_names.put("dd", "Monster Deep Dish");
    }
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateOrder() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response,Order order) throws ServletException, IOException {
		response.getWriter().println("<h1>Order ID:" + order.getId() + "</h1>");
		response.getWriter().println("<h4>Please enter delivery details</h4>");
		response.getWriter().println("<form method='post' action='./FinalPage'>");
		response.getWriter().println("Name: <input type='text' name='name'/>");
		response.getWriter().println("Phone: <input type='text' name='phone'/>");
		response.getWriter().println("Address: <input type='text' name='address'/>");
		response.getWriter().println("<input type='submit' name='Enter details'/>");
		response.getWriter().println("</form>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Order order = (Order) request.getSession().getAttribute("order");
		order = createOrder(order,request,response);
		request.getSession().setAttribute("order",order);
		doGet(request, response,order);
	}
	
	protected Order createOrder(Order order,HttpServletRequest req,HttpServletResponse response) throws IOException
	{
		HashMap<String,String[]> order_details = new HashMap<>();
		String[] temp = new String[3];
		for(String pizza : this.pizzas)
		{
			if(req.getParameter(pizza)!=null && Integer.parseInt(req.getParameter(pizza)) > 0)
			{
				temp[0] = req.getParameter(pizza + "-size");
				temp[1] = req.getParameter(pizza);
				temp[2] = pizza_names.get(pizza);
				order_details.put(pizza,temp);
			}
		}
		return Database.createOrder(order, order_details);
	}
	
	

}
