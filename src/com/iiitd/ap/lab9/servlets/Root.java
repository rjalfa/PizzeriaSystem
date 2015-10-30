package com.iiitd.ap.lab9.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.iiitd.ap.lab9.Database;
import com.iiitd.ap.lab9.model.*;

/**
 * Servlet implementation class Root
 */
@WebServlet("/root")
public class Root extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Root() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response, ArrayList<Order> orders) throws ServletException, IOException {
		
		response.getWriter().println("<html><body>");
		response.getWriter().println("<h1>Welcome Admin!</h1>");
		response.getWriter().println("<h2>View currently placed orders here:</h2>");
		response.getWriter().println("<table><tr><th>Order ID</th><th>Pizzas</th><th>State</th><th>Update</th></tr>");
		for(Order order: orders)
		{
			response.getWriter().println("<tr>");
			response.getWriter().println("<td>");
			response.getWriter().println(order.getId());
			response.getWriter().println("</td>");
			response.getWriter().println("<td>");
			for(Pizza p: order.getOrders().keySet())
			{
				response.getWriter().print(p.getToppings()+",");
			}
			response.getWriter().println("</td>");
//			response.getWriter().println("<td>");
//			response.getWriter().println(order.);
//			response.getWriter().println("</td>");
			response.getWriter().println("<td>");
			response.getWriter().println("<form action='./root' method='post'>");
			response.getWriter().println("<input type='hidden' name='orderId' value="+ order.getId()+ ">");
			response.getWriter().println("</form>");
			response.getWriter().println("</td>");
			response.getWriter().println("</tr>");
		}
		response.getWriter().println("</table>");
		response.getWriter().println("</body></html>");

		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Order> orders = Database.getIncompleteOrders();
		doGet(request, response,orders);
	}

}
