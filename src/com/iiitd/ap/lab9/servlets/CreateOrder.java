package com.iiitd.ap.lab9.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.iiitd.ap.lab9.Database;
import com.iiitd.ap.lab9.model.Order;

/**
 * Servlet implementation class NewOrder
 */
/**
 * 
 * @author Rounaq jhunjhunu Wala | 2014089
 * @author Shrey Bagroy	| 2014099
 *
 */
@WebServlet("/CreateOrder")
public class CreateOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;

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
	
	protected Order createOrder(Order order,HttpServletRequest req,HttpServletResponse response) throws IOException
	{
		System.out.println(req.getParameter("data"));
		ArrayList<String[]> order_details = new ArrayList<>();
		String[] t = req.getParameter("data").split(";");
		for(String s : t) order_details.add(s.split(","));
		return Database.createOrder(order, order_details);
	}
}
