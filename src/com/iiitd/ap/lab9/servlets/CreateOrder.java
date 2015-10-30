package com.iiitd.ap.lab9.servlets;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
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
		Cookie[] cookies = request.getCookies();

		if (cookies != null) {
		 for (Cookie cookie : cookies) {
		   if (cookie.getName().equals("orderID")) {
			   cookie.setMaxAge(0);
			   System.out.println("[MESSAGE] Cleared Duplicate Cookies");
		   }
		  }
		}
		response.addCookie(new Cookie("orderID",""+order.getId()));
		
		RequestDispatcher view = request.getRequestDispatcher("/order/Details.html");
		view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession() == null) System.out.println("[ERROR] HttpSession Object NULL");
		Order order = (Order) request.getSession(false).getAttribute("order");
		order = createOrder(order,request,response);
		request.getSession().setAttribute("order",order);
		System.out.println("[INFO] Order at CreateOrder toString: "+order);
		doGet(request, response,order);
	}
	
	@SuppressWarnings("static-access")
	protected Order createOrder(Order order,HttpServletRequest req,HttpServletResponse response) throws IOException
	{
		HashMap<String,String[]> order_details = new HashMap<>();
		for(String pizza : this.pizzas)
		{
			if(req.getParameter(pizza) != null && Integer.parseInt(req.getParameter(pizza+"-qty")) > 0)
			{
				String[] temp = new String[3];
				temp[0] = req.getParameter(pizza + "-size");
				temp[1] = req.getParameter(pizza + "-qty");
				temp[2] = pizza_names.get(pizza);
				order_details.put(pizza,temp);
			}
		}
		return Database.createOrder(order, order_details);
	}
	
	

}
